package com.epam.spring.cinema.domain.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 4/10/2017.
 */
@XmlRootElement(name = "auditoriumList")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbAuditoriumList {

    @XmlElement(name="auditorium")
    List<JaxbAuditorium> auditoriumList;

    public List<JaxbAuditorium> getAuditoriumList() {
        return auditoriumList;
    }

    public void setAuditoriumList(List<JaxbAuditorium> auditoriumList) {
        this.auditoriumList = auditoriumList;
    }
}
