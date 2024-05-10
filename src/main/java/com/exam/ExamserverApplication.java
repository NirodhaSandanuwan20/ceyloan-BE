package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.QuizRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public QuizRepository quizRepository;

    public static void main(String[] args) {


        SpringApplication.run(ExamserverApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        /*try {


            System.out.println("starting code");

            User user = new User();

            user.setFirstName("Nirodha");
            user.setLastName("Sandanuwan");
            user.setUsername("nirodha123");
            user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
            user.setEmail("nirodhasandanuwan2002@gmail.com");
            user.setProfile("default.png");
            user.setEnabled(true);

            Role role1 = new Role();
            role1.setRoleId(44L);
            role1.setRoleName("ADMIN");


            Set<UserRole> userRoleSet = new HashSet<>();
            UserRole userRole = new UserRole();

            userRole.setRole(role1);

            userRole.setUser(user);

            userRoleSet.add(userRole);

            boolean user1 = this.userService.createUser(user, userRoleSet);
            System.out.println(user1);


        } catch (UserFoundException e) {
            e.printStackTrace();
        }*/
    }
}
