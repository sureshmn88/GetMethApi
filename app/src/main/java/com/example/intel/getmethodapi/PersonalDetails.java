package com.example.intel.getmethodapi;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonalDetails implements Parcelable {
    String id,name,city,state,country,email,image;

    protected PersonalDetails(Parcel in) {
        id = in.readString();
        name = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
        email = in.readString();
        image = in.readString();
    }


    public PersonalDetails(){

    }

    public static final Creator<PersonalDetails> CREATOR = new Creator<PersonalDetails>() {
        @Override
        public PersonalDetails createFromParcel(Parcel in) {
            return new PersonalDetails(in);
        }

        @Override
        public PersonalDetails[] newArray(int size) {
            return new PersonalDetails[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
        dest.writeString(email);
        dest.writeString(image);
    }
}
