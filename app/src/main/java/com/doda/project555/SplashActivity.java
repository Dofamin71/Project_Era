package com.doda.project555;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    private boolean flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        startLogin();
    }

    void startLogin() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.AnonymousBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        } else {
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Destroy", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Restart", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Pause", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Resume", "onResume");
        if (flag) {
            startLogin();
            flag = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Start", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Stop", "onStop");
        flag = true;
    }
}
