package com.sidgul.challenge.users.client;

import com.sidgul.challenge.http.client.HttpException;
import com.sidgul.challenge.http.client.IHttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/***
 * Concrete implementation of the IUsersClient
 * Uses REST API to get Users data.
 */
public class UsersClient implements IUsersClient {
    private static final Logger logger = LoggerFactory.getLogger(UsersClient.class);
    public static final String USERS_CLIENT_API_URL = "users.client.api.url";
    private final IHttpClient httpClient;
    private IUsersUnmarshaller usersUnmarshaller = new UsersUnmarshaller();
    private String apiUrl;

    public UsersClient(Properties configuration, IHttpClient httpClient, IUsersUnmarshaller usersUnmarshaller) throws UsersClientError {
        validateConfiguration(configuration);
        logger.debug("configuration is valid");
        this.apiUrl = configuration.getProperty(USERS_CLIENT_API_URL);
        logger.debug("UsersClient instantiated with URL " + this.apiUrl);
        this.httpClient = httpClient;
        this.usersUnmarshaller = usersUnmarshaller;
    }

    /***
     * Validates configuration for 'users.client.api.url'
     * @param configuration application configuration - java.util.Properties
     * @throws UsersClientError if configuration is invalid
     */
    private void validateConfiguration(Properties configuration) throws UsersClientError {
        // Validation for null configuration
        if (null == configuration) {
            UsersClientError nullConfigError = new UsersClientError("Configuration is null - cannot create RestApiUsersClient");
            logger.error(nullConfigError.getMessage(), nullConfigError);
            throw (nullConfigError);
        }

        // Validation for no 'users.client.api.url' key in configuration
        if (!configuration.containsKey(USERS_CLIENT_API_URL)) {
            UsersClientError noApiUrlKeyError = new UsersClientError("Configuration does not contain API URL key 'users.client.api.url' - cannot create RestApiUsersClient");
            logger.error(noApiUrlKeyError.getMessage(), noApiUrlKeyError);
            throw (noApiUrlKeyError);
        }

        // Validation for empty URL in configuration
        String apiUrlValue = configuration.getProperty(USERS_CLIENT_API_URL);
        if (null == apiUrlValue || apiUrlValue.trim().length() == 0) {
            UsersClientError emptyApiUrlKeyError = new UsersClientError("Configuration does not contain a value for 'users.client.api.url' - cannot create RestApiUsersClient");
            logger.error(emptyApiUrlKeyError.getMessage(), emptyApiUrlKeyError);
            throw (emptyApiUrlKeyError);
        }


    }

    @Override
    /***
     * Concrete implementation of IUsersClient#getUsers.
     * Uses REST API URL to fetch paginated data
     * @param page - page number
     */
    public List<User> getUsers(int page) {
        List<User> users;
        try {
            logger.debug("Fetching users data from URL " + this.apiUrl);
            String responseBody = this.httpClient.GET(this.apiUrl, null);
            users = this.usersUnmarshaller.unmarshalUsers(responseBody);
            logger.debug("successfully unmarshalled users data from URL " + this.apiUrl);

        } catch (HttpException httpException) {
            throw new UsersClientError(String.format("Error while getting users data from  URL %s. See inner error for details.", this.apiUrl), httpException);
        }
        return users;
    }
}

