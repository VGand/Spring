package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.dao.EventManager;
import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.dao.map.MapDB;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private TicketManager ticketManager;

    @Value("${coefficientForHighRating}")
    private Double coefficientForHighRating;

    @Value("${coefficientForVipSits}")
    private Double coefficientForVipSits;

    @Override
    public void add(String name, double basePrice, Auditorium auditorium, EventRating rating,
                    LocalDateTime date, String description) {
        Event event = new Event();
        Long id = mapDB.getNextEventId();
        event.setId(id);
        event.setDate(date);
        event.setAuditorium(auditorium);
        event.setBasePrice(basePrice);
        event.setName(name);
        event.setRating(rating);
        event.setDescription(description);

        List<Long> ticketIdList = generateTicketList(auditorium, event);
        event.setTicketIdList(ticketIdList);

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
            if (from.isAfter(event.getDate()) || to.isBefore(event.getDate())) {
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
            allEventsDateTime.add(event.getDate());
        }
        //Получаем следующее значение даты
        LocalDateTime nextDate = allEventsDateTime.ceiling(from);

        //Добавляем все события которые происходят в полученную дату
        List<Event> resultEvents = new ArrayList<Event>();
        for(Event event : events) {
            if (nextDate.equals(event.getDate())) {
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

    private List<Long> generateTicketList(Auditorium auditorium, Event event) {
        List<Long> ticketList = new ArrayList<>();

        if (auditorium.getNumberOfSeats() != null) {
            for (long i = 0; i < auditorium.getNumberOfSeats(); i++) {
                Ticket ticket = new Ticket();
                ticket.setEventId(event.getId());
                ticket.setId(mapDB.getNextTicketId());
                ticket.setSeat(i);

                Boolean isVip = auditorium.getVipSeats().contains(i);
                ticket.setVip(isVip);

                Double price = getPrice(event, isVip);
                ticket.setTicketPrice(price);

                ticketManager.save(ticket);

                ticketList.add(ticket.getId());
            }
        }

        return ticketList;
    }

    private Double getPrice(Event event, Boolean isVip) {
        Double price = 0d;
        if (isVip) {
            price += event.getBasePrice() * coefficientForVipSits;
        } else {
            price += event.getBasePrice();
        }
        if (event.getRating() == EventRating.HIGH) {
            price = price * coefficientForHighRating;
        }

        return price;
    }
}
