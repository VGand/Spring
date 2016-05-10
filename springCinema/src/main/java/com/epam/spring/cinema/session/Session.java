package com.epam.spring.cinema.session;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 * Описывает сессию пользователя
 */
public class Session {

    private Role role;
    private String userLogin;

    private static Session session;

    public Session(Role role, String userLogin) {
        this.role = role;
        this.userLogin = userLogin;

        session = this;
    }

    public static Session currentSession() {
        return session;
    }

    public Role getRole() {
        return role;
    }

    public String getUserLogin() {
        return userLogin;
    }
}
