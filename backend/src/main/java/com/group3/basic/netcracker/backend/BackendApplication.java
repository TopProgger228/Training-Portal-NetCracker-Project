package com.group3.basic.netcracker.backend;

import com.group3.basic.netcracker.backend.usertable.dao.daoimpl.UserDaoImpl;
import com.group3.basic.netcracker.backend.usertable.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BackendApplication.class, args);

        UserDaoImpl jdbcTemplateUsersDao = context.getBean(UserDaoImpl.class);
        LocalDate todayDate = LocalDate.now();

        System.out.println("========Creating new records to DB========");
        //jdbcTemplateUsersDao.createUser("student0", 4,"Jack", "Bo", "mail@temp-mail.com", "12345", todayDate, null,5);
        //jdbcTemplateUsersDao.createUser("student0", 4,"Jack", "Bo", "mail@temp-mail.com", "12345", todayDate, null);
        //jdbcTemplateUsersDao.createUser("billy123", 4,"Rick", "Taker", "billymail@temp-mail.com", "qwerty", todayDate, null);
        //jdbcTemplateUsersDao.createUser("Petr", "C++ Developer", 2);
        //jdbcTemplateUsersDao.createUser("DesignerAsya", "UI Developer", 1);

        System.out.println("========Developers List========");
        List<User> user = jdbcTemplateUsersDao.listUsers();
        for (User users : user) {
            System.out.println(users);
        }

        System.out.println("========Some changes to DB========");
        //jdbcTemplateUsersDao.updateUser(33, "DesignerAsya", "UI Developer", 2);
        //jdbcTemplateUsersDao.removeUser(32);

        System.out.println("========Final Developers List========");
        List<User> finalUsers = jdbcTemplateUsersDao.listUsers();

        for (User users : finalUsers) {
            System.out.println(users);
        }
    }

}
