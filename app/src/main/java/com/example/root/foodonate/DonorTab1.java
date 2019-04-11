package com.example.root.foodonate;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DonorTab1 extends Fragment {

    RecyclerView donorRecyclerView;
    View rootView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference("Donor");
    private List<DonorItem> DItems= new ArrayList<>();
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_donor, container, false);
        donorRecyclerView = (RecyclerView) rootView.findViewById(R.id.drv);
        donorRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));


        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    DonorItem newItem = child.getValue(DonorItem.class);
                    DItems.add(newItem);
/*
                    System.out.println("data : "+newItem);
*/
                    MyAdaptor adaptor = new MyAdaptor(DItems);
                    donorRecyclerView.setAdapter(adaptor);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return rootView;
    }

   

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView Dnos;
        public TextView Dloc;
        public DonorItem DItem;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.donor_item, parent, false));
            Dnos = (TextView) itemView.findViewById(R.id.nos);
            Dloc = (TextView) itemView.findViewById(R.id.loc);
        }

        public void bind(DonorItem dItem) {
            DItem = dItem;
            Dnos.setText(DItem.getDnos());
            Dloc.setText(DItem.getDlocation());

        }
    }

    public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder>{

        private List<DonorItem> DItem;

        public MyAdaptor(List<DonorItem> dItems){
            DItem = dItems;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

            DonorItem dItem = DItem.get(position);
            holder.bind(dItem);
        }


        @Override
        public int getItemCount(){
            return DItem.size();
        }

    }
}
