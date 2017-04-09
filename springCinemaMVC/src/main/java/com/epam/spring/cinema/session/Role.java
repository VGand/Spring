package com.epam.spring.cinema.session;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 * Роль пользователя
 */
public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    String roleSysName;

    Role(String roleSysName) {
        this.roleSysName = roleSysName;
    }

    public String getRoleSysName() {
        return roleSysName;
    }

    public static Role getRoleBySysName(String roleSysName) {
        if (roleSysName != null) {
            for(Role role : values()) {
                if (role.getRoleSysName().equals(roleSysName)) {
                    return role;
                }
            }
        }
        return null;
    }
}
