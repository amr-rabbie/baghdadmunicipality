package com.unicomg.baghdadmunicipality.data.models.scheduled_works;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduledWorksResponse {

    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<ScheduledWorkModel> scheduledWorkModels;

    public ScheduledWorksResponse(String status, String message, List<ScheduledWorkModel> scheduledWorkModels) {
        this.status = status;
        this.message = message;
        this.scheduledWorkModels = scheduledWorkModels;
    }

    public ScheduledWorksResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ScheduledWorkModel> getScheduledWorkModels() {
        return scheduledWorkModels;
    }

    public void setScheduledWorkModels(List<ScheduledWorkModel> scheduledWorkModels) {
        this.scheduledWorkModels = scheduledWorkModels;
    }

    @Override
    public String toString() {
        return "ScheduledWorksResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", scheduledWorkModels=" + scheduledWorkModels +
                '}';
    }
}
