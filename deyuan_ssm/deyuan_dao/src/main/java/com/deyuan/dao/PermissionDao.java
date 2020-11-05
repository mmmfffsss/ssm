package com.deyuan.dao;

import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select*  from permission where id in  (select permissionid from role_permission where roleid=#{roleid}) ")
    public List<Permission> findByRoleId(String roleid);


    @Select("select * from permission")
    public List<Permission> permissionAll();


    @Insert("insert into permission (id,permissionname,url)values(role_seq.nextval,#{permissionName},#{url})")
    void permissionAdd(Permission permission);

    @Select("select*  from permission where id in  (select permissionid from role_permission where roleid=#{roleid})")
    void findRoleId(String id);

    @Select("select * from role where id not in(select roleid from role_permission where permissionid=#{permissionId})")
    List<Role> findPermissionToRole(String id);
}
