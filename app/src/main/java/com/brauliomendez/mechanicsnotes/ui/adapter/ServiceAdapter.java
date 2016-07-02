package com.brauliomendez.mechanicsnotes.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Braulio on 29/06/2016.
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private Context context;
    private List<Service> mDataServices;

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name_owner_label) TextView mNameOwnerLabel;
        @Bind(R.id.name_car_label) TextView mNameCarLabel;
        @Bind(R.id.milege_car_text) TextView mMilegeCarText;
        @Bind(R.id.year_car_text) TextView mYearCarText;
        @Bind(R.id.service_car_text) TextView mServiceCarText;
        @Bind(R.id.total_price_text) TextView mTotalPriceText;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ServiceAdapter(Context context, List<Service> mDataServices) {
        this.context = context;
        this.mDataServices = mDataServices;
    }

    @Override public int getItemCount() {
        return mDataServices != null ? mDataServices.size():0;
    }

    @Override public ServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_service, parent, false);
        return new ServiceViewHolder(v);
    }


    @Override public void onBindViewHolder(ServiceViewHolder holder, int position) {
        Service service = mDataServices.get(position);

        holder.mNameOwnerLabel.setText(service.getNameOwner());
        holder.mNameCarLabel.setText(service.getCar());
        holder.mMilegeCarText.setText(service.getMileage());
        holder.mYearCarText.setText(service.getYear());
        holder.mServiceCarText.setText(service.getService());
        holder.mTotalPriceText.setText(service.getTotalPrice());

    }

}
