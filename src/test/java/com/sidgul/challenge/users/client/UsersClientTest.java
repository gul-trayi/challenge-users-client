package com.sidgul.challenge.users.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Properties;

class UserClientTest{

    public static final int EXPECTED_USERS_COUNT_PAGE0 = 6;
    public static final String EXPECTED_USER0_PAGE0_FIRSTNAME = "George";
    private static IUsersClient usersClient;
    private static Properties configuration = new Properties();

    @BeforeAll
    static void setUp() {
        // initialize users client
       configuration = new Properties();
        configuration.setProperty("users.client.api.url", "https://reqres.in/api/users");

        UsersClientFactory usersClientFactory = new UsersClientFactory(configuration);
        usersClient = usersClientFactory.getUsersClient();
    }

    @AfterAll
    static void tearDown() {
        usersClient = null;
        configuration = null;
    }

    /***
     * Tests if first page returns EXPECTED_USERS_COUNT_PAGE0
     * and if the first user's first name is EXPECTED_USER0_PAGE0_FIRSTNAME
     */
    @Test
    void getUsersFromFirstPageTest() {
        List<User> usersFromFirstPage = usersClient.getUsers(0);
        // Assuming the first page always returns  EXPECTED_USERS_COUNT_PAGE0 users as per the current API behavior
        assertEquals(EXPECTED_USERS_COUNT_PAGE0, usersFromFirstPage.size(),
                "The number of users in the first page should be " + EXPECTED_USERS_COUNT_PAGE0);
        // Assuming the first page always returns a user with first name EXPECTED_USER0_PAGE0_FIRSTNAME as per the current API behavior
        assertEquals(EXPECTED_USER0_PAGE0_FIRSTNAME, usersFromFirstPage.get(0).getFirstName(),
                "The first name of the first user in the first page should be " + EXPECTED_USER0_PAGE0_FIRSTNAME);

    }
}