package com.sidgul.challenge.users.client;

import java.util.List;

public interface IUsersClient {
    List<User> getUsers(int page);
}
