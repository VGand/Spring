package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 *
 */
@Component
public class MapDB {
    //Мапа с пользователями, ключ - логин пользователя
    private Map<String, User> userMap;

    //Мапа с местами проведения мироприятий, ключ id
    private Map<Long, Auditorium> auditoriumMap;

    //Хранит следующий идентификатор для мест
    private Long auditoriumSequence;

    //Мапа с описанием мироприятий, ключ id
    private Map<Long, Event> eventMap;

    //Хранит следующий идентификатор для событий
    private Long eventSequence;

    //Мапа с билетами на мироприятие, ключ id
    private Map<Long, Ticket> ticketMap;

    //Хранит следующий идентификатор для билетов
    private Long ticketSequence;

    public MapDB() {
        userMap = new HashMap<String, User>();
        auditoriumMap = new HashMap<Long, Auditorium>();
        eventMap = new HashMap<Long, Event>();
        ticketMap = new HashMap<Long, Ticket>();

        auditoriumSequence = 0L;
        eventSequence = 0L;
        ticketSequence = 0L;
    }

    public Long getNextAuditoriumId() {
        return auditoriumSequence++;
    }

    public Long getNextEventId() {
        return eventSequence++;
    }

    public Long getNextTicketId() {
        return ticketSequence++;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public Map<Long, Auditorium> getAuditoriumMap() {
        return auditoriumMap;
    }

    public Map<Long, Event> getEventMap() {
        return eventMap;
    }

    public Map<Long, Ticket> getTicketMap() {
        return ticketMap;
    }
}
