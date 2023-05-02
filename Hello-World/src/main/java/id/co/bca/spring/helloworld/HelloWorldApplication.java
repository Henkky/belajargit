package id.co.bca.spring.helloworld;

import id.co.bca.spring.helloworld.controller.HelloController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

	@Autowired
	HelloController helloController;

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	/*bisa juga dengan void tes(@Autowired HelloController helloController) {} */
	void tes(){
		helloController.hello();
	}

}
