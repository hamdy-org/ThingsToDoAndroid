package com.android.hamdy.dss_project.Views.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import com.android.hamdy.dss_project.Controllers.LoginController;
import com.android.hamdy.dss_project.R;
import com.android.hamdy.dss_project.Utilities.AlertHelper;

import Vendor.Errors.Error;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    public EditText emailET;
    public EditText passwordET;
    public View mProgressView;
    public View mLoginFormView;
    public Button signInBtn;
    public LoginController controller;
    public TextInputLayout passwordTIL;
    public TextInputLayout emailTIL;
    public Button signUpBtn;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.


        container =(LinearLayout) findViewById(R.id.activity_login_container_ll);
        emailET = (EditText) findViewById(R.id.email);
        passwordET = (EditText) findViewById(R.id.password);


        emailTIL = (TextInputLayout) findViewById(R.id.email_til);
        passwordTIL = (TextInputLayout) findViewById(R.id.password_til);


        signInBtn = (Button) findViewById(R.id.sign_in_btn);
        signUpBtn = (Button) findViewById(R.id.sign_up_btn);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        controller = new LoginController(this);

        controller.actionListener();


    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            container =  (LinearLayout)findViewById(R.id.activity_login_container_ll);


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

    public void displayValidationErrors(ArrayList<Error> errors) {

        for(int i = 0 ; i < errors.size() ; i++){
            errors.get(i).displayError();
        }

    }

    public void displayLoginMessage(String message) {
//        loginErrorTV.setText(message);

        AlertHelper.showLongSnackBar(container , message);
    }


    public static void start(Context context) {

        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((AppCompatActivity) context).finish();
    }
}

