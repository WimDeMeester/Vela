package org.deepskylog.vela.wizard;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.deepskylog.vela.R;
import org.deepskylog.vela.util.Android;

public class Bluetooth extends AppCompatActivity {

    BluetoothAdapter _mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Check if bluetooth is enabled. If this is the case, change the button and make the button unselectable
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_bluetooth);

        // TODO: Why is the image not shown on the Nexus 5?
        
        if (_mBluetoothAdapter != null && _mBluetoothAdapter.isEnabled()) {
            // Change the button to show that bluetooth is enabled.
            Button mButton=(Button)this.getWindow().getDecorView().findViewById(R.id.enableBluetoothButton);
            mButton.setText(R.string.bluetoothEnabled);

            // Make the button unselectable
            mButton.setActivated(false);
            mButton.setEnabled(false);
            mButton.setClickable(false);
        }
    }

    public void arduinoButtonOnClick(View v) {
        Intent intent = new Intent(this, Hardware.class);
        startActivity(intent);
    }

    public void enableBluetoothOnClick(View v) {
        // Check if the device has a bluetooth adapter
        if (_mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Android.showDialog(this, R.string.noBluetooth, R.string.noBluetoothTitle, R.string.ok);
        } else {
            if (!_mBluetoothAdapter.isEnabled()) {
                // Turn on bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                int REQUEST_ENABLE_BT = 1;
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

                // TODO: Wait for result!
                // Change the button to show that bluetooth is enabled.
                Button mButton=(Button)v.findViewById(R.id.enableBluetoothButton);
                mButton.setText(R.string.bluetoothEnabled);

                // Make the button unselectable
                mButton.setActivated(false);
                mButton.setEnabled(false);
                mButton.setClickable(false);
            }
        }
    // TODO: If there is no bluetooth, go to the DeepskyLog activity
    }
}
