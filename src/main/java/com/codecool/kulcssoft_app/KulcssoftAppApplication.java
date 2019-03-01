package com.codecool.kulcssoft_app;

import com.codecool.kulcssoft_app.entity.User;
import com.codecool.kulcssoft_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class KulcssoftAppApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(KulcssoftAppApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            User john = User.builder()
                    .userName("John")
                    .userEmail("john@codecool.com")
                    .adminEmail("admin@kulcssoft.com")
                    .build();
            userRepository.save(john);

            User jack = User.builder()
                    .userName("Jack")
                    .userEmail("jack@codecool.com")
                    .adminEmail("admin@kulcssoft.com")
                    .build();
            userRepository.save(jack);

            User kate = User.builder()
                    .userName("Kate")
                    .userEmail("kate@codecool.com")
                    .adminEmail("admin@kulcssoft.com")
                    .build();
            userRepository.save(kate);

            User james = User.builder()
                    .userName("James")
                    .userEmail("james@codecool.com")
                    .adminEmail("admin@kulcssoft.com")
                    .build();
            userRepository.save(james);

            User karen = User.builder()
                    .userName("Karen")
                    .userEmail("karen@codecool.com")
                    .adminEmail("admin2@kulcssoft.com")
                    .build();
            userRepository.save(karen);
        };
    }
}
