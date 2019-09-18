package com.me.ch.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<DbMessage, String> {
    @Query(value = "SELECT * FROM message WHERE fromUser = :username or toUser = :username", nativeQuery = true)
    Iterable<DbMessage> getAllMessages(@Param("username") String username);

}
