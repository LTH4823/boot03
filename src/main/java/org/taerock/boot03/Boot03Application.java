package org.taerock.boot03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Boot03Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot03Application.class, args);
    }

}

