package com.example.nsd.pay.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nsd.pay.R;
import com.example.nsd.pay.model.PInfo;

import java.util.List;

public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.ViewHolder> {

    private Context context;
    private List<PInfo> modelArrayList;

    public AllItemsAdapter(Context context, List<PInfo> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recharge_items,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_item_name.setText(modelArrayList.get(position).pname);
        holder.img_icon.setImageResource(modelArrayList.get(position).versionCode);

        holder.ll_parant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, modelArrayList.get(position).pname, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_icon;
        private TextView tv_item_name;
        private LinearLayout ll_parant;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_item_name = itemView.findViewById(R.id.tv_item_name);
            img_icon = itemView.findViewById(R.id.img_icon);
            ll_parant = itemView.findViewById(R.id.ll_parant);
        }
    }
}