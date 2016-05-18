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

    private Double ticketPrice;

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

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (id != null ? !id.equals(ticket.id) : ticket.id != null) return false;
        if (user != null ? !user.equals(ticket.user) : ticket.user != null) return false;
        if (!event.equals(ticket.event)) return false;
        if (!dateTime.equals(ticket.dateTime)) return false;
        if (!seat.equals(ticket.seat)) return false;
        return ticketPrice != null ? ticketPrice.equals(ticket.ticketPrice) : ticket.ticketPrice == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + event.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + seat.hashCode();
        result = 31 * result + ticketPrice.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", event=" + event +
                ", dateTime=" + dateTime +
                ", seat=" + seat +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
