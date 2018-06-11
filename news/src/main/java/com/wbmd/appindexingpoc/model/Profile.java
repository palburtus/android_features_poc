package com.wbmd.appindexingpoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Profile {

    @SerializedName("Name")
    @Expose
    private String fullName;
    @SerializedName("specialty")
    @Expose
    private String specialty;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("photo")
    @Expose
    private String photo;

    public Profile() {
    }

    public String getFirstName() {
        return fullName;
    }

    public void setFirstName(String firstName) {
        this.fullName = firstName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
