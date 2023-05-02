package id.co.bca.spring.WebFluxDemo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
@Setter
@Getter
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
