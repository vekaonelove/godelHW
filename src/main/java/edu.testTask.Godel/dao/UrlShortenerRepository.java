package edu.testTask.Godel.dao;

import edu.testTask.Godel.dao.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlEntity, String> {
}