package com.newrelic.cordova;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import com.newrelic.agent.android.NewRelic;

import android.util.Log;

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
        ).start(this.getApplication());
    }


    private string getAppToken(){
        String token = "";
        token = this.cordova.getActivity().getStringProperty("NewRelicApplicationToken");

        Log.d(LOGTAG, "NewRelic Token: " + token);

        return token;
    }

}