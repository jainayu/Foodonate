package com.example.root.foodonate;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DonorList {

    private static DonorList DonorList;
    private List<DonorItem> DItems;

    private DonorList(Context context, DatabaseReference myref){
        DItems = new ArrayList<>();
        /*for (int i=0; i<100; i++ ){
            DonorItem dItem = new DonorItem();

            DItems.add(dItem);
        }*/

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    DonorItem newItem = child.getValue(DonorItem.class);
                    DItems.add(newItem);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<DonorItem> getDItems(){
        return DItems;
    }

    public static DonorList get(Context context, DatabaseReference myref){
        if(DonorList == null){
            DonorList = new DonorList(context, myref);
        }
        return DonorList;

    }

}
