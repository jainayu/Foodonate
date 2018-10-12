package com.example.root.foodonate;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonorTab1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.donor, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance("donor");
        DatabaseReference myRef = database.getReference();
        ValueEventListener pl = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        return rootView;
    }
}
