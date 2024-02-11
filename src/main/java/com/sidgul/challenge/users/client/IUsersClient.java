package com.sidgul.challenge.users.client;

import java.util.List;

public interface IUsersClient {
    public List<User> getUsers(int page);
}
