package com.android.hamdy.dss_project.Views.Fragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hamdy on 28/04/17.
 */

public class UserDialogFragment extends DialogFragment{

    public EditText fullNameET;
    public Button saveBtn;
    public EditText emailET;
    public EditText addressET;
    public View view;
    private TextView errorTV;
    //public Model model;
    public boolean edit;


//   public static UserDialogFragment newInstance(Model model){
//
//
//        UserDialogFragment userDialogFragment = new UserDialogFragment();
//
//        Bundle bundle = new Bundle();
//
//        if(model!= null) {
//            bundle.putBoolean("model" , true);
//            bundle.putString("id", model.id);
//            bundle.putString("name", model.name);
//            bundle.putString("email", model.email);
//            bundle.putString("address", model.address);
//        }
//        else
//            bundle.putBoolean("model" , false);
//
//
//        userDialogFragment.setArguments(bundle);
//
//        return userDialogFragment;
//
//    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Bundle args = getArguments();
//
//        if(args != null && args.getBoolean("model")){
//            model = new Model(getContext());
//
//            model.id = args.getString("id");
//            model.name = args.getString("name");
//            model.email = args.getString("email");
//            model.address = args.getString("address");
//        }
//    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//
//
//         view = inflater.inflate(R.layout.activity_main , container , false);
//
//        getDialog().setTitle("Create User");
//
//
//        DialogController controller = new DialogController(this);
//
//        initViews();
//
//        controller.actionListener();
//
//
//
//        return view;
//    }
//
//    public void initViews() {
//
//
//        fullNameET =  (EditText)view.findViewById(R.id.nameET);
//        emailET =  (EditText)view.findViewById(R.id.emailET);
//        addressET =  (EditText)view.findViewById(R.id.addressET);
//        saveBtn =  (Button) view.findViewById(R.id.saveBtn);
//
//        errorTV =  (TextView)view.findViewById(R.id.activity__create_account_error_tv);
//
//        if(model != null){
//            fullNameET.setText(model.name);
//           addressET.setText(model.address);
//           emailET.setText(model.email);
//
//            edit = true;
//
//            saveBtn.setText("Edit");
//        }
//
//    }


}
