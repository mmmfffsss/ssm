package com.deyuan.service.Impl;

import com.deyuan.dao.UserDao;
import com.deyuan.pojo.Role;
import com.deyuan.pojo.UserInfo;
import com.deyuan.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 孟哥
 * <p>
 * 2020/10/31
 */
@Service("userService")
public class IuserServiceImpl implements IuserService {
   @Autowired
   private UserDao userDao;
   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));

        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> rolelist) {

        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : rolelist) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }

    @Override
    public List<UserInfo> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userid) {
        return userDao.findOtherRoles(userid);
    }

    @Override
    public void addRoleToUser(String userId, String[] roles) {
        for (String roleId : roles) {
            userDao.addRoleToUser(userId,roleId);
        }
    }


}
