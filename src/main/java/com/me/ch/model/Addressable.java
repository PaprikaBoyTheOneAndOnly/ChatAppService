package com.me.ch.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Addressable {
    protected String from;
    protected String to;
    protected LocalDateTime timeSent;

    public Addressable(String from, String to, LocalDateTime timeSent) {
        this.from = from;
        this.to = to;
        this.timeSent = timeSent;
    }

    public Addressable() {
        this("", "", LocalDateTime.now());
    }

    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Addressable)) return false;

        Addressable that = (Addressable) o;

        if (!Objects.equals(from, that.from)) return false;
        if (!Objects.equals(to, that.to)) return false;
        return Objects.equals(timeSent, that.timeSent);
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (timeSent != null ? timeSent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Addressable{");
        sb.append("from='").append(from).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append(", timeSent=").append(timeSent);
        sb.append('}');
        return sb.toString();
    }
}
