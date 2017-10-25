package com.cy.mybatis.service;

import com.cy.mybatis.poj.Items;

import java.util.List;

public interface ItemsService {
    public List<Items> getList();

    public Items findById(Integer id);

    public int update(Items items);

    public void deleteAll(int[] ids);
}
