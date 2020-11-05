package com.deyuan.service.Impl;

import com.deyuan.dao.PermissionDao;
import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import com.deyuan.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public List<Permission> permissionAll() {
        return permissionDao.permissionAll() ;
    }

    @Override
    public void permissionAdd(Permission permission) {
        permissionDao.permissionAdd(permission);
    }

    @Override
    public List<Role> findPermissionToRole(String id) {
        return permissionDao.findPermissionToRole(id);
    }



    /*@Override
    public void findById(String id) {
        return permissionDao.findByRoleId()
    }*/

    /*@Override
    public void findByPermissionToRoleId(String id) {
        return permissionDao.findByRoleId(id);

    }*/
}
