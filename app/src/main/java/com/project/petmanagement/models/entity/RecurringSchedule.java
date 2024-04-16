package com.project.petmanagement.models.entity;

import com.project.petmanagement.models.enums.FrequencyEnum;

public class RecurringSchedule {
    private Long id;
    private String name;
    private FrequencyEnum frequency;
    private Integer value;
    private CareActivityNotification careActivityNotification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FrequencyEnum getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyEnum frequency) {
        this.frequency = frequency;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public CareActivityNotification getCareActivityNotification() {
        return careActivityNotification;
    }

    public void setCareActivityNotification(CareActivityNotification careActivityNotification) {
        this.careActivityNotification = careActivityNotification;
    }
}
