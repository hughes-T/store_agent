package com.hughes.core.login;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 这里简单模拟用户信息，实际应用中应从数据库获取
        if ("zhangsan".equals(username)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = "123456";
            String encryptedPassword = encoder.encode(password);
            System.out.println("BCrypt加密结果: " + encryptedPassword);

            return new User(username, encryptedPassword,
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
