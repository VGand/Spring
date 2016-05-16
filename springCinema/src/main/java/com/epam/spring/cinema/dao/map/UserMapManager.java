package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.UserManager;
import com.epam.spring.cinema.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class UserMapManager implements UserManager {

    @Autowired
    private UserMapManager(@Qualifier("administrator") User administrator) {
        MapDB.getInstance().getUserMap().put(administrator.getLogin(), administrator);
    }

    public void save(User user) {
        if (user != null && user.getLogin() != null) {
            MapDB.getInstance().getUserMap().put(user.getLogin(), user);
        }
    }

    public void remove(String login) {
        if (login != null) {
            MapDB.getInstance().getUserMap().remove(login);
        }
    }

    public User getByLogin(String login) {
        if (login != null) {
            return MapDB.getInstance().getUserMap().get(login);
        }
        return null;
    }

    public User getByEmail(String email) {
        if (email != null) {
            for(Map.Entry<String, User> entry : MapDB.getInstance().getUserMap().entrySet()) {
                if (entry.getValue() != null && email.equals(entry.getValue().getEmail())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public List<User> getAll() {
        return new ArrayList<User>(MapDB.getInstance().getUserMap().values());
    }
}
