package org.deepskylog.vela.wizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import org.deepskylog.vela.R;

public class Bluetooth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_bluetooth);
    }

    public void arduinoButtonOnClick(View v) {
        Intent intent = new Intent(this, Hardware.class);
        startActivity(intent);
    }

    public void enableBluetoothOnClick(View v) {
        //Intent intent = new Intent(this, Bluetooth.class);
        //startActivity(intent);
    }
}
