package com.deyuan.controller;


import com.deyuan.pojo.Role;
import com.deyuan.pojo.UserInfo;
import com.deyuan.service.IuserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IuserService userService;


    @RequestMapping("/findAll")
    //@PreAuthorize("hasRole('ROLE_GUEST')")//有该角色的用户才能使用该功能
    public ModelAndView findAllUser(){
        ModelAndView modelAndView=new ModelAndView();
        List<UserInfo> allUser = userService.findAllUser();
        modelAndView.addObject("userList",allUser);
        modelAndView.setViewName("user-list");
        return  modelAndView;

    }


    @RequestMapping("/save")
   // @PreAuthorize("authentication.principal.username=='cctv1'")//规定只有用户名为CCTV1的用户使用该功能
    public String userAdd(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";


    }

    // user/findById.
    @RequestMapping("/findById")
    private ModelAndView TestFindById(@RequestParam(name="id",required = true) String id){
        UserInfo userInfo=userService.findById(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;

    }

   //user/findUserByIdAndAllRole
  @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid){
        UserInfo userInfo=userService.findById(userid);
        //根据用户id查出该用户没有的角色
        List<Role> roles=userService.findOtherRoles(userid);

       ModelAndView modelAndView=new ModelAndView();
       modelAndView.addObject("roleList",roles);
       modelAndView.addObject("user",userInfo);
      modelAndView.setViewName("user-role-add");
       return modelAndView;

   }

    @RequestMapping("/addRoleToUser")
    public String test(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roles){
        userService.addRoleToUser(userId,roles);
        return "redirect:findAll.do";


    }

    /*@RequestMapping("/findRoleByIdPermission")
    public ModelAndView test1(@RequestParam(name = "roleId",required = true) String roleId){
        List<Permission> permissions=userService.findOtherPermission(roleId);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("PermissionList",permissions);
        modelAndView.setViewName("role-permission-add.jsp");
    }*/
}
