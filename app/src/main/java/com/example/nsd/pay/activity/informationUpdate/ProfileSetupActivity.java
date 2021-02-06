package com.example.nsd.pay.activity.informationUpdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.ProfileProgressAdapter;
import com.example.nsd.pay.interfaces.AllCategoryInterface;
import com.example.nsd.pay.model.AllListData;

import java.util.ArrayList;

public class ProfileSetupActivity extends AppCompatActivity implements AllCategoryInterface, View.OnClickListener {

    public ImageView img_back;
    public RecyclerView rv_profile_option;
    public RecyclerView.Adapter mAllCategory;
    public AllCategoryInterface allCategoryInterface;
    public ArrayList<AllListData> allCategoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        allCategoryInterface = this;

        setCategoryData();

        img_back = findViewById(R.id.img_back);
        rv_profile_option = findViewById(R.id.rv_profile_option);
        rv_profile_option.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        mAllCategory = new ProfileProgressAdapter(ProfileSetupActivity.this, allCategoryList, allCategoryInterface);
        rv_profile_option.setAdapter(mAllCategory);

        img_back.setOnClickListener(this);
    }

    private void setCategoryData() {
        AllListData list1 = new AllListData();
        list1.id = "1";
        list1.name = "PanCard";
        list1.description = "Help your friends find\nyou Easily";
        list1.type = "Upload";
        list1.drawable = R.mipmap.p_card_icon;
        allCategoryList.add(list1);

        AllListData list2 = new AllListData();
        list2.id = "2";
        list2.name = "Upload profile pic";
        list2.description = "Help your friends find\nyou Easily";
        list2.type = "Upload";
        list2.drawable = R.mipmap.user_profile_icon;
        allCategoryList.add(list2);

        AllListData list3 = new AllListData();
        list3.name = "Add Address";
        list3.id = "3";
        list3.description = "For Quick";
        list3.type = "Add";
        list3.drawable = R.mipmap.location_icon;
        allCategoryList.add(list3);

        AllListData list4 = new AllListData();
        list4.id = "4";
        list4.name = "Edit Profile";
        list4.description = "For Quick";
        list4.type = "Edit";
        list4.drawable = R.mipmap.edit_profile_icon;
        allCategoryList.add(list4);

        AllListData list5 = new AllListData();
        list5.id = "5";
        list5.name = "Aadhar Card";
        list5.description = "Help your friends find\nyou Easily";
        list5.type = "Upload";
        list5.drawable = R.mipmap.p_card_icon;
        allCategoryList.add(list5);

        AllListData list6 = new AllListData();
        list6.id = "6";
        list6.name = "ID Proof";
        list6.description = "Help your friends find\nyou Easily";
        list6.type = "Upload";
        list6.drawable = R.mipmap.p_card_icon;
        allCategoryList.add(list6);
    }

    @Override
    public void onClickCategoryItem(AllListData allListData) {
        if (allListData.id.equalsIgnoreCase("1")) {

        } else if (allListData.id.equalsIgnoreCase("2")) {
            startActivity(new Intent(ProfileSetupActivity.this, UploadProfilePicActivity.class));
        } else if (allListData.id.equalsIgnoreCase("3")) {

        } else if (allListData.id.equalsIgnoreCase("4")) {

        } else if (allListData.id.equalsIgnoreCase("5")) {

        } else if (allListData.id.equalsIgnoreCase("6")) {

        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}