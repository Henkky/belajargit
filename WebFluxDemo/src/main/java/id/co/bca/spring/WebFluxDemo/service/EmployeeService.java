package id.co.bca.spring.WebFluxDemo.service;

import id.co.bca.spring.WebFluxDemo.model.Employee;
import id.co.bca.spring.WebFluxDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void create(Employee e) {
        employeeRepository.save(e).subscribe();
    }

    @Override
    public Mono<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> update(Employee e) {
        return employeeRepository.save(e);
    }

    @Override
    public Mono<Void> delete(String id) {
        return employeeRepository.deleteById(id);
    }
}
