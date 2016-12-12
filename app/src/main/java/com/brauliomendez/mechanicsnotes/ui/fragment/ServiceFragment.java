package com.brauliomendez.mechanicsnotes.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;
import com.brauliomendez.mechanicsnotes.ui.adapter.ServiceAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import mx.leo.easyrecycler.util.RecyclerViewItemClickListener;
import mx.leo.easyrecycler.util.extensions.RecyclerViewExtensionsKt;

/**
 * @author Braulio Méndez Jiménez
 * @since 28/06/16
 */
public class ServiceFragment extends Fragment {

    @Bind(R.id.main_recycler) RecyclerView recyclerView;

    private Realm realm;
    private RealmResults<Service> services;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        realm = Realm.getDefaultInstance();
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        final ServiceAdapter serviceAdapter = new ServiceAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(serviceAdapter);
        services = realm.where(Service.class).findAll();
        for (Service service : services) {
            serviceAdapter.addItem(service);
        }
        RecyclerViewExtensionsKt.OnItemClickListener(recyclerView,
                new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, Integer integer) {
                Service service = serviceAdapter.getItems().get(integer);
                showInfoDialog(service.getService(), serviceAdapter, integer,
                        service.getNameOwner(), service.getCar(), service.getMileage(), service.getYear(),
                        service.getService(), service.getTotalPrice());
            }
        });
    }

    public void showInfoDialog(final String service, final ServiceAdapter serviceAdapter, final int position,
                               final String nameOwner, final String car, final String mileage, final String year, String carService,
                               final String price) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(service);
        dialog.setMessage("¿Que deseas hacer?");
        dialog.setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                realm.beginTransaction();
                services.remove(position);
                serviceAdapter.deleteItem(position);
                realm.commitTransaction();
            }
        });
        dialog.setNegativeButton("EDITAR", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                Bundle bundle = new Bundle();
                bundle.putString("name_owner_car", nameOwner);
                bundle.putString("car", car);
                bundle.putString("mileage", mileage);
                bundle.putString("year", year);
                bundle.putString("service", service);
                bundle.putString("price", price);
                EditServiceFragment editServiceFragment = new EditServiceFragment();
                editServiceFragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_fragment, editServiceFragment)
                        .commit();
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        dialog.show();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @OnClick(R.id.fab) public void setService() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, new DetailFragment())
                .addToBackStack("detail_fragment")
                .commit();
    }
}
