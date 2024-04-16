package com.project.petmanagement.models.entity;

public class CareActivityInfo {
    private Long id;

    private CareActivity careActivity;
    private String note;

    private CareActivityNotification careActivityNotification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CareActivity getCareActivity() {
        return careActivity;
    }

    public void setCareActivity(CareActivity careActivity) {
        this.careActivity = careActivity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CareActivityNotification getCareActivityNotification() {
        return careActivityNotification;
    }

    public void setCareActivityNotification(CareActivityNotification careActivityNotification) {
        this.careActivityNotification = careActivityNotification;
    }
}
