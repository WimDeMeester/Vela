package org.deepskylog.vela;

import android.os.AsyncTask;

/**
 * Created by wim on 2/17/15.
 */
public class DslCommand {
    private static final String SERVER_URL = "http://www.deepskylog.org/";

    public static void getCommandAndInvokeClassMethod(String command, String params, String getDslCommandOnResultClass, String getDslCommandOnResultMethod) {
        //MainActivity.mainActivity.setProgressBarIndeterminateVisibility(true);
        new getCommandAndInvokeClassMethodTask().execute(SERVER_URL + "appgetcommand.php?command=" + command + params, getDslCommandOnResultClass, getDslCommandOnResultMethod);
    }

    private static class getCommandAndInvokeClassMethodTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return "<onResultClass>" + urls[1] + "</onResultClass>" +
                    "<onResultMethod>" + urls[2] + "</onResultMethod>" +
                    Utils.downloadUrl(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Utils.invokeClassMethodWithResult(result.trim());
        }
    }


}
