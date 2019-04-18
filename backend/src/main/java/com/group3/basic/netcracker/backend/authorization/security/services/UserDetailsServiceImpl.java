package com.group3.basic.netcracker.backend.authorization.security.services;

import com.group3.basic.netcracker.backend.UserTable.dao.jdbc.JdbcTemplateUserDaoImpl;
import com.group3.basic.netcracker.backend.UserTable.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbctemplate-user-config.xml");

        JdbcTemplateUserDaoImpl jdbcTemplateUsersDao =
                (JdbcTemplateUserDaoImpl) context.getBean("jdbcTemplateUserDao");
        User user = jdbcTemplateUsersDao.findByUsername(username);

        return UserPrinciple.build(user);
    }
}