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
import com.example.nsd.pay.interfaces.AllCategoryInterface;
import com.example.nsd.pay.model.AllListData;

import java.util.List;


public class ProfileProgressAdapter extends RecyclerView.Adapter<ProfileProgressAdapter.ViewHolder> {

    private Context context;
    private List<AllListData> modelArrayList;
    private AllCategoryInterface allCategoryInterface;

    public ProfileProgressAdapter(Context context, List<AllListData> modelArrayList, AllCategoryInterface allCategoryInterface) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.allCategoryInterface = allCategoryInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile_progress_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_doc_typ.setText(modelArrayList.get(position).name);
        holder.tv_doc_description.setText(modelArrayList.get(position).description);
        holder.tv_action.setText(modelArrayList.get(position).type);

        holder.img_icon.setImageResource(modelArrayList.get(position).drawable);

   /*     if (modelArrayList.get(position).image.equalsIgnoreCase("")) {
            holder.img_icon.setImageResource(R.drawable.ic_correct);
        } else {
            Glide.with(context)
                    .load(BASE_URL + modelArrayList.get(position).image)
                    .into(holder.img_icon);
        }*/

        holder.ll_parant.setOnClickListener(v -> {
            allCategoryInterface.onClickCategoryItem(modelArrayList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_icon;
        private TextView tv_doc_typ, tv_doc_description, tv_action;
        private LinearLayout ll_parant;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_doc_typ = itemView.findViewById(R.id.tv_doc_typ);
            tv_doc_description = itemView.findViewById(R.id.tv_doc_description);
            tv_action = itemView.findViewById(R.id.tv_action);
            img_icon = itemView.findViewById(R.id.img_icon);
            ll_parant = itemView.findViewById(R.id.ll_parant);
        }
    }
}