package com.android.hamdy.dss_project.Views.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hamdy.dss_project.Models.ThingModel;
import com.android.hamdy.dss_project.R;

import java.util.ArrayList;


/**
 * Created by AppleXIcon on 20/4/2017.
 */

public class ThingsListAdapter extends RecyclerView.Adapter<ThingsListAdapter.ChildViewHolder> {

    private Context mContext;
    private ArrayList<ThingModel> thingArrayList;

    public ThingsListAdapter(Context context, ArrayList<ThingModel> thingArrayList) {

        this.thingArrayList = thingArrayList;
        this.mContext = context;
    }

    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.thing_list_row, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChildViewHolder holder, final int position) {

        final ThingModel thing = thingArrayList.get(position);

        holder.thingNameTV.setText(thing.name);
        holder.thingDescTV.setText(thing.desc);
        holder.thingDateTV.setText(thing.date);

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBtn(position);
            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBtn(position);
            }
        });

    }

    private void deleteBtn(int position) {


    }

    private void editBtn(int position) {

    }


    @Override
    public int getItemCount() {
        return thingArrayList.size();
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView thingNameTV, thingDescTV, thingDateTV;

        ImageView deleteBtn, editBtn;

        public ChildViewHolder(View itemView) {
            super(itemView);
            thingNameTV = (TextView) itemView.findViewById(R.id.thing_name_tv);
            thingDescTV = (TextView) itemView.findViewById(R.id.thing_description_tv);
            thingDateTV = (TextView) itemView.findViewById(R.id.thing_date_tv);
            deleteBtn = (ImageView) itemView.findViewById(R.id.thing_delete_iv);
            editBtn = (ImageView) itemView.findViewById(R.id.thing_edit_iv);
        }
    }
}
