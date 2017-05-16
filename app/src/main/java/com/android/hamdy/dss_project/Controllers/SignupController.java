package com.android.hamdy.dss_project.Controllers;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.hamdy.dss_project.Models.SignupModel;
import com.android.hamdy.dss_project.R;
import com.android.hamdy.dss_project.ThingsApp;
import com.android.hamdy.dss_project.User;
import com.android.hamdy.dss_project.Utilities.ResponseParserUtils;
import com.android.hamdy.dss_project.Views.Activities.LoginActivity;
import com.android.hamdy.dss_project.Views.Activities.SignUpActivity;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Vendor.Errors.Error;
import Vendor.Errors.TextInput;
import Vendor.Validation.ConfirmPassword;
import Vendor.Validation.Email;
import Vendor.Validation.IValidationHandler;
import Vendor.Validation.IValidationRule;
import Vendor.Validation.Required;
import Vendor.Validation.Validation;
import Vendor.WebService.IResponseHandler;

/**
 * Created by AppleXIcon on 20/4/2017.
 */

public class SignupController implements IValidationHandler, IResponseHandler {

    private final User user;
    private final SignupModel model;
    private ArrayList<Error> errors;

    private  final String TAG = "CreateAccountController";
    private final Context context;
    private SignUpActivity mSignUpActivity;


    public SignupController(SignUpActivity createNewAccountActivity) {

        this.context = createNewAccountActivity.getApplicationContext();
        this.mSignUpActivity = createNewAccountActivity;

        user = ((ThingsApp) mSignUpActivity.getApplication()).user;

        model = new SignupModel();

    }

    public void actionListener() {

        mSignUpActivity.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signupBtn();
            }
        });

        mSignUpActivity.signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signinBtn();
            }
        });
    }

    private void signinBtn() {

        LoginActivity.start(mSignUpActivity);
    }

    private void signupBtn() {

       

        String name = mSignUpActivity.nameET.getText().toString();
        String email = mSignUpActivity.emailET.getText().toString();
        String password = mSignUpActivity.passwrodET.getText().toString();
        String confirmPassword = mSignUpActivity.confirmPasswrodET.getText().toString();



        model.name = name;
        model.email = email;
        model.password = password;

        Validation validation = new Validation();

        //name field
        validation.addValidationField(
                name ,
                new TextInput( mSignUpActivity.nameTIL) ,
                new IValidationRule[]{new Required()});

        //email field
        validation.addValidationField(
                email,
                new TextInput( mSignUpActivity.emailTIL) ,
                new IValidationRule[]{
                        new Required(),
                        new Email()
                });

        //password
        validation.addValidationField(
                password ,
                new TextInput(mSignUpActivity.passwrodTIL),
                new IValidationRule[]{
                        new Required()
                } );

        //confirm password
        validation.addValidationField(
                confirmPassword ,
                new TextInput(mSignUpActivity.confirmPasswrodTIL ),
                new IValidationRule[]{
                        new Required(),
                        new ConfirmPassword(password)
                } );

        validation.validate(context , this);

    }

    @Override
    public void onValidationSuccessfull() {
        Log.e(TAG , "onValidationSuccessfull");
        model.signup(context , this);//add user
    }

    @Override
    public void onValidationFailed(ArrayList<Error> errors) {
        Log.e(TAG , "onValidationFailed : " + errors.toString());
        mSignUpActivity.displayValidationErrors(errors);
    }

    @Override
    public void onResponseSuccess(String response) {
        Log.e(TAG , response);

        try {
            ResponseParserUtils parser = new ResponseParserUtils(response);

            if(!parser.result) {

                String message = context.getString(context.getResources().getIdentifier("error_occured", "string", context.getPackageName()));;

                mSignUpActivity.displayError(message);
            }else
                LoginActivity.start(mSignUpActivity);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Error> getErrors(JSONObject data) {

        errors = new ArrayList<>();

        try {
            if(data.has("name")) {
                errors.add( new Error( getMessage( data.getJSONArray("fullname")) , new TextInput(mSignUpActivity.nameTIL)));
            }

            if(data.has("email")) {
                errors.add( new Error( getMessage( data.getJSONArray("email")) , new TextInput(mSignUpActivity.emailTIL)));
            }

            if(data.has("password")) {
                errors.add( new Error( getMessage( data.getJSONArray("password")) , new TextInput(mSignUpActivity.passwrodTIL)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  errors;

    }

    private String getMessage(JSONArray array) throws JSONException {

        String message = "";
        for(int i = 0 ; i < array.length() ; i++){

            message += array.getString(i) + "\n";
        }

        return message;

    }


    @Override
    public void onResponseFailed(VolleyError error) {
        Log.e(TAG , error.toString());

        String message ;
        if(error instanceof TimeoutError || error instanceof NoConnectionError)
            message = context.getString(R.string.no_internet_connection);
        else
            message = context.getString(R.string.error_occured);

        mSignUpActivity.displayError(message);
    }
}
