package com.project.petmanagement.payloads.requests;

import com.google.gson.annotations.SerializedName;

public class CareActivityInfoRequest {
    private Long id;
    @SerializedName("care_activity_id")
    private Long careActivityId;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCareActivityId() {
        return careActivityId;
    }

    public void setCareActivityId(Long careActivityId) {
        this.careActivityId = careActivityId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
