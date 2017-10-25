package com.cy.mybatis.contraler;

import com.cy.mybatis.poj.ResultModel;
import com.cy.mybatis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public String list(String queryString, String catalog_name,
                       String price, Integer page, String sort, Model model) {
        try {
            ResultModel resultModel = productService.queryProduct(queryString, catalog_name, price, sort, page);
            //列表传递给jsp
            model.addAttribute("result", resultModel);
            model.addAttribute("queryString", queryString);
            model.addAttribute("catalog_name", catalog_name);
            model.addAttribute("price", price);
            model.addAttribute("sort", sort);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product_list";
    }
}
