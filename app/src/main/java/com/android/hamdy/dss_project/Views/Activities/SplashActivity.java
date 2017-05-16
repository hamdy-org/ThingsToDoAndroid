package com.android.hamdy.dss_project.Views.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.hamdy.dss_project.R;
import com.android.hamdy.dss_project.ThingsApp;
import com.android.hamdy.dss_project.User;


/**
 * Created by AppleXIcon on 18/4/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        user = ((ThingsApp) getApplication()).user;

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                Intent intent = new Intent(SplashActivity.this ,LoginActivity.class);
                startActivity(intent);

                if(user.isLoggedIn(getApplicationContext())){
                    HomeActivity.start(SplashActivity.this);
                }
                else{

                    LoginActivity.start(SplashActivity.this);

                }
            }
        }, 5000);
    }
}
