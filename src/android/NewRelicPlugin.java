package com.newrelic.cordova;



import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;

import com.newrelic.agent.android.NewRelic;

import android.content.Context;
import android.util.Log;
import android.os.Bundle;

public class NewRelicPlugin extends CordovaPlugin {

    /** Common tag used for logging statements. */
    private static final String LOGTAG = "NewRelicPlugin";


     /**
     * @param cordova The context of the main Activity.
     * @param webView The associated CordovaWebView.
     */
     @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova,webView);

        this.cordova = cordova;
        this.webView = webView;

        NewRelic.withApplicationToken(
          this.getAppToken()
        ).start(this.cordova.getActivity().getApplication());
    }



    private String getAppToken(){
        //String token = "";
        //token = this.getStringProperty("NewRelicApplicationToken","CHANGE_ME");
        String token = preferences.getString("NewRelicApplicationToken", "NO_KEY");

        Log.d(LOGTAG, "NewRelic Token: " + token);

        return token;
    }

    /**
     * Get string property for activity.
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public String getStringProperty(String name, String defaultValue) {
        name = name.toLowerCase();
        Bundle bundle = this.cordova.getActivity().getIntent().getExtras();
        if (bundle == null) {
            return defaultValue;
        }
        String p = bundle.getString(name);
        if (p == null) {
            return defaultValue;
        }
        return p;
    }

    /**
     * Gets the application context from cordova's main activity.
     * @return the application context
     */
    private Context getApplicationContext() {
        return this.cordova.getActivity().getApplicationContext();
    }

}
