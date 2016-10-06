package org.deepskylog.vela.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by wim on 06/10/2016.
 */

public class Android {
    /**
     * Show a dialog and sets the title, message and button name. Nothing happens when the button is
     * clicked
     * @param act The activity where the dialog should be shown
     * @param message The message to show
     * @param title The title to show
     * @param button The text of the button
     */
    public static void showDialog(Activity act, int message, int title, int button) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(act);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message)
                .setTitle(title);

        builder.setPositiveButton(button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Nothing happens
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();

    }
}
