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
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Braulio on 28/06/2016.
 */
public class DetailFragment extends Fragment {

    @Bind(R.id.name_textInput) TextInputLayout mNameTextInput;
    @Bind(R.id.car_textInput) TextInputLayout mCarTextInput;
    @Bind(R.id.service_textInput) TextInputLayout mServiceTextInput;
    @Bind(R.id.price_textInput) TextInputLayout mPriceTextInput;
    @Bind(R.id.calendar_label) TextView mCalendarTextView;
    @Bind(R.id.mileage_textInput) TextInputLayout mMileageTextInput;
    @Bind(R.id.year_car_textInput) TextInputLayout mYearCarTextInput;

    private int mYear, mMonth, mDay;

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                                 @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private boolean isValidateName(String nameOwner) {
        Pattern patternName = Pattern.compile(getResources().getString(R.string.validation_name));
        if (!patternName.matcher(nameOwner).matches()) {
            mNameTextInput.setError(getResources().getString(R.string.name_error));
            return false;
        } else {
            mNameTextInput.setError(null);
        }
        return true;
    }

    private boolean isValidateNameCar(String car) {
        Pattern patternNameCar = Pattern.compile(getResources().getString(R.string.validation_name_car));
        if (!patternNameCar.matcher(car).matches()) {
            mCarTextInput.setError(getResources().getString(R.string.name_car_error));
            return false;
        } else {
            mCarTextInput.setError(null);
        }
        return true;
    }

    private boolean isValidateMileage(String mileageCar) {
        Pattern patternPrice = Pattern.compile(getResources().getString(R.string.validation_mileage));
        if (!patternPrice.matcher(mileageCar).matches()) {
            mMileageTextInput.setError(getResources().getString(R.string.mileage_error));
            return false;
        } else {
            mMileageTextInput.setError(null);
        }
        return true;
    }

    private boolean isValidateYear(String yearCar) {
        Pattern patternYear = Pattern.compile(getResources().getString(R.string.validation_year));
        if (!patternYear.matcher(yearCar).matches()) {
            mYearCarTextInput.setError(getResources().getString(R.string.year_car_error));
            return false;
        } else {
            mYearCarTextInput.setError(null);
        }
        return true;
    }

    private boolean isValidateService(String carService) {
        Pattern patternService = Pattern.compile(getResources().getString(R.string.validation_service));
        if (!patternService.matcher(carService).matches()) {
            mServiceTextInput.setError(getResources().getString(R.string.service_error));
            return false;
        } else {
            mServiceTextInput.setError(null);
        }
        return true;
    }

    private boolean isValidatePrice(String totalPrice) {
        Pattern patternPrice = Pattern.compile(getResources().getString(R.string.validation_price));
        if (!patternPrice.matcher(totalPrice).matches()) {
            mPriceTextInput.setError(getResources().getString(R.string.price_error));
            return false;
        } else {
            mPriceTextInput.setError(null);
        }
        return true;
    }

    @OnClick(R.id.calendar_label) public void setDate() {
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        mCalendarTextView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.fab) public void setService() {
        Firebase firebaseUrl = new Firebase(getResources().getString(R.string.firebase_url));

        String nameOwner = mNameTextInput.getEditText().getText().toString().trim();
        String car = mCarTextInput.getEditText().getText().toString().trim();
        String mileageCar = mMileageTextInput.getEditText().getText().toString().trim();
        String yearCar = mYearCarTextInput.getEditText().getText().toString().trim();
        String carService = mServiceTextInput.getEditText().getText().toString().trim();
        String totalPrice = mPriceTextInput.getEditText().getText().toString().trim();

        boolean validateName = isValidateName(nameOwner);
        boolean validateCar = isValidateNameCar(car);
        boolean validateMileage = isValidateMileage(mileageCar);
        boolean validateYear = isValidateYear(yearCar);
        boolean validateService = isValidateService(carService);
        boolean validatePrice = isValidatePrice(totalPrice);

        if (validateName && validateCar && validateMileage && validateYear && validateService
                && validatePrice) {

            Service service = new Service();
            service.setNameOwner(nameOwner);
            service.setCar(car);
            service.setMileage(mileageCar);
            service.setYear(yearCar);
            service.setService(carService);
            service.setTotalPrice(totalPrice);
            firebaseUrl.push().setValue(service);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, new ServiceFragment())
                    .commit();
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}



