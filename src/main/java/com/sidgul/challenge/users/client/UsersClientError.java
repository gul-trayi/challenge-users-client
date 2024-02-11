package com.sidgul.challenge.users.client;

public class UsersClientError extends RuntimeException {
    public UsersClientError() {
    }

    public UsersClientError(String message) {
        super(message);
    }

    public UsersClientError(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersClientError(Throwable cause) {
        super(cause);
    }

    public UsersClientError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
