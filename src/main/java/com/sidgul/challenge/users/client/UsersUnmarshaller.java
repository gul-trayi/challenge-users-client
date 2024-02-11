package com.sidgul.challenge.users.client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/***
 * Concrete implementation of IUsersUnmarshaller for unmarshalling JSON data (say, from the REST API)
 */
public class UsersUnmarshaller implements IUsersUnmarshaller {

    public static final String FIELD_DATA = "data";
    public static final String FIELD_ID = "id";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_FIRST_NAME = "first_name";
    public static final String FIELD_LAST_NAME = "last_name";

    @Override
    public List<User> unmarshalUsers(String usersData) {
        List<User> users = new ArrayList<User>();
        JSONObject jsonObject = new JSONObject(usersData);
        JSONArray jsonArray = jsonObject.getJSONArray(FIELD_DATA);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userJson = jsonArray.getJSONObject(i);
            User user = new User(
                    userJson.getInt(FIELD_ID),
                    userJson.getString(FIELD_EMAIL),
                    userJson.getString(FIELD_FIRST_NAME),
                    userJson.getString(FIELD_LAST_NAME)
            );
            users.add(user);
        }
        return users;
    }
}
