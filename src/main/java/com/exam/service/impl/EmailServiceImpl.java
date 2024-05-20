package com.exam.service.impl;

import com.exam.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    String emailBody = "<style>\n" +
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
            "      <h1>{verifyCode}</h1>\n" +
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

    private JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }




    @Override
    public boolean signupEmail(String email , String otp) {
        return sendMail(email, "Regarding Email Verification" , emailBody.replace("{verifyCode}", otp));
    }

    @Override
    public boolean ChangeEmail(String email, String otp) {
        return sendMail(email, "Regarding New Email Verification" , emailBody.replace("{verifyCode}", otp));
    }

    @Override
    public boolean welcomeEmail(String email , String firstName) {
        return sendMail(email, "Hello "+firstName+" ! welcome to Ceylon Paper Hub" , emailBody);
    }

    public boolean sendMail(String mail, String subject, String body) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(subject);
            emailSender.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}


