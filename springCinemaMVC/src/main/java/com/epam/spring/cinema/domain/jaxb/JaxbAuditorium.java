package com.epam.spring.cinema.domain.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey_Vaganov on 4/10/2017.
 */
@XmlRootElement(name = "auditorium")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbAuditorium {

    private String name;

    private Long numberOfSeats;

    @XmlElementWrapper(name="vipSeats")
    @XmlElement(name="seat")
    private Set<Long> vipSeats;

    @XmlElementWrapper(name="eventList")
    @XmlElement(name="event")
    private List<JaxbEvent> eventList;

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

    public List<JaxbEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<JaxbEvent> eventList) {
        this.eventList = eventList;
    }
}
