package id.co.bca.spring.helloworld.controller;

import id.co.bca.spring.helloworld.model.Api;
import id.co.bca.spring.helloworld.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HelloController {

    /*penulisan bean convention nya beda ama nama class,, dia semisal nya managerSpv */
    @Autowired
    @Qualifier("manager")
    Employee employee;

    @Autowired
    Employee karyawan;

    @Value("${spring.message}")
    private String message;

    @GetMapping("/hello")
    public @ResponseBody String hello(){
        return "Hello World";
    }

    @GetMapping("/salary")
    public @ResponseBody String salary(){
        /* otomatis akan ngambil bean yg @Primary, kalau ada @Qualifier ambil qualifier
        return employee.salary();*/
        return karyawan.salary();
    }

    @GetMapping("/message")
    public @ResponseBody String message(){
        return message;
    }

    @Autowired
    Api api;

    @GetMapping("/api")
    public @ResponseBody String api(){
        return "Base URL" + " : " + api.getUrl() +
                "<br>" + "" +
                "DataType" + " : " + api.getDataType();
    }


}
