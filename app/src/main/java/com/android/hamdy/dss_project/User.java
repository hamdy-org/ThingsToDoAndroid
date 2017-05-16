package com.android.hamdy.dss_project;

import android.content.Context;

import com.android.hamdy.dss_project.Controllers.ThingsListController;
import com.android.hamdy.dss_project.Utilities.Constant;
import com.android.hamdy.dss_project.Utilities.SharedPref;
import com.android.hamdy.dss_project.Views.Activities.LoginActivity;

import Vendor.Authentication.Authentication;
import Vendor.WebService.IResponseHandler;
import Vendor.WebService.Request;

/**
 * Created by hamdy on 16/05/17.
 */

public class User {
    private  final int TIMEOUT = 10000;
    private  final String USER_ID = "user_id";
    public String id;
    public String name;
    public String email;




    public boolean isLoggedIn(Context context) {
        return SharedPref.getBoolean(context , Authentication.PREF_AUTH);
    }

    public void logout(Context context) {
        SharedPref.clear(context);
        LoginActivity.start(context);
    }

    public void getThingsListfromUrl(Context context , IResponseHandler iResponseHandler) {

        String url = Constant.THINGS_LIST_URL + id;
        Request.get(context, url)
                .setResponseHandler(iResponseHandler)
                .timeout(TIMEOUT)
                .send();
    }
}
