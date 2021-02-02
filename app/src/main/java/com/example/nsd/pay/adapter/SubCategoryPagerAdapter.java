package com.example.nsd.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.nsd.pay.R;
import com.example.nsd.pay.model.AllListData;
import com.example.nsd.pay.model.PInfo;

import java.util.List;

public class SubCategoryPagerAdapter extends PagerAdapter {
    // Declare Variables
    private Context context;
    private List<AllListData> simage;
    private LayoutInflater inflater;

    public SubCategoryPagerAdapter(Context context, List<AllListData> simage) {
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
        View itemView = LayoutInflater.from(context).inflate(R.layout.sub_category_pager_adapter, container, false);
        ((ViewPager) container).addView(itemView);

        RecyclerView rv_sub_category;
        rv_sub_category = itemView.findViewById(R.id.rv_sub_category);
        AllItemsAdapter allItemsAdapter = new AllItemsAdapter(context, simage.get(position).pInfoList);
        rv_sub_category.setHasFixedSize(true);
        rv_sub_category.setLayoutManager(new GridLayoutManager(context, 4));

        rv_sub_category.setAdapter(allItemsAdapter);
        rv_sub_category.setNestedScrollingEnabled(false);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
