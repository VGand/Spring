package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.dao.EventManager;
import com.epam.spring.cinema.dao.map.MapDB;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private MapDB mapDB;

    @Autowired
    private EventManager eventManager;

    @Override
    public void add(String name, double basePrice, NavigableMap<LocalDateTime, Auditorium> auditoriums, EventRating rating) {
        Event event = new Event();
        Long id = mapDB.getNextEventId();
        event.setId(id);
        event.setAirDates(auditoriums.navigableKeySet());
        event.setAuditoriums(auditoriums);
        event.setBasePrice(basePrice);
        event.setName(name);
        event.setRating(rating);
        save(event);
    }

    public void save(Event event) {
        eventManager.save(event);
    }

    public void remove(Long id) {
        eventManager.remove(id);
    }

    public Event getById(Long id) {
        return eventManager.getById(id);
    }

    public Event getByName(String name) {
        return eventManager.getByName(name);
    }

    public List<Event> getAll() {
        return eventManager.getAll();
    }

    public List<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        if (from == null && to == null) {
            return eventManager.getAll();
        } else if (from == null) {
            from = LocalDateTime.now();
        } else if (to == null) {
            to = LocalDateTime.now();
        }

        List<Event> events = new ArrayList<Event>(eventManager.getAll());
        Iterator<Event> iterator = events.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            Boolean isContains = false;
            for (LocalDateTime dateTime : event.getAirDates()) {
                if (from.isBefore(dateTime) && to.isAfter(dateTime)) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                iterator.remove();
            }
        }
        return events;

    }

    public List<Event> getNextEvent(LocalDateTime from) {
        if (from == null) {
            from = LocalDateTime.now();
        }
        //Собираем все возможные даты событий в одно множество
        NavigableSet<LocalDateTime> allEventsDateTime = new TreeSet<LocalDateTime>();
        List<Event> events = new ArrayList<Event>(eventManager.getAll());
        for(Event event : events) {
            allEventsDateTime.addAll(event.getAirDates());
        }
        //Получаем следующее значение даты
        LocalDateTime nextDate = allEventsDateTime.ceiling(from);

        //Добавляем все события которые происходят в полученную дату
        List<Event> resultEvents = new ArrayList<Event>();
        for(Event event : events) {
            if (event.getAirDates().contains(nextDate)) {
                resultEvents.add(event);
            }
        }
        return resultEvents;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void setMapDB(MapDB mapDB) {
        this.mapDB = mapDB;
    }
}
