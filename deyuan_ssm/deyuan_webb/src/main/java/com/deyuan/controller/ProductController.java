package com.deyuan.controller;

import com.deyuan.pojo.Product;

import com.deyuan.service.IProdutService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/10/28
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProdutService productService;

    @RequestMapping("/findAll")
    //@RolesAllowed("ADMIN")//只有用户有ADMIN这个角色猜可以使用该功能
    //查询所有产品
    /* public  String findAll(Model model){*/
    public ModelAndView findAllTest(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> list = productService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(list);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("product-list");
        //model.addAttribute("productList",list);
        //return "product-list";
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Product product) {

        productService.save(product);

        return "redirect:findAll.do";
    }

}
