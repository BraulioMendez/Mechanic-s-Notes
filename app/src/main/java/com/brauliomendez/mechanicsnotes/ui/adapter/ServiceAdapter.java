package com.brauliomendez.mechanicsnotes.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;

import org.jetbrains.annotations.NotNull;

import mx.leo.easyrecycler.adapter.EasyAdapter;

/**
 * @author Braulio Méndez Jiménez
 * @since 29/06/16
 */
public class ServiceAdapter extends EasyAdapter<ServiceViewHolder, Service> {

    @NotNull @Override public ServiceViewHolder createHolder(ViewGroup parent, int i) {
        return new ServiceViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_service, parent, false));
    }

    @Override public void onBindItemViewHolder(ServiceViewHolder holder, Service service, int i) {
        holder.bindItem(service);
    }
}
