package org.deepskylog.vela.wizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.deepskylog.vela.R;

public class Introduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_introduction);
    }

    public void buttonOnClick(View v) {
        Intent intent = new Intent(this, Bluetooth.class);
        startActivity(intent);
    }
}
