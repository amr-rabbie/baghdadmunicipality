package com.unicomg.baghdadmunicipality.data.models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModel  {

    @SerializedName("id")
    String id;
    @SerializedName("first_name")
    String first_name;
    @SerializedName("last_name")
    String last_name;
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;
    @SerializedName("permission")
    String permission;
    @SerializedName("is_active")
    String is_active;

    @SerializedName("user_id_created")
    String user_id_created;
    @SerializedName("user_id_modified")
    String user_id_modified;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;


    public LoginModel() {

    }

    public LoginModel(String id, String first_name, String last_name, String username, String email, String permission, String is_active, String user_id_created, String user_id_modified, String created_at, String updated_at) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.permission = permission;
        this.is_active = is_active;
        this.user_id_created = user_id_created;
        this.user_id_modified = user_id_modified;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getUser_id_created() {
        return user_id_created;
    }

    public void setUser_id_created(String user_id_created) {
        this.user_id_created = user_id_created;
    }

    public String getUser_id_modified() {
        return user_id_modified;
    }

    public void setUser_id_modified(String user_id_modified) {
        this.user_id_modified = user_id_modified;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", permission='" + permission + '\'' +
                ", is_active='" + is_active + '\'' +
                ", user_id_created='" + user_id_created + '\'' +
                ", user_id_modified='" + user_id_modified + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
