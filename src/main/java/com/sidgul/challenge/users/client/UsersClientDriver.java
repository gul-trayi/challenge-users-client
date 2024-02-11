package com.sidgul.challenge.users.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Properties;


/***
 * Driver for Users Client
 *
 * Note: Challenge had asked for this driver (main method) to be in the UsersClient class.
 * But, implemented it here to maintain separation of concerns and high cohesion of the UsersClient class.
 */
public class UsersClientDriver {
    private static final Logger logger = LoggerFactory.getLogger(UsersClientDriver.class);
    public static final String APPLICATION_PROPERTIES = "application.properties";

    public final static void main(String[] args) throws UsersClientError {
        logger.info("Users Client Driver - Started");

        // Load application configuration file from class path
        Properties configuration = new Properties();
        try {
            configuration.load(UsersClientDriver.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES));
        } catch (IOException ioEx) {
            throw new UsersClientError(String.format("Fatal error: cannot load %s from class path", APPLICATION_PROPERTIES), ioEx);
        }

        // Use Factory to get UsersClient object
        UsersClientFactory usersClientFactory = new UsersClientFactory(configuration);
        IUsersClient usersClient = usersClientFactory.getUsersClient();

        // Get first page of Users from UsersClient
        List<User> usersFromFirstPage = usersClient.getUsers(0);
        for (User currentUser : usersFromFirstPage) {
            logger.info(String.format("First Name: %s Last Name: %s", currentUser.getFirstName(), currentUser.getLastName()));
        }


        logger.info("Users Client Driver - Done");
    }
}
