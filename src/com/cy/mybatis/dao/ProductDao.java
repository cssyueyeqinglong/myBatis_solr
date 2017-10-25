package com.cy.mybatis.dao;

import com.cy.mybatis.poj.ResultModel;
import org.apache.solr.client.solrj.SolrQuery;

public interface ProductDao {
    public ResultModel queryProduct(SolrQuery query) throws Exception;
}
