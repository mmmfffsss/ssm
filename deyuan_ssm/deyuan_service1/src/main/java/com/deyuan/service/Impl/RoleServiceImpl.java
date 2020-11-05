package com.deyuan.service.Impl;

import com.deyuan.dao.RoleDao;
import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import com.deyuan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/11/2
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAllRole() {
        List<Role> allRole = roleDao.findAllRole();
        return allRole;
    }

    @Override
    public void roleAdd(Role role) {
        roleDao.roleAdd(role);
    }

    @Override
    public List<Permission> findByRoleIdOtherPermission(String id) {
        return roleDao.findByRoleIdOtherPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> findRoleIdtoPermission(String roleid) {
        return roleDao.findRoleAllPermission(roleid);
    }



  /*  @Override
    public void addPermissionToUser(String roleId, String[] permissions) {
        for (String permission : permissions) {
            roleDao.addPermission(roleId,permission);
        }
    }*/
}
