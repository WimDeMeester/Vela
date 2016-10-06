package org.deepskylog.vela.wizard;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import org.deepskylog.vela.R;
import org.deepskylog.vela.util.Android;

public class Bluetooth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Check if bluetooth is enabled. If this is the case, change the button and make the button unselectable
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_bluetooth);
    }

    public void arduinoButtonOnClick(View v) {
        Intent intent = new Intent(this, Hardware.class);
        startActivity(intent);
    }

    public void enableBluetoothOnClick(View v) {
        // Check if the device has a bluetooth adapter
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Android.showDialog(this, R.string.noBluetooth, R.string.noBluetoothTitle, R.string.ok);
        }

        // TODO: Turn on bluetooth

        // TODO: Change the button to show that bluetooth is enabled.
        // TODO: Make the button unselectable

        //Intent intent = new Intent(this, Bluetooth.class);
        //startActivity(intent);
    }
}
