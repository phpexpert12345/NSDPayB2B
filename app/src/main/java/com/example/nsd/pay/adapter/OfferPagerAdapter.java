package com.example.nsd.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.nsd.pay.R;
import com.example.nsd.pay.model.PInfo;

import java.util.ArrayList;

public class OfferPagerAdapter extends PagerAdapter {
    // Declare Variables
    private Context context;
    private ArrayList<PInfo> simage;
    private LayoutInflater inflater;

    public OfferPagerAdapter(Context context, ArrayList<PInfo> simage) {
        this.context = context;
        this.simage = simage;
    }

    @Override
    public int getCount() {
        return simage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        // Declare Variables
        ImageView image_pager;
        TextView text1, text2;
        View itemView = LayoutInflater.from(context).inflate(R.layout.offer_pager_adapter, container, false);

        image_pager = itemView.findViewById(R.id.icon);
        text1 = itemView.findViewById(R.id.tv_text1);
        text2 = itemView.findViewById(R.id.tv_text2);
/*
        Glide.with(context).load(BASE_URL + simage.get(position).image).
                into(image_pager);*/
        text1.setText(simage.get(position).pname);
        text2.setText(simage.get(position).appname);
        image_pager.setImageResource(simage.get(position).versionCode);
        // add viewpager_item.xml to ViewPager
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
