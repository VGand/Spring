package com.epam.spring.cinema.domain;

import java.util.Collections;
import java.util.Set;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class Auditorium {

    private Long id;

    private String name;

    private long numberOfSeats;

    private Set<Long> vipSeats = Collections.emptySet();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Long> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Long> vipSeats) {
        this.vipSeats = vipSeats;
    }
}
