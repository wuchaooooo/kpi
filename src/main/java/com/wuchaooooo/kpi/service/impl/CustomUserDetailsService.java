package com.wuchaooooo.kpi.service.impl;

import com.wuchaooooo.kpi.javabean.po.PUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired  //数据库服务类
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        PUser user = userService.getUser(userName);

        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
        System.err.println("username is " + userName + ", " + user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), authorities);
    }

    public String modifyPassword(String userName, String oldPassword, String newPassword, String checkPassword) {
        //数据库中存着的加密密码
        String password = userService.getUser(userName).getPassword();
        if (!passwordEncoder.matches(oldPassword, password)) {
            return "您输入的原始密码有误，请重新输入";
        } else if (!newPassword.equals(checkPassword)) {
            return "您两次输入的新密码不一致，请重新输入";
        } else if (oldPassword.equals(newPassword)) {
            return "您两次输入的新密码不一致，请重新输入";
        }
        String encodeNewPassword = passwordEncoder.encode(newPassword);
        userService.modifyPassword(encodeNewPassword, userName);

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication currentAuth = ctx.getAuthentication();
//        createNewAuthentication(currentAuth);
        return "密码更新成功";
    }

    public boolean createNewAuthentication(Authentication currentAuth)
    {
        UserDetails user = loadUserByUsername(currentAuth.getName());

        UsernamePasswordAuthenticationToken newAuthentication =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        newAuthentication.setDetails(currentAuth.getDetails());
        SecurityContext ctx = SecurityContextHolder.getContext();
        ctx.setAuthentication(newAuthentication);
        return true;
    }



}

