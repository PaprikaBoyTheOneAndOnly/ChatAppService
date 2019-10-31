package com.me.ch.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, String> {
    @Query(value = "SELECT * FROM account WHERE username = :username and password = :password", nativeQuery = true)
    AccountEntity findValidAccount(
            @Param("username") String username,
            @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO account(username, password) values(:username, :password)", nativeQuery = true)
    void addAccount(@Param("username") String username, @Param("password") String password) throws SQLException;
}
