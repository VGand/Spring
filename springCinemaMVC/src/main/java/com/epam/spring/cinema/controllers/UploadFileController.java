package com.epam.spring.cinema.controllers;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.domain.jaxb.JaxbAuditorium;
import com.epam.spring.cinema.domain.jaxb.JaxbAuditoriumList;
import com.epam.spring.cinema.domain.jaxb.JaxbEvent;
import com.epam.spring.cinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * Created by Andrey_Vaganov on 4/10/2017.
 */
@Controller
@RequestMapping("/upload")
public class UploadFileController {

    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;

    @Autowired
    AuditoriumManager auditoriumManager;

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.POST)
    public String importFromFile(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try (InputStream is = file.getInputStream()) {
                JaxbAuditoriumList auditoriumList = (JaxbAuditoriumList) jaxb2Marshaller.unmarshal(new StreamSource(file.getInputStream()));

                for(JaxbAuditorium jaxbAuditorium : auditoriumList.getAuditoriumList()) {
                    Auditorium auditorium = new Auditorium();
                    auditorium.setName(jaxbAuditorium.getName());
                    auditorium.setNumberOfSeats(jaxbAuditorium.getNumberOfSeats());
                    auditorium.setVipSeats(jaxbAuditorium.getVipSeats());

                    auditoriumManager.add(auditorium);

                    for(JaxbEvent jaxbEvent : jaxbAuditorium.getEventList()) {
                        String name = jaxbEvent.getName();
                        Double pasePrice = jaxbEvent.getBasePrice();
                        LocalDateTime dateTime = jaxbEvent.getDate();
                        String description = jaxbEvent.getDescription();
                        EventRating eventRating = EventRating.getRatingBySysName(jaxbEvent.getRating());

                        eventService.add(name, pasePrice, auditorium, eventRating, dateTime, description);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/";
    }
}
