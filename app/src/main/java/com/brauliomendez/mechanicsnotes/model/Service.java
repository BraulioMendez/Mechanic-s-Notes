package com.brauliomendez.mechanicsnotes.model;

import org.parceler.Parcel;

/**
 * Created by Braulio on 28/06/2016.
 */
@Parcel
public class Service {

    private String picture;
    private String nameOwner;
    private String car;
    private String mileage;
    private String year;
    private String service;
    private String totalPrice;
    private String date;

    public Service() { }

    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }

    public String getNameOwner() { return nameOwner; }

    public void setNameOwner(String nameOwner) { this.nameOwner = nameOwner; }

    public String getCar() { return car; }

    public void setCar(String car) { this.car = car; }

    public String getService() { return service; }

    public String getMileage() { return mileage; }

    public void setMileage(String mileage) { this.mileage = mileage; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public void setService(String service) { this.service = service; }

    public String getTotalPrice() { return totalPrice; }

    public void setTotalPrice(String totalPrice) { this.totalPrice = totalPrice; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}
