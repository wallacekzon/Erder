package com.example.walla.erder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class AccountActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private final int RC_SIGN_IN = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        setSwitchOnClickListeners();
        setGoogleAccountOnClickListeners();
    }

    private void setGoogleAccountOnClickListeners() {
        TextView googleAccountTxt = (TextView) findViewById(R.id.account_google_account);
        googleAccountTxt.setOnClickListener(googleAccountTxtListener());
    }


    private View.OnClickListener googleAccountTxtListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Configure sign-in to request the user's ID, email address, and basic profile. ID and
                // basic profile are included in DEFAULT_SIGN_IN.
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
                GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(AccountActivity.this)
                        .enableAutoManage(AccountActivity.this, AccountActivity.this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                        .build();

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        };
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from
        //   GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();
                // Get account information
                String mFullName = acct.getDisplayName();
                String mEmail = acct.getEmail();

                System.out.println(mFullName + mEmail);
            }
        }
    }

    private void setSwitchOnClickListeners() {
        Button signUpButton = (Button) findViewById(R.id.account_signup_button);
        signUpButton.setOnClickListener(signUpButtonListener());

        Button loginButton = (Button) findViewById(R.id.account_login_button);
        loginButton.setOnClickListener(loginButtonListener());
    }

    private View.OnClickListener signUpButtonListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button signUpButton = (Button) findViewById(R.id.account_signup_button);
                Button loginButton = (Button) findViewById(R.id.account_login_button);

                signUpButton.setBackgroundColor(ContextCompat.getColor(AccountActivity.this, R.color.colorPrimaryDark));
                signUpButton.setTextColor(Color.WHITE);
                loginButton.setBackgroundColor(Color.LTGRAY);
                loginButton.setTextColor(Color.GRAY);

                Button submitButton = (Button) findViewById(R.id.account_submit_button);
                submitButton.setText("SIGN UP");
            }
        };
    }

    private View.OnClickListener loginButtonListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button signUpButton = (Button) findViewById(R.id.account_signup_button);
                Button loginButton = (Button) findViewById(R.id.account_login_button);

                signUpButton.setBackgroundColor(Color.LTGRAY);
                signUpButton.setTextColor(Color.GRAY);
                loginButton.setBackgroundColor(ContextCompat.getColor(AccountActivity.this,R.color.colorPrimaryDark));
                loginButton.setTextColor(Color.WHITE);

                Button submitButton = (Button) findViewById(R.id.account_submit_button);
                submitButton.setText("LOG IN");
            }
        };
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
