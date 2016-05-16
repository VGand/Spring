package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.EventManager;
import com.epam.spring.cinema.domain.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class EventMapManager implements EventManager {
    public EventMapManager() {
    }

    public void save(Event event) {
        if (event != null) {
            if (event.getId() == null) {
                Long id = MapDB.getInstance().getNextEventId();
                event.setId(id);
            }
            MapDB.getInstance().getEventMap().put(event.getId(), event);
        }
    }

    public void remove(Long id) {
        if (id != null) {
            MapDB.getInstance().getEventMap().remove(id);
        }
    }

    public Event getById(Long id) {
        if (id != null) {
            return MapDB.getInstance().getEventMap().get(id);
        }
        return null;
    }

    public Event getByName(String name) {
        if (name != null) {
            for (Map.Entry<Long, Event> entry : MapDB.getInstance().getEventMap().entrySet()) {
                if (entry.getValue() != null && name.equals(entry.getValue().getName())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public List<Event> getAll() {
        return new ArrayList<Event>(MapDB.getInstance().getEventMap().values());
    }
}
