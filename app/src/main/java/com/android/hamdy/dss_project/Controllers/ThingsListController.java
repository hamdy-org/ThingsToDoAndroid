package com.android.hamdy.dss_project.Controllers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.hamdy.dss_project.Models.ThingModel;
import com.android.hamdy.dss_project.R;
import com.android.hamdy.dss_project.ThingsApp;
import com.android.hamdy.dss_project.User;
import com.android.hamdy.dss_project.Utilities.ResponseParserUtils;
import com.android.hamdy.dss_project.Views.Activities.HomeActivity;
import com.android.hamdy.dss_project.Views.Adapters.ThingsListAdapter;
import com.android.volley.VolleyError;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Vendor.WebService.IResponseHandler;

/**
 * Created by AppleXIcon on 20/4/2017.
 */

public class ThingsListController implements IResponseHandler {

    private  final String TAG = "ChildrenListController";
    private final User user;
    private final Context context;

    private HomeActivity mHomeActivity;

    private ThingsListAdapter mThingsListAdapter;
    private ArrayList<ThingModel> things;

    public ThingsListController(HomeActivity homeActivity) {
        this.mHomeActivity = homeActivity;


         context = mHomeActivity.getApplicationContext();
         user = ((ThingsApp) mHomeActivity.getApplication()).user;


        mHomeActivity.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.logout(mHomeActivity);
            }
        });

        mHomeActivity.showProgress(true);
        user.getThingsListfromUrl(context , this);

    }

    @Override
    public void onResponseSuccess(String response) {

        Log.e(TAG,response);

        try {
            ResponseParserUtils parser = new ResponseParserUtils(response);

            if(parser.result){

                things = parseToThings(parser.data);

                mThingsListAdapter = new ThingsListAdapter(mHomeActivity, things);
                mHomeActivity.mRecyclerView.setAdapter(mThingsListAdapter);

            }else {
                Toast.makeText(mHomeActivity, mHomeActivity.getString(R.string.error_occured), Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mHomeActivity.showProgress(false);
    }

    private ArrayList<ThingModel> parseToThings(JSONObject data) {

        ArrayList<ThingModel> things = new ArrayList<>();

        try {

            String KEY_THING_ID = "id";
             String KEY_THING_NAME = "thing_name";
            String KEY_THING_DESC = "desc";
            String KEY_THING_DATE = "date";

            JSONArray list = data.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {

                ThingModel model = new ThingModel();

                JSONObject jo = list.getJSONObject(i);
                model.id = jo.getString(KEY_THING_ID);
                model.name  = jo.getString(KEY_THING_NAME);
                model.desc = jo.getString(KEY_THING_DESC);
                model.date = jo.getString(KEY_THING_DATE);

                things.add(model);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ChildrenLists",e.getMessage()+"");
        }
        finally {
            return things;
        }
    }

    @Override
    public void onResponseFailed(VolleyError error) {
        Log.e(TAG,"Error"+error.toString());
        mHomeActivity.showProgress(false);

    }
}
