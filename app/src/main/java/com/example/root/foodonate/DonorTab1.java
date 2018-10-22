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

import java.util.List;

public class DonorTab1 extends Fragment {

    RecyclerView drv;
    View rootView;

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_donor, container, false);
        drv = (RecyclerView) rootView.findViewById(R.id.drv);
        drv.setLayoutManager(new GridLayoutManager(getActivity(),2));

        DonorList dList = DonorList.get(getActivity());
        List<DonorItem> dItems = dList.getDItems();
        MyAdaptor adaptor = new MyAdaptor(dItems);
        drv.setAdapter(adaptor);

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
