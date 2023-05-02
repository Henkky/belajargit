package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Primary
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
