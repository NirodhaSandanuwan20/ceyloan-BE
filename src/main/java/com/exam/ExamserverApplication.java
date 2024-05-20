package com.exam;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.QuizRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public QuizRepository quizRepository;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.firstName}")
    private String adminFirstName;

    @Value("${admin.lastName}")
    private String adminLastName;

    @Value("${admin.role}")
    private String adminRole;

    public static void main(String[] args) {
        SpringApplication.run(ExamserverApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("++++++++++++++++++++++Starting code++++++++++++++++++++");

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setFirstName(adminFirstName);
                admin.setLastName(adminLastName);
                admin.setPassword(bCryptPasswordEncoder.encode(adminPassword));
                admin.setEmail(adminEmail);
                admin.setProfile("default.png");
                admin.setEnabled(true);

                Role role = new Role();
                role.setRoleId(44L);
                role.setRoleName(adminRole);

                Set<UserRole> userRoles = new HashSet<>();
                UserRole userRole = new UserRole();
                userRole.setRole(role);
                userRole.setUser(admin);
                userRoles.add(userRole);


                userService.createUser(admin, userRoles);

            } else {
                System.out.println("Admin user already exists.");
            }
        } catch (UserFoundException e) {
            e.printStackTrace();
        }
    }
}
