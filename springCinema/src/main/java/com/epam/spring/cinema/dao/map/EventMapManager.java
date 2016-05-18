package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.EventManager;
import com.epam.spring.cinema.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class EventMapManager implements EventManager {

    @Autowired
    private MapDB mapDB;

    public void save(Event event) {
        if (event != null) {
            if (event.getId() == null) {
                Long id = mapDB.getNextEventId();
                event.setId(id);
            }
            mapDB.getEventMap().put(event.getId(), event);
        }
    }

    public void remove(Long id) {
        if (id != null) {
            mapDB.getEventMap().remove(id);
        }
    }

    public Event getById(Long id) {
        if (id != null) {
            return mapDB.getEventMap().get(id);
        }
        return null;
    }

    public Event getByName(String name) {
        if (name != null) {
            for (Map.Entry<Long, Event> entry : mapDB.getEventMap().entrySet()) {
                if (entry.getValue() != null && name.equals(entry.getValue().getName())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public List<Event> getAll() {
        return new ArrayList<Event>(mapDB.getEventMap().values());
    }
}
