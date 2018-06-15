package com.wbmd.appindexingpoc.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Profile implements Parcelable {

    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("specialty")
    @Expose
    private String specialty;
    @SerializedName("address_top")
    @Expose
    private String addressTop;
    @SerializedName("address_bottom")
    @Expose
    private String addressBottom;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("location_photo")
    @Expose
    private String locationPhoto;

    public Profile() {
    }

    protected Profile(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        specialty = in.readString();
        addressTop = in.readString();
        addressBottom = in.readString();
        locationPhoto = in.readString();
        logo = in.readString();
        photo = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAddress() {
        return addressTop;
    }

    public void setAddress(String address) {
        this.addressTop = address;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(specialty);
        dest.writeString(addressTop);
        dest.writeString(addressBottom);
        dest.writeString(locationPhoto);
        dest.writeString(logo);
        dest.writeString(photo);
    }

    public String getAddressBottom() {
        return addressBottom;
    }

    public void setAddressBottom(String addressBottom) {
        this.addressBottom = addressBottom;
    }

    public String getLocationPhoto() {
        return locationPhoto;
    }

    public void setLocationPhoto(String locationPhoto) {
        this.locationPhoto = locationPhoto;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
