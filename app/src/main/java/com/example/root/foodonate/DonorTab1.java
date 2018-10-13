package com.example.root.foodonate;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    TextView nos;
    TextView location;
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_donor, container, false);
        nos = rootView.findViewById(R.id.nos);
        location = rootView.findViewById(R.id.location);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("donor1");

        ValueEventListener pl = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {


                DonorItem donorItem = ds.getValue(DonorItem.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        return rootView;
    }
}
