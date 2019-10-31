package com.me.ch.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fromUser;
    private String toUser;
    private String message;

    public MessageEntity() {
    }

    public MessageEntity(String fromUser, String toUser, String message) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageEntity)) return false;

        MessageEntity messageEntity = (MessageEntity) o;

        if (id != messageEntity.id) return false;
        if (!Objects.equals(fromUser, messageEntity.fromUser)) return false;
        if (!Objects.equals(toUser, messageEntity.toUser)) return false;
        return Objects.equals(message, messageEntity.message);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.fromUser);
        hash = 79 * hash + Objects.hashCode(this.toUser);
        hash = 79 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DbMessage{ ");
        sb.append("id=").append(id);
        sb.append(", from='").append(fromUser).append('\'');
        sb.append(", to='").append(toUser).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
