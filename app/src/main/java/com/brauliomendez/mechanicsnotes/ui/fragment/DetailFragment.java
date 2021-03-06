package com.brauliomendez.mechanicsnotes.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * @author Braulio Méndez Jiménez
 * @since 28/06/16
 */
public class DetailFragment extends Fragment {

    @Bind(R.id.name_editText) TextInputEditText nameEditText;
    @Bind(R.id.car_editText) TextInputEditText carEditText;
    @Bind(R.id.mileage_editText) TextInputEditText mileageEditText;
    @Bind(R.id.year_car_editText) TextInputEditText yearCarEditText;
    @Bind(R.id.service_editText) TextInputEditText serviceEditText;
    @Bind(R.id.price_editText) TextInputEditText priceEditText;

    private Realm realm;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    private void setNewService() {
        realm.beginTransaction();
        Service service = realm.createObject(Service.class);
        service.setId(mileageEditText.getText().toString());
        service.setNameOwner(nameEditText.getText().toString());
        service.setService(serviceEditText.getText().toString());
        service.setCar(carEditText.getText().toString());
        service.setYear(yearCarEditText.getText().toString());
        service.setMileage(mileageEditText.getText().toString());
        service.setTotalPrice(priceEditText.getText().toString());
        realm.commitTransaction();
    }

    @OnClick(R.id.fab) public void setService() {
        setNewService();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, new ServiceFragment())
                .commit();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}



