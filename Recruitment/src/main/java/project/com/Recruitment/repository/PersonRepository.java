package project.com.Recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.com.Recruitment.model.Person;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username); 
}
