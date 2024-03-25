package com.project.petmanagement.models;

import java.util.Date;

public class HealthRecord {
    private Long id;
    private Date checkupDate;
    private Float weight;
    private Integer exerciseLevel;
    private Date lastVisit;
    private String note;
    public HealthRecord(Date checkupDate, Float weight) {
        this.checkupDate = checkupDate;
        this.weight = weight;
    }
    public HealthRecord(Date checkupDate, Float weight, Integer exerciseLevel, String note) {
        this.checkupDate = checkupDate;
        this.weight = weight;
        this.exerciseLevel = exerciseLevel;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckupDate() {
        return checkupDate;
    }

    public void setCheckupDate(Date checkupDate) {
        this.checkupDate = checkupDate;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getExerciseLevel() {
        return exerciseLevel;
    }

    public void setExerciseLevel(Integer exerciseLevel) {
        this.exerciseLevel = exerciseLevel;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
