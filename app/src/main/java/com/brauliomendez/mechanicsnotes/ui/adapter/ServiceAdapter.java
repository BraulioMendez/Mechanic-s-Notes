package com.brauliomendez.mechanicsnotes.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;
import com.bumptech.glide.Glide;
import com.firebase.client.Query;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Braulio on 29/06/2016.
 */
public class ServiceAdapter extends FirebaseRecyclerAdapter<ServiceAdapter.ServiceViewHolder, Service> {

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name_owner_label) TextView mNameOwnerLabel;
        @Bind(R.id.name_car_label) TextView mNameCarLabel;
        @Bind(R.id.milege_car_text) TextView mMilegeCarText;
        @Bind(R.id.year_car_text) TextView mYearCarText;
        @Bind(R.id.service_car_text) TextView mServiceCarText;
        @Bind(R.id.total_price_text) TextView mTotalPriceText;
        @Bind(R.id.share_button) Button mShareButton;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ServiceAdapter(Query query, Class<Service> itemClass, @Nullable ArrayList<Service> items,
                          @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }


    @Override public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_service, parent, false);
        return new ServiceViewHolder(v);
    }


    @Override public void onBindViewHolder(ServiceViewHolder holder, int position) {
        Service service = getItem(position);


        holder.mNameOwnerLabel.setText(service.getNameOwner());
        holder.mNameCarLabel.setText(service.getCar());
        holder.mMilegeCarText.setText(service.getMileage());
        holder.mYearCarText.setText(service.getYear());
        holder.mServiceCarText.setText( service.getService());
        holder.mTotalPriceText.setText( service.getTotalPrice());

        shareInfoService(holder, service.getNameOwner(), service.getNameOwner() + "\n" + service.getCar() +
                "\n" + service.getMileage() + "\n" + service.getYear()  + "\n" + service.getService() +
                "\n" + service.getTotalPrice());
    }

    private void shareInfoService(ServiceViewHolder holder, final String mail, final String data) {

        holder.mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] { mail });
                email.putExtra(Intent.EXTRA_SUBJECT, "Cuenta");
                email.putExtra(Intent.EXTRA_TEXT, data);
                email.setType("text/plain");

                view.getContext().startActivity(Intent.createChooser(email, "Choose an Email client"));
            }
        });
    }

    @Override protected void itemAdded(Service item, String key, int position) {
        Log.d("MyAdapter", "Added a new item to the adapter.");
    }

    @Override protected void itemChanged(Service oldItem, Service newItem, String key, int position) {
        Log.d("MyAdapter", "Changed an item.");
    }

    @Override protected void itemRemoved(Service item, String key, int position) {
        Log.d("MyAdapter", "Removed an item from the adapter.");
    }

    @Override protected void itemMoved(Service item, String key, int oldPosition, int newPosition) {
        Log.d("MyAdapter", "Moved an item.");
    }

}
