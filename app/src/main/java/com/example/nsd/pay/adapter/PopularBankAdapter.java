package com.example.nsd.pay.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nsd.pay.BaseApp;
import com.example.nsd.pay.R;
import com.example.nsd.pay.activity.ChooseTransferTypeActivity;

import java.util.List;

public class PopularBankAdapter extends RecyclerView.Adapter<PopularBankAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<String> modelArrayList;

    public PopularBankAdapter(Context context, Activity activity, List<String> modelArrayList) {
        this.context = context;
        this.activity = activity;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_popular_bank_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_user_name.setText(modelArrayList.get(position));

        Glide.with(context).load(R.drawable.ic_bank).
                into(holder.civ_img);

    /*    if (modelArrayList.get(position).image.equalsIgnoreCase("")) {
            Glide.with(context).load(R.drawable.ic_launcher_background).
                    into(holder.civ_img);
        } else {
            Glide.with(context)
                    .load(BASE_URL + modelArrayList.get(position).image)
                    .into(holder.civ_img);
        }*/

        holder.ll_latest_user.setOnClickListener(v -> {
            BaseApp.getInstance().toastHelper().showSnackBar(activity.findViewById(R.id.ll_parant), "Work under development !!", true);

            context.startActivity(new Intent(context, ChooseTransferTypeActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView civ_img;
        private TextView tv_user_name;
        private LinearLayout ll_latest_user;

        public ViewHolder(View itemView) {
            super(itemView);
            civ_img = itemView.findViewById(R.id.civ_img);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            ll_latest_user = itemView.findViewById(R.id.ll_latest_user);
        }
    }
}