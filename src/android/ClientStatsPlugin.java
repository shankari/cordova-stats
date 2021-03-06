package edu.berkeley.eecs.emission.cordova.clientstats;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import edu.berkeley.eecs.emission.cordova.clientstats.ClientStatsHelper;

public class ClientStatsPlugin extends CordovaPlugin {

    protected void pluginInitialize() {
        new ClientStatsHelper(cordova.getActivity()).storeMeasurement(
                "app_launched", // key
                null, // value
                String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (action.equals("storeMeasurement")) {
            Context ctxt = cordova.getActivity();
            (new ClientStatsHelper(ctxt)).storeMeasurement(
                data.getString(0), // key
                data.getString(1), // value
                data.getString(2)); // ts
            callbackContext.success();
            return true;
        } else if (action.equals("storeEventNow")) {
            Context ctxt = cordova.getActivity();
            new ClientStatsHelper(ctxt).storeMeasurement(
                data.getString(0), // key
                null, // value
                String.valueOf(System.currentTimeMillis())); // ts
            callbackContext.success();
            return true;
        } else {
            return false;
        }
    }
}

