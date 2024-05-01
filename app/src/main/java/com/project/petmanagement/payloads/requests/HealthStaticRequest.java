package com.project.petmanagement.payloads.requests;

import com.google.gson.annotations.SerializedName;

public class HealthStaticRequest {
    @SerializedName("pet_id")
    private Long petId;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("end_date")
    private String endDate;

    public HealthStaticRequest(Long petId, String startDate, String endDate) {
        this.petId = petId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
