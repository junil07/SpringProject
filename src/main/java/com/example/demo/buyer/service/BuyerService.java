package com.example.demo.buyer.service;

import java.util.List;
import java.util.Map;

public interface BuyerService {
    public List getAll();

    //다인 수정
    public List getSubCategories(Long parentId);
    //
    public List getRows();

    public List getRowParamOne(Integer productId);

}
