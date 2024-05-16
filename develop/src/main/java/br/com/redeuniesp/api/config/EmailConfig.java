package br.com.redeuniesp.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "mail")
public class EmailConfig {

    @Value("${mail.smtp.host:host}")
    private String host = "host";

    @Value("${mail.smtp.port:port}")
    private String port = "port";

    @Value("${mail.smtp.username:username}")
    private String username = "username";

    @Value("${mail.smtp.password:password}")
    private String password = "password";

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // ambiente dev

        return mailSender;
    }

}
