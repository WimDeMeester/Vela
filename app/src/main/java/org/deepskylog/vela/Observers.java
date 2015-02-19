package org.deepskylog.vela;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.deepskylog.vela.telescopecontrol.R;


/**
 * Created by wim on 2/19/15.
 */
public class Observers {

    private static final String TAG = "Vela.Observers";

    public static void onLoginResult(String resultRaw) {
        try {
            String result = Utils.getTagContent(resultRaw, "result");
            if (result.startsWith("loggedUser:")) {
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.mainActivity);
                SharedPreferences.Editor mySettings = settings.edit();
                mySettings.putString("loggedUser", result.substring(11));
                mySettings.commit();
                Log.i(TAG, "Logged in as " + result.substring(11));
                Toast.makeText(MainActivity.mainActivity, new StringBuilder().append(
                        MainActivity.mainActivity.getString(R.string.logged_in)).append(" ").append(result.substring(11)).toString(), Toast.LENGTH_LONG).show();
            } else {
                // Not correctly logged in...
                Log.i(TAG, "Problem logging in.");
                Toast.makeText(MainActivity.mainActivity, R.string.cannot_log_in, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Observers Exception 1: " + e.toString());
        }
    }

}
