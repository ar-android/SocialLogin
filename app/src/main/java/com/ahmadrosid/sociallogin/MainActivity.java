package com.ahmadrosid.sociallogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jaychang.slm.SocialLoginManager;
import com.jaychang.slm.SocialUser;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button google, facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google = (Button) findViewById(R.id.google);
        facebook = (Button) findViewById(R.id.facebook);

        google.setOnClickListener(this);
        facebook.setOnClickListener(this);

    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google:
                loginWithGoogle();
                break;
            case R.id.facebook:
                loginWithFacebook();
                break;
        }
    }

    private void loginWithFacebook() {
        SocialLoginManager.getInstance(this)
                .facebook()
                .login()
                .subscribe(new Action1<SocialUser>() {
                    @Override public void call(SocialUser socialUser) {
                        Log.d(TAG, "userId: " + socialUser.userId);
                        Log.d(TAG, "photoUrl: " + socialUser.photoUrl);
                        Log.d(TAG, "accessToken: " + socialUser.accessToken);
                        Log.d(TAG, "name: " + socialUser.profile.name);
                        Log.d(TAG, "email: " + socialUser.profile.email);
                        Log.d(TAG, "pageLink: " + socialUser.profile.pageLink);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "facebook: " + throwable.getMessage(), throwable);
                    }
                });

    }

    private void loginWithGoogle() {
        SocialLoginManager.getInstance(this)
                .google(getString(R.string.default_web_client_id))
                .login()
                .subscribe(new Action1<SocialUser>() {
                    @Override public void call(SocialUser socialUser) {
                        Log.d(TAG, "userId: " + socialUser.userId);
                        Log.d(TAG, "photoUrl: " + socialUser.photoUrl);
                        Log.d(TAG, "accessToken: " + socialUser.accessToken);
                        Log.d(TAG, "email: " + socialUser.profile.email);
                        Log.d(TAG, "name: " + socialUser.profile.name);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "google: " + throwable.getMessage(), throwable);
                    }
                });
    }
}
