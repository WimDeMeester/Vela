package org.deepskylog.vela;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public static String downloadUrl(String theUrl) {
        InputStream inputStream = null;
        try {
            URL url = new URL(theUrl);
            HttpURLConnection conn = ((HttpURLConnection) url.openConnection());
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                inputStream = conn.getInputStream();
                LocalBroadcastManager.getInstance(MainActivity.mainActivity).sendBroadcast(new Intent("org.deepskylog.online").putExtra("org.deepskylog.online", "online"));
                byte[] buffer = new byte[500000];
                byte[] tempbuffer = new byte[500000];
                int count = 0;
                int len1 = 0;
                while ((len1 = inputStream.read(tempbuffer)) > 0) {
                    for (int i = 0; i < len1; i++) buffer[count + i] = tempbuffer[i];
                    count += len1;
                }
                return new String(buffer);
            } else {
                LocalBroadcastManager.getInstance(MainActivity.mainActivity).sendBroadcast(new Intent("org.deepskylog.online").putExtra("org.deepskylog.online", "offline"));
                return "<result>" + "Unavailable url: " + theUrl + "</result>";
            }
        } catch (Exception e) {
            LocalBroadcastManager.getInstance(MainActivity.mainActivity).sendBroadcast(new Intent("org.deepskylog.online").putExtra("org.deepskylog.online", "offline"));
            return "<result>" + "Unavailable url: " + theUrl + "</result>";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void invokeClassMethodWithResult(String result) {
        try {
            Class.forName(getTagContent(result, "onResultClass")).getMethod(getTagContent(result, "onResultMethod"), String.class).invoke(null, result);
        } catch (Exception e) {
            Toast.makeText(MainActivity.mainActivity, "Utils.invokeClassMethodWithResult: Exception 2, " + result + " " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    public static String getTagContent(String result, String tag) throws Exception {
        if (result == null) throw new Exception("Utils.getTagContent: No string to analyse");
        if (tag == null) throw new Exception("Utils.getTagContent: No tag to analyse");
        if (tag.equals("")) throw new Exception("Utils.getTagContent: Empty tag to analyse");
        Integer datatag1 = result.indexOf("<" + tag + ">") + (tag.length() + 2);
        if (datatag1 == -1)
            throw new Exception("Utils.getTagContent: No opening tag " + tag + " in " + result);
        Integer datatag2 = result.indexOf("</" + tag + ">", datatag1);
        if (datatag2 == -1)
            throw new Exception("Utils.getTagContent: No closing tag " + tag + " in " + result);
        return result.substring(datatag1, datatag2);
    }
}
