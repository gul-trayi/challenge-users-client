package com.sidgul.challenge.users.client;

import com.sidgul.challenge.http.client.HttpClient;
import com.sidgul.challenge.http.client.IHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/***
 * Factory to create UsersClient objects
 *
 * TODO: Invert dependency injection
 */
public class UsersClientFactory {
    private static final Logger logger = LoggerFactory.getLogger(UsersClientFactory.class);
    private Properties configuration;

    public UsersClientFactory(Properties configuration) {
        this.configuration = configuration;
    }

    /*** Creates Users Client Instance ***/
    public IUsersClient getUsersClient() {
        logger.debug("Created REST API Users Client Instance");
        IHttpClient httpClient = new HttpClient();
        IUsersUnmarshaller usersUnmarshaller = new UsersUnmarshaller();
        return new UsersClient(this.configuration, httpClient, usersUnmarshaller);
    }
}
