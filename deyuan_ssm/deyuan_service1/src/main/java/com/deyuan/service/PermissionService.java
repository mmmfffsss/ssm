package com.deyuan.service;

import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PermissionService {

    public List<Permission> permissionAll();

    void permissionAdd(Permission permission);

    List<Role> findPermissionToRole(String id);

    //void findById(String id);

   /* void findByPermissionToRoleId(String id);*/
}
