package id.co.bca.spring.helloworld.model;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfig {
    @PostConstruct
    public void test(){
        System.out.println("In Development Environment");
    }
}
