package com.deyuan.controller;

import com.deyuan.pojo.Orders;
import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import com.deyuan.pojo.UserInfo;
import com.deyuan.service.OrdersService;
import com.deyuan.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;


    @RequestMapping("/findAll")
    private ModelAndView TestFindAll(){
        List<Permission> permissions = permissionService.permissionAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("permissionList",permissions);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public  String permissionAdd(Permission permission) {
        permissionService.permissionAdd(permission);
        return "redirect:findAll.do";

    }
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String id){

      /*  List<Role> roles=userService.findOtherRoles(id);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("roleList",roles);
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-role-add");
        return modelAndView;

        permissionService.findByPermissionToRoleId(id);*/
        List<Role> roles = permissionService.findPermissionToRole(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;


    }



}
