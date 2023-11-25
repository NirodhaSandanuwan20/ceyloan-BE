package com.exam.service.impl;

import com.exam.helper.Generator;
import com.exam.helper.ServerErorrException;
import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.EmailService;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EmailService emailService;
    private Generator generator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService, Generator generator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.generator = generator;
    }


    //creating user
    @Override
    public boolean createUser(User user, Set<UserRole> userRoles) throws Exception {

        /*Optional<User> local = this.userRepository.findByEmail(user.getEmail());

        String verifyCode = generator.createVerifyCode();
        if (emailService.createEmail(user.getEmail(), "Regarding Login Verification",
                "<h1>Your Verification Code :" + verifyCode + "</h1>")) {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            user.setIsEnabled(false);
            user.setOtp(verifyCode);
            return this.userRepository.save(user);

        } else {
            throw new ServerErorrException();
        }*/


        /* //intially gnnko addmin case ek nisa mhm arn db update krl gnn lede blmu api
        for (UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }
        this.userRepository.save(user);
        return true;*/

        System.out.println("userIMPL");
        System.out.println(user);
        System.out.println(userRoles);
        Optional<User> local = this.userRepository.findByEmail(user.getEmail());

        if(local.isEmpty() || !local.get().isEnabled()){
            if(local.isPresent()){
                this.userRepository.delete(local.get());
            }
            String verifyCode = generator.createVerifyCode();

            String emailBo = "<style>\n" +
                    "*, html, body {\n" +
                    "  margin: 0;\n" +
                    "  padding: 0;\n" +
                    "  box-sizing: border-box;\n" +
                    "  font-family: \"Roboto\", sans-serif;\n" +
                    "}\n" +
                    "\n" +
                    ".container {\n" +
                    "  height: 80vh;\n" +
                    "  width: 100%;\n" +
                    "  display: flex;\n" +
                    "  justify-content: center;\n" +
                    "  align-items: center;\n" +
                    "  background: #cecece;\n" +
                    "}\n" +
                    ".container .box_container {\n" +
                    "  background: white;\n" +
                    "  width: 60%;\n" +
                    "  border-radius: 1rem;\n" +
                    "  padding-inline: 2rem;\n" +
                    "  padding-block: 2rem;\n" +
                    "}\n" +
                    ".container .box_container h1 {\n" +
                    "  text-align: center;\n" +
                    "  margin-bottom: 1.4rem;\n" +
                    "  color: #7c09ed;\n" +
                    "}\n" +
                    ".container .box_container h2 {\n" +
                    "  text-align: center;\n" +
                    "}\n" +
                    ".container .box_container h2 b {\n" +
                    "  color: #7c09ed;\n" +
                    "}\n" +
                    ".container .box_container p {\n" +
                    "  padding-block: 0.6rem;\n" +
                    "  font-size: 1.1rem;\n" +
                    "  line-height: 1.5em;\n" +
                    "  color: #444;\n" +
                    "  text-align: center;\n" +
                    "}\n" +
                    ".container .box_container p b {\n" +
                    "  color: #7c09ed;\n" +
                    "}\n" +
                    ".container .box_container .otp {\n" +
                    "  margin-block: 2rem;\n" +
                    "  background: #efefef;\n" +
                    "  width: -webkit-fit-content;\n" +
                    "  width: -moz-fit-content;\n" +
                    "  width: fit-content;\n" +
                    "  margin-inline: auto;\n" +
                    "  padding-inline: 4rem;\n" +
                    "  padding-block: 0.8rem;\n" +
                    "  border-radius: 0.2rem;\n" +
                    "  display: flex;\n" +
                    "  align-items: center;\n" +
                    "  justify-content: center;\n" +
                    "}\n" +
                    ".container .box_container .otp h1 {\n" +
                    "  margin: 0;\n" +
                    "}\n" +
                    ".container .box_container .disclaimer {\n" +
                    "  width: 100%;\n" +
                    "  text-align: center;\n" +
                    "  margin-bottom: 2rem;\n" +
                    "}\n" +
                    ".container .box_container .disclaimer small {\n" +
                    "  color: #4f4f4f;\n" +
                    "}\n" +
                    ".container .box_container footer {\n" +
                    "  text-align: center;\n" +
                    "  margin-top: 1rem;\n" +
                    "}\n" +
                    ".container .box_container footer section:nth-child(2) {\n" +
                    "  margin-top: 1rem;\n" +
                    "  font-weight: 600;\n" +
                    "}\n" +
                    "</style>\n" +
                    "\n" +
                    "<div style=' margin: 0;\n" +
                    "  padding: 0;\n" +
                    "  box-sizing: border-box;\n" +
                    "  font-family: \"Roboto\", sans-serif;' class=\"container\">\n" +
                    "  <div class=\"box_container\">\n" +
                    "    <h1>Ceylon Papers Hub</h1>\n" +
                    "    <h2>Verify Account For <b>Ceylon Papers Hub</b></h2>\n" +
                    "    <p>Verify account to get started. Enter this otp and verify now. Thanks for choosing <b>Ceylon Papers Hub</b></p>\n" +
                    "    <div class=\"otp\">\n" +
                    "      <h1>" + verifyCode + "</h1>\n" +
                    "    </div>\n" +
                    "    <div class=\"disclaimer\">\n" +
                    "      <small>\n" +
                    "        Please do not share this otp with anyone. If not requested by you please ignore this email.\n" +
                    "      </small>\n" +
                    "    </div>\n" +
                    "    <footer>\n" +
                    "      <section></section>\n" +
                    "      <section>Copyright of CPH &copy; 2023</section>\n" +
                    "    </footer>\n" +
                    "  </div>\n" +
                    "</div>";

            if (emailService.createEmail(user.getEmail(), "Regarding Login Verification",
                    emailBo)) {
                //user create
                for (UserRole ur : userRoles) {
                    roleRepository.save(ur.getRole());
                }
                user.getUserRoles().addAll(userRoles);
                user.setEnabled(false);
                user.setOtp(verifyCode);

                this.userRepository.save(user);
                return true;

            } else {
                throw new ServerErorrException();
            }
        }

        if (local.get().isEnabled()) {
            new UserFoundException();
        }

        return false;

    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserById(Long userID) {
        return this.userRepository.findById(userID);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User verifyAccount(String email, String otp) throws Exception {
        Optional<User> selectedUser = userRepository.findByEmail(email);
        if (selectedUser.isEmpty()) throw new UserFoundException();
        System.out.println(otp);
        if (selectedUser.get().getOtp().equals(otp)) {
            User Activated = userRepository.save(selectedUser.get());
            return Activated;
        } else {
            throw new Exception();
        }

    }

    @Override
    public User resendMail(String email) {
        Optional<User> selectedUser = this.userRepository.findByEmail(email);
        String verifyCode = generator.createVerifyCode();

        String emailB = "<style>\n" +
                "*, html, body {\n" +
                "  margin: 0;\n" +
                "  padding: 0;\n" +
                "  box-sizing: border-box;\n" +
                "  font-family: \"Roboto\", sans-serif;\n" +
                "}\n" +
                "\n" +
                ".container {\n" +
                "  height: 80vh;\n" +
                "  width: 100%;\n" +
                "  display: flex;\n" +
                "  justify-content: center;\n" +
                "  align-items: center;\n" +
                "  background: #cecece;\n" +
                "}\n" +
                ".container .box_container {\n" +
                "  background: white;\n" +
                "  width: 60%;\n" +
                "  border-radius: 1rem;\n" +
                "  padding-inline: 2rem;\n" +
                "  padding-block: 2rem;\n" +
                "}\n" +
                ".container .box_container h1 {\n" +
                "  text-align: center;\n" +
                "  margin-bottom: 1.4rem;\n" +
                "  color: #7c09ed;\n" +
                "}\n" +
                ".container .box_container h2 {\n" +
                "  text-align: center;\n" +
                "}\n" +
                ".container .box_container h2 b {\n" +
                "  color: #7c09ed;\n" +
                "}\n" +
                ".container .box_container p {\n" +
                "  padding-block: 0.6rem;\n" +
                "  font-size: 1.1rem;\n" +
                "  line-height: 1.5em;\n" +
                "  color: #444;\n" +
                "  text-align: center;\n" +
                "}\n" +
                ".container .box_container p b {\n" +
                "  color: #7c09ed;\n" +
                "}\n" +
                ".container .box_container .otp {\n" +
                "  margin-block: 2rem;\n" +
                "  background: #efefef;\n" +
                "  width: -webkit-fit-content;\n" +
                "  width: -moz-fit-content;\n" +
                "  width: fit-content;\n" +
                "  margin-inline: auto;\n" +
                "  padding-inline: 4rem;\n" +
                "  padding-block: 0.8rem;\n" +
                "  border-radius: 0.2rem;\n" +
                "  display: flex;\n" +
                "  align-items: center;\n" +
                "  justify-content: center;\n" +
                "}\n" +
                ".container .box_container .otp h1 {\n" +
                "  margin: 0;\n" +
                "}\n" +
                ".container .box_container .disclaimer {\n" +
                "  width: 100%;\n" +
                "  text-align: center;\n" +
                "  margin-bottom: 2rem;\n" +
                "}\n" +
                ".container .box_container .disclaimer small {\n" +
                "  color: #4f4f4f;\n" +
                "}\n" +
                ".container .box_container footer {\n" +
                "  text-align: center;\n" +
                "  margin-top: 1rem;\n" +
                "}\n" +
                ".container .box_container footer section:nth-child(2) {\n" +
                "  margin-top: 1rem;\n" +
                "  font-weight: 600;\n" +
                "}\n" +
                "</style>\n" +
                "\n" +
                "<div style=' margin: 0;\n" +
                "  padding: 0;\n" +
                "  box-sizing: border-box;\n" +
                "  font-family: \"Roboto\", sans-serif;' class=\"container\">\n" +
                "  <div class=\"box_container\">\n" +
                "    <h1>Ceylon Papers Hub</h1>\n" +
                "    <h2>Verify Account For <b>Ceylon Papers Hub</b></h2>\n" +
                "    <p>Verify account to get started. Enter this otp and verify now. Thanks for choosing <b>Ceylon Papers Hub</b></p>\n" +
                "    <div class=\"otp\">\n" +
                "      <h1>" + verifyCode + "</h1>\n" +
                "    </div>\n" +
                "    <div class=\"disclaimer\">\n" +
                "      <small>\n" +
                "        Please do not share this otp with anyone. If not requested by you please ignore this email.\n" +
                "      </small>\n" +
                "    </div>\n" +
                "    <footer>\n" +
                "      <section></section>\n" +
                "      <section>Copyright of CPH &copy; 2023</section>\n" +
                "    </footer>\n" +
                "  </div>\n" +
                "</div>";

        emailService.createEmail(email, "Verify User",
                emailB);
        selectedUser.get().setOtp(verifyCode);
        return this.userRepository.save(selectedUser.get());
    }

    @Override
    public User changeEmailRequest(String oldEmail, String newEmail) {
        Optional<User> selectedUser = this.userRepository.findByEmail(oldEmail);

        String verifyCode = generator.createVerifyCode();
        emailService.createEmail(newEmail, "Verify Your Email",
                "<h1>Your Verification Code :" + verifyCode + "</h1>");
        selectedUser.get().setOtp(verifyCode);
        return this.userRepository.save(selectedUser.get());
    }

    @Override
    public User verifyNewMail(String otp, String newEmail, String oldEmail) throws Exception {
        Optional<User> selectedUser = userRepository.findByEmail(oldEmail);
        if (selectedUser.isEmpty()) throw new UserFoundException();
        System.out.println(otp);
        if (selectedUser.get().getOtp().equals(otp)) {
            //verify
            selectedUser.get().setEmail(newEmail);
            User Activated = userRepository.save(selectedUser.get());
            return Activated;
        } else {
            throw new Exception();
        }
    }

    @Override
    public User forgotPassword(String otp, String newPassword, String mail) throws Exception {

        Optional<User> selectedUser = this.userRepository.findByEmail(mail);
        if (selectedUser.get().getOtp().equals(otp)) {
            if(!selectedUser.get().isEnabled()){
                selectedUser.get().setEnabled(true);
            }
            //change password
            selectedUser.get().setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.userRepository.save(selectedUser.get());
            /*emailService.createEmail(mail, "Verify User",
                    emailBc);*/
            return selectedUser.get();
        } else {
            throw new Exception();
        }

    }
}


