package com.me.ch.Model;

public class RequestFailed {
    private RequestCode code;
    private String reason;

    public RequestFailed() {
    }

    public RequestFailed(RequestCode code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public RequestCode getCode() {
        return code;
    }

    public void setCode(RequestCode code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public enum RequestCode {
        FORBIDDEN(403);

        private int value;

        RequestCode(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}


