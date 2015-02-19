package org.deepskylog.vela;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.deepskylog.vela.telescopecontrol.R;


/**
 * A login screen that offers login via email/password.
 */
public class DeepskyLogLoginActivity extends Activity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mDeepskyLogIdView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We check if we are already connected. If this is the case, we show the username and add a 'Log out' button.
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.mainActivity);
        Log.i("Vela.USER", settings.getString("loggedUser", ""));
        if (settings.getString("loggedUser", "") != "") {
            setContentView((R.layout.activity_deepskylog_logout));
            setupActionBar();

            ((TextView) findViewById(R.id.LoggedInAs)).setText(
                    new StringBuilder().append(
                            MainActivity.mainActivity.getString(R.string.logged_in)).append(" ").append(settings.getString("loggedUser", "")));

            Button mSignOutButton = (Button) findViewById(R.id.deepskylog_logout_button);

            mSignOutButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.mainActivity);
                    settings.edit().remove("loggedUser").commit();
                    finish();
                }
            });

        } else {
            // We are not connected, so we show the log in layout.
            setContentView(R.layout.activity_deepskylog_login);
            setupActionBar();

            // Set up the login form.
            mDeepskyLogIdView = (EditText) findViewById(R.id.DeepskyLogId);

            mPasswordView = (EditText) findViewById(R.id.password);
            mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == R.id.DeepskyLogId || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            });

            Button mSignInButton = (Button) findViewById(R.id.deepskylog_sign_in_button);
            mSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    attemptLogin();
                }
            });

            // If we don't have network connection, disable the settings and add text that we need network access.
            // Easy to test: Enable airplane mode.
            if (!Utils.isConnectedToTheInternet(DeepskyLogLoginActivity.this)) {
                mSignInButton.setEnabled(false);
                mSignInButton.setText(R.string.no_network);
                mDeepskyLogIdView.setEnabled(false);
                mPasswordView.setEnabled(false);
            } else {
                mSignInButton.setEnabled(true);
                mSignInButton.setText(R.string.action_sign_in);
                mDeepskyLogIdView.setEnabled(true);
                mPasswordView.setEnabled(true);
            }

            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);
        }
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mDeepskyLogIdView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String id = mDeepskyLogIdView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid id.
        if (TextUtils.isEmpty(id)) {
            mDeepskyLogIdView.setError(getString(R.string.error_field_required));
            focusView = mDeepskyLogIdView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(id, password);
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mId;
        private final String mPassword;

        UserLoginTask(String id, String password) {
            mId = id;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // attempt authentication against a network service.
            DslCommand.getCommandAndInvokeClassMethod("checkUser", "&userName=" + mId + "&password=" + mPassword, "org.deepskylog.vela.Observers", "onLoginResult");

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}



