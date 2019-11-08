package com.me.ch.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<FileEntity, Integer> {
    @Query(value = "SELECT * FROM file WHERE from_user = :username or to_user = :username", nativeQuery = true)
    Iterable<FileEntity> getAllFiles(@Param("username") String username);
}
