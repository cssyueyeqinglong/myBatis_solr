package com.cy.mybatis.service;

import com.cy.mybatis.mapper.ItemsMapper;
import com.cy.mybatis.poj.Items;
import com.cy.mybatis.poj.ItemsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> getList() {
        ItemsExample example = new ItemsExample();
        return itemsMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public Items findById(Integer id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Items items) {
        return itemsMapper.updateByPrimaryKeySelective(items);
    }

    @Override
    public void deleteAll(int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            itemsMapper.deleteByPrimaryKey(ids[i]);
        }
    }
}
