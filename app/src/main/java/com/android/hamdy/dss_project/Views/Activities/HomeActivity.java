package com.android.hamdy.dss_project.Views.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.hamdy.dss_project.Controllers.ThingsListController;
import com.android.hamdy.dss_project.R;

public class HomeActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    private LinearLayout container;
    private RelativeLayout relative;
    private ProgressBar progress;
    public ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        container  = (LinearLayout)findViewById(R.id.container_ll);
        logout  = (ImageView)findViewById(R.id.logout);
        progress  = (ProgressBar)findViewById(R.id.progress);
        relative  = (RelativeLayout)findViewById(R.id.relative);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new ThingsListController(this);


    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            container =  (LinearLayout)findViewById(R.id.activity_login_container_ll);


            relative.setVisibility(show ? View.GONE : View.VISIBLE);
            relative.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    relative.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progress.setVisibility(show ? View.VISIBLE : View.GONE);
            progress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progress.setVisibility(show ? View.VISIBLE : View.GONE);
            progress.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    public static void start(Context context) {

        Intent i = new Intent(context, HomeActivity.class);
        context.startActivity(i);
        ((AppCompatActivity) context).finish();
    }
}
