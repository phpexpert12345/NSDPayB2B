package com.example.nsd.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.nsd.pay.R;
import com.example.nsd.pay.model.PInfo;

import java.util.List;

public class MainOfferPagerAdapter extends PagerAdapter {
    // Declare Variables
    private Context context;
    private List<PInfo> simage;
    private LayoutInflater inflater;

    public MainOfferPagerAdapter(Context context, List<PInfo> simage) {
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
        View itemView = LayoutInflater.from(context).inflate(R.layout.main_offer_pager_adapter, container, false);

        image_pager = itemView.findViewById(R.id.im);
        image_pager.setImageResource(simage.get(position).versionCode);
      /*  Picasso.get()
                .load(BASEURL.ImagePath + simage.get(position)
                        .getImage())
                .error(context.getResources().getDrawable(R.drawable.image_not_available))
                .into(image_pager);*/
        // add viewpager_item.xml to ViewPager

        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
