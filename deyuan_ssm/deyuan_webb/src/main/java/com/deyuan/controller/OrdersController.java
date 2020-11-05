 package com.deyuan.controller;

import com.deyuan.pojo.Orders;
import com.deyuan.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/10/29
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll")
   // @Secured("ROLE_ADMIN")
    public ModelAndView findAllTest(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        List<Orders> list = ordersService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(list);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;

    }




    @RequestMapping("/findById")
    public  ModelAndView findByIdTest(@RequestParam(name = "id",required = true) String  orderid) throws Exception {
        Orders orders=ordersService.findById(orderid);
        ModelAndView modelAndView=new ModelAndView();
        //Orders byId = ordersService.findById(id);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
