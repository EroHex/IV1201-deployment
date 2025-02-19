package project.com.Recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.com.Recruitment.model.Person;
import java.util.*;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    /**
     * Finds and returns a person with username
     * @param username  the username to search for
     * @return  Person if any is found otherwise optional object which is empty
     */
    Optional<Person> findByUsername(String username); 

    /**
     * Finds and returns a list of all persons with role_id
     * @param role_id   1 or 2, 1 = recruiter, 2 = applicants
     * @return  List of persons with param role_id
     */
    List<Person> findByRoleId(int role_id);
}
