package com.cy.mybatis.dao;

import com.cy.mybatis.poj.ProductModel;
import com.cy.mybatis.poj.ResultModel;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 站内搜索引擎查询
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private HttpSolrServer solrServer;

    @Override
    public ResultModel queryProduct(SolrQuery query) throws Exception {

        ResultModel resultModel = new ResultModel();
        //根据query对象查询商品列表
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //取查询结果的总数量
        resultModel.setRecordCount(solrDocumentList.getNumFound());
        List<ProductModel> productList = new ArrayList<>();
        //遍历查询结果
        for (SolrDocument solrDocument : solrDocumentList) {
            //取商品信息
            ProductModel productModel = new ProductModel();
            productModel.setPid((String) solrDocument.get("id"));
            //取高亮显示
            String productName = "";
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
            if (null != list) {
                productName = list.get(0);
            } else {
                productName = (String) solrDocument.get("product_name");
            }
            productModel.setName(productName);
            if (null == solrDocument.get("product_price")) {
                productModel.setPrice(0f);
            } else {
                productModel.setPrice((float) solrDocument.get("product_price"));
            }
            productModel.setCatalog_name((String) solrDocument.get("product_catalog_name"));
            productModel.setPicture((String) solrDocument.get("product_picture"));
            //添加到商品列表
            productList.add(productModel);
        }
        //商品列表添加到resultmodel中
        resultModel.setProductList(productList);
        return resultModel;
    }

}
