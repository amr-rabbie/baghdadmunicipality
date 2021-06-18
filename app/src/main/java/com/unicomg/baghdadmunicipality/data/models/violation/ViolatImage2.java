package com.unicomg.baghdadmunicipality.data.models.violation;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class ViolatImage2 {

    @SerializedName("violation_monitoring_id")
    String violation_monitoring_id;
    @SerializedName("source")
    String source;
    @SerializedName("description")
    String description;

    public ViolatImage2(String violation_monitoring_id, String source, String description) {
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
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

    public ViolatImage2() {
    }

}
