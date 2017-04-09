package com.epam.spring.cinema.domain;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class Ticket {

    private Long id;

    private User user;

    private String userLogin;

    private Event event;

    private Long eventId;

    private Long seat;

    private Double ticketPrice;

    private Boolean isLucky;

    private Boolean vip;

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Boolean getLucky() {
        return isLucky;
    }

    public void setLucky(Boolean lucky) {
        isLucky = lucky;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (id != null ? !id.equals(ticket.id) : ticket.id != null) return false;
        if (user != null ? !user.equals(ticket.user) : ticket.user != null) return false;
        if (!event.equals(ticket.event)) return false;
        if (!seat.equals(ticket.seat)) return false;
        return ticketPrice != null ? ticketPrice.equals(ticket.ticketPrice) : ticket.ticketPrice == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + event.hashCode();
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
                ", seat=" + seat +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
