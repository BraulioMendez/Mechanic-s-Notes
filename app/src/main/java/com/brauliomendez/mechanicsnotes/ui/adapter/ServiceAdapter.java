package com.brauliomendez.mechanicsnotes.ui.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;
import com.firebase.client.Query;

import java.util.ArrayList;

/**
 * Created by Braulio on 29/06/2016.
 */
public class ServiceAdapter extends FirebaseRecyclerAdapter<ServiceViewHolder, Service> {

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
        Service item = getItem(position);
        holder.nameOwnerTextView.setText("Nombre del cliente : " + item.getNameOwner());
        holder.nameCarTextView.setText("Automovil : " + item.getCar());
        holder.milegeCarTextView.setText("Kilometraje : " + item.getMileage());
        holder.yearCarTextView.setText("Año : " + item.getYear());
        holder.serviceCarTextView.setText("Servicio : " +item.getService());
        holder.totalPriceTextView.setText("Precio : $" + item.getTotalPrice());
    }

    @Override protected void itemAdded(Service item, String key, int position) {

    }

    @Override protected void itemChanged(Service oldItem, Service newItem, String key, int position) {

    }

    @Override protected void itemRemoved(Service item, String key, int position) {

    }

    @Override protected void itemMoved(Service item, String key, int oldPosition, int newPosition) {

    }
}
