package com.cy.mybatis.service;

import com.cy.mybatis.dao.ProductDao;
import com.cy.mybatis.poj.ResultModel;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private static final int PAGE_SIZE = 60;
    @Autowired
    private ProductDao productDao;


    public ResultModel queryProduct(String queryString, String caltalog_name,
                                    String price, String sort, Integer page) throws Exception {
        //拼装查询条件
        SolrQuery query = new SolrQuery();
        //查询条件
        if (null != queryString && !"".equals(queryString)) {
            query.setQuery(queryString);
        } else {
            query.setQuery("*:*");
        }
        //商品分类名称过滤
        if (null != caltalog_name && !"".equals(caltalog_name)) {
            query.addFilterQuery("product_catalog_name:" + caltalog_name);
        }
        //价格区间过滤
        if (null != price && !"".equals(price)) {
            String[] strings = price.split("-");
            query.addFilterQuery("product_price:[" + strings[0] + " TO " + strings[1] + "]");
        }
        //排序条件
        if ("1".equals(sort)) {
            query.setSort("product_price", SolrQuery.ORDER.desc);
        } else {
            query.setSort("product_price", SolrQuery.ORDER.asc);
        }
        //分页处理
        if (null == page) {
            page = 1;
        }
        //start
        int start = (page - 1) * PAGE_SIZE;
        query.setStart(start);
        query.setRows(PAGE_SIZE);
        //设置默认搜索域
        query.set("df", "product_keywords");
        //高亮设置
        query.setHighlight(true);
        query.addHighlightField("product_name");
        query.setHighlightSimplePre("<span style=\"color:red\">");
        query.setHighlightSimplePost("</span>");

        //查询商品列表
        ResultModel resultModel = productDao.queryProduct(query);
        //计算总页数
        long recordCount = resultModel.getRecordCount();
        int pages = (int) (recordCount / PAGE_SIZE);
        if (recordCount % PAGE_SIZE > 0) {
            pages++;
        }
        resultModel.setPageCount(pages);
        resultModel.setCurPage(page);

        return resultModel;
    }

}
