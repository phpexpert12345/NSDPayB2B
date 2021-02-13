package com.example.nsd.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nsd.pay.R;
import com.example.nsd.pay.interfaces.ContactInterface;
import com.example.nsd.pay.interfaces.OnAccountTypeInterface;
import com.example.nsd.pay.model.AllListData;

import java.util.List;

public class AccountTypeListAdapter extends RecyclerView.Adapter<AccountTypeListAdapter.ViewHolder> {

    private Context context;
    private List<AllListData> modelArrayList;
    private OnAccountTypeInterface onAccountTypeInterface;

    public AccountTypeListAdapter(Context context, List<AllListData> modelArrayList, OnAccountTypeInterface onAccountTypeInterface) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.onAccountTypeInterface = onAccountTypeInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_account_type_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_time_interval.setText(modelArrayList.get(position).name);

        holder.rd_time_interval_select.setOnClickListener(view -> onAccountTypeInterface.onAccountTypeItem(modelArrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_time_interval;
        private RadioButton rd_time_interval_select;
        private LinearLayout ll_interval_select;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_time_interval = itemView.findViewById(R.id.tv_time_interval);
            rd_time_interval_select = itemView.findViewById(R.id.rd_time_interval_select);
            ll_interval_select = itemView.findViewById(R.id.ll_interval_select);
        }
    }
}