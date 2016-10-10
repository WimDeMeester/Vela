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
    int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_bluetooth);

        if (_mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Android.showDialog(this, R.string.noBluetooth, R.string.noBluetoothTitle, R.string.ok);

            // Disable the 'Turn on bluetooth' button
            Button tButton = (Button) this.getWindow().getDecorView().findViewById(R.id.enableBluetoothButton);
            tButton.setActivated(false);
            tButton.setEnabled(false);
            tButton.setClickable(false);

            // Change the button to 'Next'
            Button mButton = (Button) this.getWindow().getDecorView().findViewById(R.id.connectArduino);
            mButton.setText(R.string.next);
        }
        if (_mBluetoothAdapter != null && _mBluetoothAdapter.isEnabled()) {
            // Change the button to show that bluetooth is enabled.
            showBluetoothEnabledButton();
        } else if (_mBluetoothAdapter != null) {
            Button mButton = (Button) this.getWindow().getDecorView().findViewById(R.id.connectArduino);
            mButton.setActivated(false);
            mButton.setEnabled(false);
            mButton.setClickable(false);
        }
    }

    public void arduinoButtonOnClick(View v) {
        if (_mBluetoothAdapter == null) {
            // If there is no bluetooth, go to the DeepskyLog activity
            Intent intent = new Intent(this, DeepskyLogLogin.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Hardware.class);
            startActivity(intent);
        }
    }

    public void enableBluetoothOnClick(View v) {
        if (!_mBluetoothAdapter.isEnabled()) {
            // Turn on bluetooth
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    /* What to do when a result is returned from an activity */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                // Disable the 'Enable bluetooth button
                showBluetoothEnabledButton();

                Button mButton = (Button) this.getWindow().getDecorView().findViewById(R.id.connectArduino);
                mButton.setActivated(true);
                mButton.setEnabled(true);
                mButton.setClickable(true);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* Changes the 'Enable bluetooth' button to 'Bluetooth enabled' and disable the button
     */
    private void showBluetoothEnabledButton() {
        // Change the button to show that bluetooth is enabled.
        Button mButton = (Button) this.getWindow().getDecorView().findViewById(R.id.enableBluetoothButton);
        mButton.setText(R.string.bluetoothEnabled);

        // Make the button unselectable
        mButton.setActivated(false);
        mButton.setEnabled(false);
        mButton.setClickable(false);
    }

    // TODO: Only show the Startup-wizard the first time you start up, or the first time you start up after an update
}
