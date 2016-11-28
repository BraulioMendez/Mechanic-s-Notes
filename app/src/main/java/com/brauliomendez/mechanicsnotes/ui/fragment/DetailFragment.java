package com.brauliomendez.mechanicsnotes.ui.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.brauliomendez.mechanicsnotes.R;
import com.brauliomendez.mechanicsnotes.model.Service;
import com.firebase.client.Firebase;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Braulio on 28/06/2016.
 */
public class DetailFragment extends Fragment {

    @Bind(R.id.calendar_label) TextView calendarTextView;
    @Bind(R.id.name_textInput) TextInputLayout nameTextInput;
    @Bind(R.id.car_textInput) TextInputLayout carTextInput;
    @Bind(R.id.service_textInput) TextInputLayout serviceTextInput;
    @Bind(R.id.price_textInput) TextInputLayout priceTextInput;
    @Bind(R.id.mileage_textInput) TextInputLayout mileageTextInput;
    @Bind(R.id.year_car_textInput) TextInputLayout yearCarTextInput;

    private Firebase firebase;
    private int year, month, day;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.calendar_label) public void setDate() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        calendarTextView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.fab) public void setService() {
        firebase = new Firebase(getResources().getString(R.string.firebase_url));
        Service service = new Service(nameTextInput.getEditText().getText().toString().trim(),
                carTextInput.getEditText().getText().toString().trim(),
                mileageTextInput.getEditText().getText().toString().trim(),
                yearCarTextInput.getEditText().getText().toString().trim(),
                serviceTextInput.getEditText().getText().toString().trim(),
                priceTextInput.getEditText().getText().toString().trim());
        firebase.push().setValue(service);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, new ServiceFragment())
                .commit();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}



