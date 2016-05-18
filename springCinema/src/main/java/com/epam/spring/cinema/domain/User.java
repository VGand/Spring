package com.epam.spring.cinema.domain;

import com.epam.spring.cinema.session.Role;

import java.time.LocalDate;
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

    private List<Ticket> luckyTickets = new ArrayList<>();

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

    public List<Ticket> getLuckyTickets() {
        return luckyTickets;
    }

    public void setLuckyTickets(List<Ticket> luckyTickets) {
        this.luckyTickets = luckyTickets;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (purchasedTickets != null ? !purchasedTickets.equals(user.purchasedTickets) : user.purchasedTickets != null)
            return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (!login.equals(user.login)) return false;
        if (role != user.role) return false;
        return birthday != null ? birthday.equals(user.birthday) : user.birthday == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (purchasedTickets != null ? purchasedTickets.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + login.hashCode();
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", purchasedTickets=" + purchasedTickets +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", birthday=" + birthday +
                '}';
    }
}
