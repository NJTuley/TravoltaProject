package io.github.ngbrown11.practioner_ui_remake;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ArrayList<TextView> textViews = new ArrayList<TextView>();
    private int num_txt_views = 0;
    private String view_type = "New";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.username);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }/* else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }*/

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
            setContentView(R.layout.main_doctor_view);
            openLoggedInMainView();

        }
    }

    private void openLoggedInMainView()
    {
        Button logout_button = (Button) findViewById(R.id.logoutBtn);
        logout_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_login);
                Button login_button = (Button) findViewById((R.id.email_sign_in_button));
                login_button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        attemptLogin();
                    }
                });
            }
        });

        Button new_button = (Button) findViewById(R.id.newBtn);
        Button old_button = (Button) findViewById(R.id.oldBtn);
        Button sent_button = (Button) findViewById(R.id.sentBtn);

        new_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewTypeMainScreen("New");
            }
        });

        old_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewTypeMainScreen("Old");
            }
        });

        sent_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeViewTypeMainScreen("Sent");
            }
        });

        for(int i = 0; i < 6; i++)
            addMessagePending(view_type);

        Button newUserBtn = (Button) findViewById(R.id.newPatientBtn);
        newUserBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.new_patient);
                openNewPatientView();
            }
        });
    }

    private void openNewPatientView()
    {
        Button loginBackBtn = (Button) findViewById(R.id.newUserBackBtn);
        loginBackBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.main_doctor_view);
                openLoggedInMainView();
            }
        });

        Button createNewUser = (Button) findViewById(R.id.createNewUserFinalBtn);
        createNewUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.main_doctor_view);
                openLoggedInMainView();
            }
        });
    }

    private void changeViewTypeMainScreen(String new_type)
    {
        view_type = new_type;
        LinearLayout layout = (LinearLayout) findViewById(R.id.msg_holder);
        layout.removeAllViews();

        for(int i = 0; i < 6; i++)
            addMessagePending(view_type);
    }

    private void addMessagePending(String type) {
        LinearLayout msg_holder = (LinearLayout) findViewById(R.id.msg_holder);
        TextView txt = new TextView(this);
        MessageFromPatient msg;
        MessageFromPractitioner msg2;

        txt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.message_full_view);
                showMessageFull("Patient Testing", "Testing Subject", "Urgent", "Hello, Hello, Testing 123");
            }
        });

        if (type == "New") {

            msg = new MessageFromPatient("TempSender", "URGENT TESTING", "Testing Testing", "Urgent", null);
            txt.setText(msg.getSenderId() + " - " + msg.getSubject() + " - " + msg.getSeverity());
        }
        else if (type == "Old")
        {
            msg = new MessageFromPatient("TempSender", "Old Testing", "Testing Testing", "Low", null);
            txt.setText(msg.getSenderId() + "\t-\t" + msg.getSubject() + "\t-\t" + msg.getSeverity());
        }
        else if(type == "Sent") {
            msg2 = new MessageFromPractitioner("MeSender", "Sent Testing", "Testing Testing", "High", null);
            txt.setText(msg2.getSenderId() + "\t-\t" + msg2.getSubject() + "\t-\t" + msg2.getSeverity());
        }
        textViews.add(txt);
        num_txt_views++;

        txt.setHeight(200);
        txt.setBackgroundDrawable(getResources().getDrawable((R.drawable.back)));
        msg_holder.addView(txt);
    }

    private void showMessageFull(String _sender, String _subj, String _severity, String _content)
    {
        TextView txt = (TextView) findViewById(R.id.senderTxtFull);
        txt.setText(_sender);
        txt = (TextView) findViewById(R.id.subjectTxtFull);
        txt.setText(_subj + " - " + _severity);
        txt = (TextView) findViewById(R.id.contentTxtFull);
        txt.setText(_content);

        Button btn = (Button) findViewById(R.id.backBtnMessageFull);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.main_doctor_view);
                openLoggedInMainView();
            }
        });

        Button reply_btn = (Button) findViewById(R.id.replyBtn);
        reply_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.write_reply_view);
                openReplyView();
            }
        });
    }

    private void openReplyView()
    {
        Button backReplyBtn = (Button) findViewById(R.id.backReplyBtn);
        backReplyBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.message_full_view);
                showMessageFull("Patient Testing", "Testing Subject", "Urgent", "Hello, Hello, Testing 123");
            }
        });

        Button sendReplyButton = (Button) findViewById(R.id.sendReplyBtn);
        sendReplyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.main_doctor_view);
                openLoggedInMainView();
            }
        });
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(Login.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
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