package com.deyuan.service;

import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;

import java.util.List;


    public interface RoleService {

        List<Role> findAllRole();

        void roleAdd(Role role);

        List<Permission> findByRoleIdOtherPermission(String id);

      /*  void addPermissionToUser(String roleId, String[] permissions);*/

        void addPermissionToRole(String roleId, String[] permissionIds);


        List<Permission> findRoleIdtoPermission(String roleid);
    }
