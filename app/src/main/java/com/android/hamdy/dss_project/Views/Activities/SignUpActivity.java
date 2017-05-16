package com.android.hamdy.dss_project.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.hamdy.dss_project.Controllers.SignupController;
import com.android.hamdy.dss_project.R;
import com.android.hamdy.dss_project.Utilities.AlertHelper;

import java.util.ArrayList;

import Vendor.Errors.Error;

public class SignUpActivity extends AppCompatActivity {

    public EditText nameET;
    public EditText emailET;
    public EditText passwrodET;
    public TextInputLayout nameTIL;
    public TextInputLayout emailTIL;
    public TextInputLayout passwrodTIL;
    public Button signupBtn;
    public Button signinBtn;
    public EditText confirmPasswrodET;
    public TextInputLayout confirmPasswrodTIL;
    public RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        container =  (RelativeLayout)findViewById(R.id.container_signup_rl);
        nameET =  (EditText)findViewById(R.id.name_signup_et);
        emailET =  (EditText)findViewById(R.id.email_signup_et);
        passwrodET =  (EditText)findViewById(R.id.password_signup_et);
        confirmPasswrodET =  (EditText)findViewById(R.id.confirmPassword_signup_et);

        nameTIL =  (TextInputLayout)findViewById(R.id.name_signup_til);
        emailTIL =  (TextInputLayout)findViewById(R.id.email_signup_til);
        passwrodTIL =  (TextInputLayout)findViewById(R.id.password_signup_til);
        confirmPasswrodTIL =  (TextInputLayout)findViewById(R.id.confirmPassword_signup_til);

        signupBtn =  (Button)findViewById(R.id.sign_up_btn);
        signinBtn =  (Button)findViewById(R.id.sign_in_btn);



        new SignupController(this).actionListener();




    }

    public void displayValidationErrors(ArrayList<Error> errors) {

        for(int i = 0 ; i < errors.size() ; i++){
            errors.get(i).displayError();
        }

    }

    public void displayError(String message) {
        AlertHelper.showLongSnackBar(container , message);
    }


    public static void start(Context context) {

        Intent i = new Intent(context, SignUpActivity.class);
        context.startActivity(i);
        ((AppCompatActivity) context).finish();
    }
}
