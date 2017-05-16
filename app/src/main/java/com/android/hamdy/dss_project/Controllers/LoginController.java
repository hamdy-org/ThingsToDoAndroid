package com.android.hamdy.dss_project.Controllers;

import android.content.Context;
import android.util.Log;
import android.view.View;


import com.android.hamdy.dss_project.Models.LoginModel;
import com.android.hamdy.dss_project.ThingsApp;
import com.android.hamdy.dss_project.User;
import com.android.hamdy.dss_project.Utilities.SharedPref;
import com.android.hamdy.dss_project.Views.Activities.HomeActivity;
import com.android.hamdy.dss_project.Views.Activities.LoginActivity;
import com.android.hamdy.dss_project.Views.Activities.SignUpActivity;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Vendor.Authentication.Authentication;
import Vendor.Authentication.IAuthenticationHandler;
import Vendor.Errors.Error;
import Vendor.Errors.TextInput;
import Vendor.Validation.Email;
import Vendor.Validation.IValidationRule;
import Vendor.Validation.Required;
import Vendor.Validation.Validation;

/**
 * Created by Hamdy on 4/18/2017.
 */

public class LoginController implements
        Vendor.Validation.IValidationHandler,
        IAuthenticationHandler {

    private  final String TAG = "LoginController";
    public final LoginActivity mLoginActivity;
    private final User user;
    private final LoginModel model;
    private Authentication auth;
    private Context context;

    public LoginController(LoginActivity loginActivity) {
        context = loginActivity.getApplicationContext();
        mLoginActivity = loginActivity;

        user = ((ThingsApp) mLoginActivity.getApplication()).user;

        model = new LoginModel();

    }


    public void actionListener() {

        mLoginActivity.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
        mLoginActivity.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.start(mLoginActivity);
            }
        });
    }


    private void doLogin() {

        String email =  mLoginActivity.emailET.getText().toString();
        String password = mLoginActivity.passwordET.getText().toString();



        model.email = email;
        model.password = password;

        Validation validation = new Validation();

        //mobile
        validation.addValidationField(
                email ,
                new TextInput( mLoginActivity.emailTIL) ,
                new IValidationRule[]{
                        new Required(),
                        new Email()
                });

        //password
        validation.addValidationField(
                password ,
                new TextInput(mLoginActivity.passwordTIL ),
                new IValidationRule[]{
                        new Required()
                } );

        validation.validate(context , this);

    }

    @Override
    public void onValidationSuccessfull() {
        Log.e(TAG , "onValidationSuccessfull");
        auth =  new Authentication();
        auth.attemp(context , model, this);
    }

    @Override
    public void onValidationFailed(ArrayList<Error> errors) {
        Log.e(TAG , "onValidationFailed : " + errors.toString());
        mLoginActivity.displayValidationErrors(errors);
    }


    @Override
    public void onAuthSuccessfull(JSONObject data) {

        Log.e(TAG , "user : " + data.toString());
        //start home;

        try {
            String id = data.getString("id");
            String name = data.getString("name");
            String email = data.getString("email");

            SharedPref.putString(context , "pref_user_id" , id );
            SharedPref.putString(context , "pref_user_name" , name );
            SharedPref.putString(context , "pref_user_email" , email );

            HomeActivity.start(mLoginActivity);


        } catch (JSONException e) {
            e.printStackTrace();
            String message = context.getString(context.getResources().getIdentifier("error_occured", "string", context.getPackageName()));
            mLoginActivity.displayLoginMessage(message);
        }

    }

    @Override
    public void onAuthFailed(int messageCode) {
        Log.e(TAG , "onAuthFailed : " + messageCode);

        String message = context.getString(context.getResources().getIdentifier("error_occured", "string", context.getPackageName()));


        mLoginActivity.displayLoginMessage(message);
    }

    @Override
    public void onAuthResponseFailed(VolleyError error) {

        Log.e(TAG , "onAuthResponseFailed : " + error.toString());

        String message = context.getString(context.getResources().getIdentifier("error_occured", "string", context.getPackageName()));


        mLoginActivity.displayLoginMessage(message);
    }
}
