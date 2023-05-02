package id.co.bca.spring.WebFluxDemo.repository;

import id.co.bca.spring.WebFluxDemo.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

}
