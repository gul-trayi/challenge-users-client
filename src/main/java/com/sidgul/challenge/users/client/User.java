package com.sidgul.challenge.users.client;

/***
 * User Model Class for the Users Client
 *
 * Abstracts user details for the users client
 *
 * Note:
 * - Data contains PII
 * - Objects are immutable once created
 */
public class User {
    private final int id;
    private final String email;
    private final String firstName;
    private final String lastName;

    // Constructor
    public User(int id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // toString method for easy printing
    // TODO: need to remove because data contains PII
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}


