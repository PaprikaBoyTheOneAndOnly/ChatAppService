package com.me.ch.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, Integer> {
    @Query(value = "SELECT * FROM message WHERE from_user = :username or to_user = :username", nativeQuery = true)
    Iterable<MessageEntity> getAllMessages(@Param("username") String username);

}
