package com.brauliomendez.mechanicsnotes.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.brauliomendez.mechanicsnotes.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Braulio on 28/11/2016.
 */

public class ServiceViewHolder extends RecyclerView.ViewHolder {

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
}
