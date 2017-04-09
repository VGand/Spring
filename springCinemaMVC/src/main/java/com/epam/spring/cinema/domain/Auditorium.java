package com.epam.spring.cinema.domain;

import java.util.Collections;
import java.util.Set;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class Auditorium {

    private Long id;

    private String name;

    private Long numberOfSeats;

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

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Set<Long> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Long> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditorium)) return false;

        Auditorium that = (Auditorium) o;

        if (numberOfSeats != that.numberOfSeats) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return vipSeats != null ? vipSeats.equals(that.vipSeats) : that.vipSeats == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (numberOfSeats ^ (numberOfSeats >>> 32));
        result = 31 * result + (vipSeats != null ? vipSeats.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", vipSeats=" + vipSeats +
                '}';
    }
}
