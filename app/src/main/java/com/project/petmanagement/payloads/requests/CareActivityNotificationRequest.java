package com.project.petmanagement.payloads.requests;

import java.io.Serializable;
import java.util.List;

public class CareActivityNotificationRequest implements Serializable {
    private Long petId;
    private String title;
    private List<CareActivityInfoRequest> careActivityInfoRequestList;
    private String note;
    private RecurringScheduleRequest recurringScheduleRequest;
    private Boolean notificationStatus;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CareActivityInfoRequest> getCareActivityInfoRequestList() {
        return careActivityInfoRequestList;
    }

    public void setCareActivityInfoRequestList(List<CareActivityInfoRequest> careActivityInfoRequestList) {
        this.careActivityInfoRequestList = careActivityInfoRequestList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RecurringScheduleRequest getRecurringScheduleRequest() {
        return recurringScheduleRequest;
    }

    public void setRecurringScheduleRequest(RecurringScheduleRequest recurringScheduleRequest) {
        this.recurringScheduleRequest = recurringScheduleRequest;
    }

    public Boolean getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(Boolean notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
