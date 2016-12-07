package com.ahmadrosid.sociallogin;

import android.app.Application;

import com.jaychang.slm.SocialLoginManager;

/**
 * Created by ocittwo on 12/7/16.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class App extends Application{

    @Override public void onCreate() {
        super.onCreate();
        SocialLoginManager.init(this);
    }

}
