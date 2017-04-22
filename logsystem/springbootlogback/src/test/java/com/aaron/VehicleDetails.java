package com.aaron;

/**
 * Created by Administrator on 2017/4/15.
 */
public class VehicleDetails {
    private String firstname;
    private String lastname;

    public VehicleDetails() {
    }

    public VehicleDetails(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
