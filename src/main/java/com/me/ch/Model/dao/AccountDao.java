package com.me.ch.Model.dao;

import com.me.ch.Model.Account;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequestScope
@Component("accountDao")
public class AccountDao extends DaoGeneric<Account> {
    public AccountDao() {
        super();
    }

    @Override
    public List<Account> getAll() {
        ArrayList<Account> accounts = new ArrayList<>();

        try (Statement stmt = super.connection.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM account");

            int foundAccounts = 0;
            while (result.next()) {
                accounts.add(new Account(
                        result.getString("username"),
                        result.getString("password")));
                foundAccounts++;
            }

            logger.debug("Loaded accounts: " + foundAccounts);
        } catch (SQLException e) {
            logger.error("Exception in statement: ", e);
        }
        return accounts;
    }

    @Override
    public void save(Account account) {
        try (PreparedStatement stmt = super.connection.prepareStatement("INSERT INTO account VALUES (?, ?)")) {
            stmt.setString(0, account.getUsername());
            stmt.setString(1, account.getPassword());

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                if (result.getInt(1) != 0) {
                    logger.warn("There are " + result.getInt(1) + " inserted rows while inserting Account!");
                } else {
                    logger.debug("Inserted Account: " + account.toString());
                }
            }
        } catch (SQLException e) {
            logger.error("Exception in statement: ", e);
        }
    }

    @Override
    public void update(Account account, String userToUpdate) {
        try (PreparedStatement stmt = super.connection.prepareStatement("UPDATE account SET username = ?, password = ? WHERE username = ?")) {
            stmt.setString(0, account.getUsername());
            stmt.setString(1, account.getPassword());
            stmt.setString(2, userToUpdate);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                if (result.getInt(1) != 0) {
                    logger.warn("There are " + result.getInt(1) + " updated accounts while updating Account with username = " + userToUpdate + "!");
                } else {
                    logger.debug("Updated Account: " + userToUpdate);
                }
            }
        } catch (SQLException e) {
            logger.error("Exception in statement: ", e);
        }
    }

    @Override
    public void delete(Account account) {
        try (PreparedStatement stmt = super.connection.prepareStatement("DELETE FROM account WHERE username = ?")) {
            stmt.setString(0, account.getUsername());

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                if (result.getInt(1) != 0) {
                    logger.warn("There are " + result.getInt(1) + " deleted accounts while deleting Account with username = " + account.getUsername() + "!");
                } else {
                    logger.debug("Deleted Account: " + account.getUsername());
                }
            }
        } catch (SQLException e) {
            logger.error("Exception in statement: ", e);
        }
    }

}
