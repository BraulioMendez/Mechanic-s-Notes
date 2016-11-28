package com.brauliomendez.mechanicsnotes.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;

import com.brauliomendez.mechanicsnotes.R;

import java.util.regex.Pattern;

/**
 * Created by Braulio on 01/08/2016.
 */
public class ValidationTextUtils {

    private static boolean isValidateName(Context context, String nameOwner, TextInputLayout mNameTextInput) {
        Pattern patternName = Pattern.compile(context.getResources().getString(R.string.validation_name));
        if (!patternName.matcher(nameOwner).matches()) {
            mNameTextInput.setError(context.getResources().getString(R.string.name_error));
            return false;
        } else {
            mNameTextInput.setError(null);
        }
        return true;
    }

    private static boolean isValidateNameCar(Context context, String car, TextInputLayout  mCarTextInput) {
        Pattern patternNameCar = Pattern.compile(context.getResources().getString(R.string.validation_name_car));
        if (!patternNameCar.matcher(car).matches()) {
            mCarTextInput.setError(context.getResources().getString(R.string.name_car_error));
            return false;
        } else {
            mCarTextInput.setError(null);
        }
        return true;
    }

    private static boolean isValidateMileage(Context context, String mileageCar, TextInputLayout mMileageTextInput) {
        Pattern patternPrice = Pattern.compile(context.getResources().getString(R.string.validation_mileage));
        if (!patternPrice.matcher(mileageCar).matches()) {
            mMileageTextInput.setError(context.getResources().getString(R.string.mileage_error));
            return false;
        } else {
            mMileageTextInput.setError(null);
        }
        return true;
    }

    private static boolean isValidateYear(Context context, String yearCar, TextInputLayout mYearCarTextInput) {
        Pattern patternYear = Pattern.compile(context.getResources().getString(R.string.validation_year));
        if (!patternYear.matcher(yearCar).matches()) {
            mYearCarTextInput.setError(context.getResources().getString(R.string.year_car_error));
            return false;
        } else {
            mYearCarTextInput.setError(null);
        }
        return true;
    }

    private static boolean isValidateService(Context context,String carService, TextInputLayout mServiceTextInput) {
        Pattern patternService = Pattern.compile(context.getResources().getString(R.string.validation_service));
        if (!patternService.matcher(carService).matches()) {
            mServiceTextInput.setError(context.getResources().getString(R.string.service_error));
            return false;
        } else {
            mServiceTextInput.setError(null);
        }
        return true;
    }

    private static boolean isValidatePrice(Context context, String totalPrice,  TextInputLayout mPriceTextInput) {
        Pattern patternPrice = Pattern.compile(context.getResources().getString(R.string.validation_price));
        if (!patternPrice.matcher(totalPrice).matches()) {
            mPriceTextInput.setError(context.getResources().getString(R.string.price_error));
            return false;
        } else {
            mPriceTextInput.setError(null);
        }
        return true;
    }

    public static boolean validateForm(Context context, String nameOwner, String car, String mileageCar,  String yearCar,
                                        String carService, String totalPrice, TextInputLayout mNameTextInput,
                                       TextInputLayout mCarTextInput, TextInputLayout  mMileageTextInput,
                                       TextInputLayout  mYearCarTextInput, TextInputLayout mServiceTextInput,
                                       TextInputLayout mPriceTextInput){
        boolean validateName = ValidationTextUtils.isValidateName(context, nameOwner, mNameTextInput);
        boolean validateCar = isValidateNameCar(context, car, mCarTextInput);
        boolean validateMileage = isValidateMileage(context, mileageCar, mMileageTextInput);
        boolean validateYear = isValidateYear(context, yearCar, mYearCarTextInput);
        boolean validateService = isValidateService(context, carService, mServiceTextInput);
        boolean validatePrice = isValidatePrice(context, totalPrice, mPriceTextInput);

        if (validateName && validateCar && validateMileage && validateYear && validateService && validatePrice){
            return true;
        }
        else {
            return false;
        }
    }

}
