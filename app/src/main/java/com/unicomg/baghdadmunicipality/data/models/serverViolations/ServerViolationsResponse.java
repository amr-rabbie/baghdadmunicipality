package com.unicomg.baghdadmunicipality.data.models.serverViolations;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerViolationsResponse {
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<ServerViolation> serverViolations;


    public ServerViolationsResponse() {
    }

    public ServerViolationsResponse(String status, String message, List<ServerViolation> serverViolations) {
        this.status = status;
        this.message = message;
        this.serverViolations = serverViolations;
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

    public List<ServerViolation> getServerViolations() {
        return serverViolations;
    }

    public void setServerViolations(List<ServerViolation> serverViolations) {
        this.serverViolations = serverViolations;
    }

    @Override
    public String toString() {
        return "ServerViolationsResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", serverViolations=" + serverViolations +
                '}';
    }
}
