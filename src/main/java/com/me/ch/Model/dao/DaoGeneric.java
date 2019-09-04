package com.me.ch.Model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoGeneric<T> {
    protected Connection connection;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public DaoGeneric() {
        try {
            logger.debug("Trying to connect");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_app?user=root&password=");
            logger.debug("Successfully done: CONNECT");
        } catch (SQLException e) {
            logger.error("Connection failed: ", e);
        }
    }

    public abstract List<T> getAll();

    public abstract void save(T t);

    public abstract void update(T t, String id);

    public abstract void delete(T t);

    @PreDestroy
    public void closeConnection() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            logger.debug("Trying to close: ");
            this.connection.close();
            logger.debug("Successfully done: CLOSE");
        }
    }
}
