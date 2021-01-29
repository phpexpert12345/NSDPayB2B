package com.example.nsd.pay.model;

import java.util.Comparator;
import java.util.List;

public class AllListData {
    public int drawable;
    public String id;

    public static Comparator<AllListData> sortByGrpId = (s1, s2) -> {
        int grp_id1 = Integer.parseInt(s1.id);
        int grp_id2 = Integer.parseInt(s2.id);
        return grp_id2 - grp_id1;
    };

    public String name;
    public String image;
    public String items;
    public String type;
    public String bgColor;

    public String description;
    public String finalPrice;
    public String offerPrice;
    public String offer;

    public List<AllListData> dashboardChildList;
    public List<AllListData> dashboardSubChildList;
}
