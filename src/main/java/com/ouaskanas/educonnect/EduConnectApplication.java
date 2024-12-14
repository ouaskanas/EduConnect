package com.ouaskanas.educonnect;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EduConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduConnectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User user = User.builder()
                    .firstname("Anas")
                    .lastname("Oaskanas")
                    .role(Role.TEACHER)
                    .password("hello")
                    .email("anasouaskar2003@gmail.com")
                    .build();
            userRepository.save(user);
        };
    }
}
