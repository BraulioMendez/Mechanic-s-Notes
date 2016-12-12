package com.brauliomendez.mechanicsnotes.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;

import butterknife.Bind;
import butterknife.ButterKnife;
import mx.leo.easyrecycler.viewholder.EasyItemViewHolder;

/**
 * @author Braulio Méndez Jiménez
 * @since 28/11/16
 */
public class ServiceViewHolder extends EasyItemViewHolder {

    @Bind(R.id.name_owner_label) TextView nameOwnerTextView;
    @Bind(R.id.name_car_label) TextView nameCarTextView;
    @Bind(R.id.milege_car_text) TextView milegeCarTextView;
    @Bind(R.id.year_car_text) TextView yearCarTextView;
    @Bind(R.id.service_car_text) TextView serviceCarTextView;
    @Bind(R.id.total_price_text) TextView totalPriceTextView;

    public ServiceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindItem(Service item){
        nameOwnerTextView.setText("Nombre del cliente : " + item.getNameOwner());
        nameCarTextView.setText("Automovil : " + item.getCar());
        milegeCarTextView.setText("Kilometraje : " + item.getMileage());
        yearCarTextView.setText("Año : " + item.getYear());
        serviceCarTextView.setText("Servicio : " +item.getService());
        totalPriceTextView.setText("Precio : $" + item.getTotalPrice());
    }
}
