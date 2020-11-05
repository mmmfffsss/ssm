package com.deyuan.service;

import com.deyuan.pojo.Role;
import com.deyuan.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/10/31
 */
public interface IuserService extends UserDetailsService  {

    List<UserInfo> findAllUser();

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String userid);

    void addRoleToUser(String userId, String[] roles);
}
