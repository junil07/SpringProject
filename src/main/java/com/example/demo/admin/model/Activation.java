package com.example.demo.admin.model;

import org.springframework.stereotype.Component;

@Component
public class Activation {

    private int activationUpdate;
    private String activationId;

    public int getActivationUpdate() {
        return activationUpdate;
    }

    public void setActivationUpdate(int activationUpdate) {
        this.activationUpdate = activationUpdate;
    }

    public String getActivationId() {
        return activationId;
    }

    public void setActivationId(String activationId) {
        this.activationId = activationId;
    }
}
