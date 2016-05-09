package com.epam.spring.cinema.domain;

import com.epam.spring.cinema.session.Role;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class User {

    private String firstName;

    private String lastName;

    private String email;

    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
