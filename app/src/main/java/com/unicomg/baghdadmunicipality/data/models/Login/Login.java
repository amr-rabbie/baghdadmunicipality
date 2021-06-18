
package com.unicomg.baghdadmunicipality.data.models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Login implements Serializable
{

    @SerializedName("info")
    @Expose
    private Info info;
    private final static long serialVersionUID = -4833529430688805529L;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
