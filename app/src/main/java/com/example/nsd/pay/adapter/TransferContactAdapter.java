package com.example.nsd.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nsd.pay.R;
import com.example.nsd.pay.interfaces.ContactInterface;
import com.example.nsd.pay.model.AllListData;

import java.util.List;

public class TransferContactAdapter extends RecyclerView.Adapter<TransferContactAdapter.ViewHolder> {

    private Context context;
    private List<AllListData> modelArrayList;
    private ContactInterface contactInterface;

    public TransferContactAdapter(Context context, ContactInterface contactInterface) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.contactInterface = contactInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_transfer_contact_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.ll_parant.setOnClickListener(view -> contactInterface.onClickContactItem("",""));

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_icon;
        private TextView tv_ssv_name, tv_description;
        private LinearLayout ll_parant;

        public ViewHolder(View itemView) {
            super(itemView);/*
            tv_ssv_name = itemView.findViewById(R.id.tv_ssv_name);
            img_icon = itemView.findViewById(R.id.img_icon);
            tv_description = itemView.findViewById(R.id.tv_description);*/
            ll_parant = itemView.findViewById(R.id.ll_parant);
        }
    }
}