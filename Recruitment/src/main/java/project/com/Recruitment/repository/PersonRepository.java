package project.com.Recruitment.repository;

import org.springframework.data.jpa.repository.EntityGraph;
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
     * @param roleId   1 or 2, 1 = recruiter, 2 = applicants
     * @return  List of persons with param role_id
     */
    List<Person> findByRoleId(Long roleId);

    /**
     * Finds and returns a person object for specific application info
     * @param personId   id to identify application by
     * @return  Person if any is found otherwise optional object which is empty
     */
    Optional<Person> findByPersonId(Long personId); 
    Optional<Person> findByEmail(String email);
    Optional<Person> findByPnr(String pnr);
}
