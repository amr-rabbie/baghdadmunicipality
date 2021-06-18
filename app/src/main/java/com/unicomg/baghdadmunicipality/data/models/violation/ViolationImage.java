package com.unicomg.baghdadmunicipality.data.models.violation;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class ViolationImage {

    @SerializedName("violation_monitoring_id")
    String violation_monitoring_id;
    @SerializedName("source")
    Bitmap source;
    @SerializedName("description")
    String description;

    public ViolationImage(String violation_monitoring_id, Bitmap source, String description) {
        this.violation_monitoring_id = violation_monitoring_id;
        this.source = source;
        this.description = description;
    }

    public String getViolation_monitoring_id() {
        return violation_monitoring_id;
    }

    public void setViolation_monitoring_id(String violation_monitoring_id) {
        this.violation_monitoring_id = violation_monitoring_id;
    }

    public Bitmap getSource() {
        return source;
    }

    public void setSource(Bitmap source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ViolationImage{" +
                "violation_monitoring_id='" + violation_monitoring_id + '\'' +
                ", source=" + source +
                ", description='" + description + '\'' +
                '}';
    }

    public ViolationImage() {
    }
}
