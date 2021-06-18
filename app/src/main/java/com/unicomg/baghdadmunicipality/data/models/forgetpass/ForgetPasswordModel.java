package com.unicomg.baghdadmunicipality.data.models.forgetpass;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordModel {
    @SerializedName("email")
    String email  ;

    public ForgetPasswordModel(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ForgetPasswordModel{" +
                "email='" + email + '\'' +
                '}';
    }
}
