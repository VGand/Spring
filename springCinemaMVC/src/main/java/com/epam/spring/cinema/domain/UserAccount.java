package com.epam.spring.cinema.domain;

/**
 * Created by Andrey_Vaganov on 4/19/2017.
 */
public class UserAccount {

    private String userLogin;
    private Double availableAmount;

    public UserAccount(String userLogin, Double availableAmount) {
        this.userLogin = userLogin;
        this.availableAmount = availableAmount;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }
}
