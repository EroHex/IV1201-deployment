package project.com.Recruitment.repository;

import project.com.Recruitment.model.CompetenceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository file for availability
 * Currently not in use as person handles applications
 */

@Repository
public interface CompetenceProfileRepository extends JpaRepository<CompetenceProfile, Long> {
    
}

