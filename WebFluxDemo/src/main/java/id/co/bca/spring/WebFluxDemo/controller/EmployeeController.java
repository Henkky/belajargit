package id.co.bca.spring.WebFluxDemo.controller;

import id.co.bca.spring.WebFluxDemo.model.Employee;
import id.co.bca.spring.WebFluxDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = {"/create","/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee e){
        employeeService.create(e);
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee e){
        return employeeService.update(e);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id){
        employeeService.delete(id).subscribe();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") String id){
        Mono<Employee> e = employeeService.findById(id);
        return new ResponseEntity<Mono<Employee>>(e, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> findAll(){
        Flux<Employee> emps = employeeService.findAll();
        return emps.delayElements(Duration.ofSeconds(1));
    }
}
