package com.epam.spring.cinema.domain;

import com.epam.spring.cinema.session.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class User {

    public User() {

    }

    public User(Role role) {
        this.role = role;
    }

    public User(String login, Role role) {
        this.login = login;
        this.role = role;
    }

    private String firstName;

    private List<Ticket> purchasedTickets = new ArrayList<Ticket>();

    private String lastName;

    private String email;

    private String login;

    private Role role;

    private LocalDate birthday;

    public Role getRole() {
        return role;
    }

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

    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(List<Ticket> purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
