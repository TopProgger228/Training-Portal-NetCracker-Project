package com.group3.basic.netcracker.backend.authorization.security.services;

import com.group3.basic.netcracker.backend.UsersTable.dao.jdbc.JdbcTemplateUsersDaoImpl;
import com.group3.basic.netcracker.backend.UsersTable.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
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
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbctemplate-users-config.xml");

        JdbcTemplateUsersDaoImpl jdbcTemplateUsersDao =
                (JdbcTemplateUsersDaoImpl) context.getBean("jdbcTemplateUsersDao");
        Users user = jdbcTemplateUsersDao.findByUsername(username);

        return UserPrinciple.build(user);
    }
}