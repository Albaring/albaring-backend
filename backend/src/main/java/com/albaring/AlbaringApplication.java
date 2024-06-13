package com.albaring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AlbaringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbaringApplication.class, args);

    }
}

@RestController
@RequestMapping("/api/health")
class Health {

    @GetMapping
    String health() {
        return "UP";
    }

}
