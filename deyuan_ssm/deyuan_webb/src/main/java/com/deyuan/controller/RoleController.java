package com.deyuan.controller;

import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;


import com.deyuan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/10/31
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    private ModelAndView testFindAllRole(){
        ModelAndView modelAndView=new ModelAndView();
        List<Role> allRole =roleService.findAllRole();
        modelAndView.addObject("roleList",allRole);
        modelAndView.setViewName("role-list");
        return modelAndView;

    }

//    //role/save
    @RequestMapping("/save")
    private String roleAdd(Role role){
        roleService.roleAdd(role);
        return "redirect:findAll.do";
    }
//addPermissionToRole
    @RequestMapping("/findRoleByIdPermission")
    public ModelAndView test1(@RequestParam(name = "id",required = true ) String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleId", id);
        List<Permission> permissions = roleService.findByRoleIdOtherPermission(id);
        modelAndView.addObject("permissionList", permissions);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /*@RequestMapping("/addPermissionToUser")
    public String addPermissionToUser(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name ="ids",required = true)String[] permissions ){
        service.addPermissionToUser(roleId,permissions);
        return "redirect:findAll.do";
    }*/
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRoleTest(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds){
     roleService.addPermissionToRole(roleId,permissionIds);
     return "redirect:findAll.do";


    }

    @RequestMapping("/findById")
    public ModelAndView findByIdTest(String id){
        ModelAndView modelAndView=new ModelAndView();
        List<Permission> roleIdtoPermission = roleService.findRoleIdtoPermission(id);
        modelAndView.addObject("permissionList",roleIdtoPermission);
        modelAndView.setViewName("permission-list");
        return modelAndView;


    }


}
