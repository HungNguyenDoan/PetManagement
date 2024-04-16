package com.project.petmanagement.models.entity;

import java.util.List;

public class CareActivityNotification extends Notification{

    private List<CareActivityInfo> careActivityInfoList;

    private RecurringSchedule schedule;

    public List<CareActivityInfo> getCareActivityInfoList() {
        return careActivityInfoList;
    }

    public void setCareActivityInfoList(List<CareActivityInfo> careActivityInfoList) {
        this.careActivityInfoList = careActivityInfoList;
    }

    public RecurringSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(RecurringSchedule schedule) {
        this.schedule = schedule;
    }
}
