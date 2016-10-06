package org.deepskylog.vela.wizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.deepskylog.vela.R;

public class Hardware extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_hardware);
    }

    public void buttonOnClick(View v) {
        Intent intent = new Intent(this, Hardware2.class);
        startActivity(intent);
    }
}
