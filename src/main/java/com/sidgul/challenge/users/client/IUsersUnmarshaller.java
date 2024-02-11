package com.sidgul.challenge.users.client;

import java.util.List;

/***
 * Interface for marshalling users data
 */
public interface IUsersUnmarshaller {
    /***
     * Unmarshals users data into a list of user objects
     * @param usersData user data as a String
     * @return list of user objects
     */
    public List<User> unmarshalUsers(String usersData);

}
