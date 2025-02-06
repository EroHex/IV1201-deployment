package project.com.Recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.com.Recruitment.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
