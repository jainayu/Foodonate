package com.example.root.foodonate;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DonorList {

    private static DonorList DonorList;
    private List<DonorItem> DItems;

    private DonorList(Context context){
        DItems = new ArrayList<>();
        for (int i=0; i<100; i++ ){
            DonorItem dItem = new DonorItem();

            DItems.add(dItem);
        }
    }

    public List<DonorItem> getDItems(){
        return DItems;
    }

    public static DonorList get(Context context){
        if(DonorList == null){
            DonorList = new DonorList(context);
        }
        return DonorList;
    }


}
