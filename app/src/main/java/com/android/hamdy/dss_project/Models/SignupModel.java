package com.android.hamdy.dss_project.Models;

import android.content.Context;
import com.android.hamdy.dss_project.Utilities.Constant;


import Vendor.WebService.IResponseHandler;
import Vendor.WebService.Request;
import Vendor.WebService.RequestPost;


/**
 * Created by Hamdy on 4/23/2017.
 */

public class SignupModel {


    public static final String NAME_KEY = "name";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";


    private final int TIMEOUT = 10000 ;


    public String name;
    public String email;
    public String password;



    public void signup(Context context , IResponseHandler responseHandler){


        String url = Constant.SIGNUP_URL;

        RequestPost request = Request.post(context, url)
                .addParam(NAME_KEY, name)
                .addParam(EMAIL_KEY , email)
                .addParam(PASSWORD_KEY , password)
                .setResponseHandler(responseHandler)
                .timeout(TIMEOUT);

        request.send();
    }
}
