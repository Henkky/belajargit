package id.co.bca.spring.WebFluxDemo.service;

import id.co.bca.spring.WebFluxDemo.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {
    void create(Employee e);
    Mono<Employee> findById(String id);
    Flux<Employee> findAll();
    Mono<Employee> update(Employee e);
    Mono<Void> delete(String i);
}
