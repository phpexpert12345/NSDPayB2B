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
import com.example.nsd.pay.interfaces.CashBackOfferInterface;
import com.example.nsd.pay.interfaces.DrawerInterface;
import com.example.nsd.pay.model.AllListData;

import java.util.List;

public class CashBackOfferAdapter extends RecyclerView.Adapter<CashBackOfferAdapter.ViewHolder> {

    private Context context;
    private List<AllListData> modelArrayList;
    private CashBackOfferInterface ssvDrawerInterface;

    public CashBackOfferAdapter(Context context, CashBackOfferInterface ssvDrawerInterface) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.ssvDrawerInterface = ssvDrawerInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cashback_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
       /* holder.tv_ssv_name.setText(modelArrayList.get(position).name);
        holder.tv_description.setText(modelArrayList.get(position).description);

        if (modelArrayList.get(position).icon.equalsIgnoreCase("")) {
            holder.img_icon.setImageResource(R.drawable.ic_correct);
        } else {
            Glide.with(context)
                    .load(BASE_URL + modelArrayList.get(position).icon)
                    .into(holder.img_icon);

        }

        holder.ll_dds_collection.setOnClickListener(v -> {
            ssvDrawerInterface.onClickDrawerItem(modelArrayList.get(position).name, String.valueOf(modelArrayList.get(position).id));
        });*/
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_icon;
        private TextView tv_ssv_name, tv_description;
        private LinearLayout ll_dds_collection;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_ssv_name = itemView.findViewById(R.id.tv_ssv_name);
            img_icon = itemView.findViewById(R.id.img_icon);
            tv_description = itemView.findViewById(R.id.tv_description);
            ll_dds_collection = itemView.findViewById(R.id.ll_dds_collection);
        }
    }
}