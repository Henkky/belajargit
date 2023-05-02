package id.co.bca.spring.helloworld.service;

import id.co.bca.spring.helloworld.model.Department;
import id.co.bca.spring.helloworld.repository.DepartmentSDJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentSDJRepository sdjRepository;

    public void insert(Department department){sdjRepository.save(department);}

    public void update(Department department){sdjRepository.save(department);}

    public void delete(Department department){sdjRepository.deleteById(department.getId());}

    public List<Department> all(){ return sdjRepository.findAll();}
}
