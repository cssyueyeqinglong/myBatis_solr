package com.cy.mybatis.service;

import com.cy.mybatis.poj.ResultModel;

public interface ProductService {
    public ResultModel queryProduct(String queryString, String caltalog_name,
                                    String price, String sort, Integer page) throws Exception;
}
