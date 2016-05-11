package com.epam.spring.cinema.domain;

import java.time.LocalDateTime;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class Ticket {

    private Long id;

    private User user;

    private Event event;

    private LocalDateTime dateTime;

    private Long seat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }
}
