package com.azino.project.repository;

import com.azino.project.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String userName);

    User deleteByName(String userName);

    /*@Query("select sessions from User.activeSessions as sessions")
    List<String> getAllActiveSessionsFromUser();*/

    /*@Query("delete from User.activeSessions as sessions where sessions = :sessionId")
    void deleteSessionFromSessions(String sessionId);*/

    /*@Query("in")
    User addSessionToSessions();*/
}
