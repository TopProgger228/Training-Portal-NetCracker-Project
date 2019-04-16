package com.group3.basic.netcracker.backend;

import com.group3.basic.netcracker.backend.UsersTable.dao.jdbc.JdbcTemplateUsersDaoImpl;
import com.group3.basic.netcracker.backend.UsersTable.model.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbctemplate-users-config.xml");

        JdbcTemplateUsersDaoImpl jdbcTemplateUsersDao =
                (JdbcTemplateUsersDaoImpl) context.getBean("jdbcTemplateUsersDao");
        LocalDate todayDate = LocalDate.now();

        System.out.println("========Creating new records to DB========");
        //jdbcTemplateUsersDao.createUser("student0", 4,"Jack", "Bo", "mail@temp-mail.com", "12345", todayDate, null);
        jdbcTemplateUsersDao.createUser("billy123", 4,"Rick", "Taker", "billymail@temp-mail.com", "qwerty", todayDate, null);
        //jdbcTemplateUsersDao.createUser("Petr", "C++ Developer", 2);
        //jdbcTemplateUsersDao.createUser("DesignerAsya", "UI Developer", 1);

        System.out.println("========Developers List========");
        List<Users> user = jdbcTemplateUsersDao.listUsers();
        for (Users users : user) {
            System.out.println(users);
        }

        System.out.println("========Some changes to DB========");
        //jdbcTemplateUsersDao.updateUser(33, "DesignerAsya", "UI Developer", 2);
        //jdbcTemplateUsersDao.removeUser(32);

        System.out.println("========Final Developers List========");
        List<Users> finalUsers = jdbcTemplateUsersDao.listUsers();

        for (Users users : finalUsers) {
            System.out.println(users);
        }
    }

}
