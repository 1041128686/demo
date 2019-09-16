package com.my.service.impl;

import com.my.pojo.User;
import com.my.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {

        userRepository.save(user);
    }


    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username,password);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(s);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        System.out.println(user);
        org.springframework.security.core.userdetails.User userDetail =
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole())
                );
        return userDetail;
    }
}
