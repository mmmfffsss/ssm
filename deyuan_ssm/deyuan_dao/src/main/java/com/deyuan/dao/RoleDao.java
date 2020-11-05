package com.deyuan.dao;

import com.deyuan.pojo.Permission;
import com.deyuan.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {


    @Select("select * from role where id in (select roleid from users_role where userid=#{id}) ")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many  =@Many(select = "com.deyuan.dao.PermissionDao.findByRoleId"))
    })
   List<Role> findRoleByUserId(String id);


    @Select("select * from role")
    List<Role> findAllRole();

    @Insert("insert into role (id,rolename,roledesc)values(role_seq.nextval,#{roleName},#{roleDesc}) ")

    void roleAdd(Role role);

    @Select("select * from permission where id not in(select permissionid from role_permission where roleid = #{id})")
    List<Permission> findByRoleIdOtherPermission(String id);

    @Insert("insert into role_permission (roleid,permissionid)values(#{roleId},#{permissionId})")
    void addPermission(@Param("roleId") String roleId,@Param("permissionId") String permission);

    @Insert("insert into role_permission (roleid,permissionid)values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);

    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> findRoleAllPermission(String roleid);
}
