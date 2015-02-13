package org.deepskylog.vela;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    /**
     * Returns true if there is a connection with the internet
     *
     * @param context The context where we should test the internetconnection.
     * @return True if there is a connection with the internet
     */
    public static boolean isConnectedToTheInternet(Context context) {
        // Check the network connection
        ConnectivityManager check = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] info = check.getAllNetworkInfo();

        for (int i = 0; i < info.length; i++) {
            if (info[i].getState() == NetworkInfo.State.CONNECTED)
                return true;
        }
        return false;
    }
}
