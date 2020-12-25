package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author v_liyichen
 * @date 2020.12.25 11:45
 */

@Service("userDetailsService")
public class UserDetail implements UserDetailsService {

    @Autowired
    private UserMapper UserMapper;
    @Override
    public UserDetails loadUserByUsername(String s)  throws UsernameNotFoundException {
        QueryWrapper w = new QueryWrapper();

        w.eq("username",s);
        com.example.demo.bean.User user = UserMapper.selectOne(w);

        if (user == null) {
            throw  new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> admin = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),admin);
    }
}
