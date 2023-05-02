package id.co.bca.spring.helloworld.model;

import org.springframework.stereotype.Component;

@Component
public class Manager implements Employee{
    @Override
    public String salary(){
        return "Rp. 16 juta";
    }
}
