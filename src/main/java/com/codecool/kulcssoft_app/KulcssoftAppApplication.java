package com.codecool.kulcssoft_app;

import com.codecool.kulcssoft_app.entity.User;
import com.codecool.kulcssoft_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KulcssoftAppApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(KulcssoftAppApplication.class, args);
    }


    @Bean
    public CommandLineRunner init() {
        return args -> {
            User john = User.builder()
                    .userName("John")
                    .userEmail("john@codecool.com")
                    .build();
            userRepository.save(john);
        };
    }
}
