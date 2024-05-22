package com.example.demo.admin.service;

import java.util.List;

public interface BuyerService {

    public List getBuyerList();
    public List<String> updateGrade(String buyerId, short grade);
    public List<String> updateActivation(String buyerId, short activation);

}
