package org.deepskylog.vela.wizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.deepskylog.vela.R;

public class Hardware2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_hardware2);
    }

    public void buttonOnClick(View v) {
        Intent intent = new Intent(this, DeepskyLogLogin.class);
        startActivity(intent);
    }
}
