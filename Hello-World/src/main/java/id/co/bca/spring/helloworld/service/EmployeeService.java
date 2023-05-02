package id.co.bca.spring.helloworld.service;

import id.co.bca.spring.helloworld.model.EmployeeModel;
import id.co.bca.spring.helloworld.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    /*@Qualifier("employeeRepoJDBCBasic")*/
    @Qualifier("employeeRepoHibernate")
    private IEmployeeRepository iEmployeeRepository;

    public void insert(EmployeeModel employee) { iEmployeeRepository.create(employee); }
    public void update(EmployeeModel employee) { iEmployeeRepository.update(employee); }
    public void delete(EmployeeModel employee) { iEmployeeRepository.deleteUnique(employee); }
    public EmployeeModel findTheEmployee(EmployeeModel employee) { return iEmployeeRepository.retrieveUnique(employee); }
    public List<EmployeeModel> allEmployees() { return iEmployeeRepository.retrieveAll(); }
}
