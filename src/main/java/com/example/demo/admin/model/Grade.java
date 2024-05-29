package com.example.demo.admin.model;

import org.springframework.stereotype.Component;

@Component
public class Grade {

    private int gradeUpdate;
    private String gradeId;

    public int getGradeUpdate() {
        return gradeUpdate;
    }

    public void setGradeUpdate(int gradeUpdate) {
        this.gradeUpdate = gradeUpdate;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }
}
