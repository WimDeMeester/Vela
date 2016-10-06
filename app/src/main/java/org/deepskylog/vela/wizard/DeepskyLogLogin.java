package org.deepskylog.vela.wizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.deepskylog.vela.MainActivity;
import org.deepskylog.vela.R;

public class DeepskyLogLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_deepskylog_login);
    }

    public void deepskyLogSignInOnClick(View view) {
        // TODO: Before going back to the MainActivity class, we should write in a file that we have run the first run wizard.
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void skipOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createAccountOnClick(View view) {
    }
}
