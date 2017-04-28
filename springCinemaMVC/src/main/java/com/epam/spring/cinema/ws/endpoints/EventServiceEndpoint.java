package com.epam.spring.cinema.ws.endpoints;

import com.epam.cinema_web_service.GetEventRequest;
import com.epam.cinema_web_service.RemoveEventRequest;
import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.service.EventService;
import com.epam.spring.cinema.util.CinemaDateUtils;
import com.springcinema.cinema_web_service.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 4/27/2017.
 */
@Endpoint
public class EventServiceEndpoint {

    @Autowired
    private EventService eventService;

    @PayloadRoot(namespace = CinemaConstants.NAMECPACE_URI, localPart = "getAllEvent")
    @ResponsePayload
    public List<com.epam.cinema_web_service.Event> getAll() {
        List<com.epam.cinema_web_service.Event> result = new ArrayList<>();
        List<Event> events = eventService.getAll();

        for(Event event : events) {
            com.epam.cinema_web_service.Event jaxbEvent = toJaxbEvent(event);
            result.add(jaxbEvent);
        }

        return result;
    }

    @PayloadRoot(namespace = CinemaConstants.NAMECPACE_URI, localPart = "getEventByParams")
    @ResponsePayload
    public com.epam.cinema_web_service.Event getByParams(@RequestPayload GetEventRequest eventRequest) {
        Event event = null;
        if (!StringUtils.isEmpty(eventRequest.getId())) {
            event = eventService.getById(eventRequest.getId());
        } else if (!StringUtils.isEmpty(eventRequest.getName())){
            event = eventService.getByName(eventRequest.getName());
        }

        com.epam.cinema_web_service.Event result = null;
        if (event != null) {
            result = toJaxbEvent(event);
        }

        return result;
    }

    @PayloadRoot(namespace = CinemaConstants.NAMECPACE_URI, localPart = "saveEvent")
    @ResponsePayload
    public EventResponse save(@RequestPayload com.epam.cinema_web_service.Event event) {

        if (!StringUtils.isEmpty(event.getId())) {
            eventService.save(toEvent(event));
        } else {
            eventService.add(event.getName(),
                    event.getBasePrice(),
                    null,
                    EventRating.getRatingBySysName(event.getRating()),
                    CinemaDateUtils.getDateTimeByFormat(event.getDate(), CinemaConstants.VIEW_DATE_TIME_FORMAT),
                    event.getDescription()
            );
        }
        return new EventResponse();
    }

    @PayloadRoot(namespace = CinemaConstants.NAMECPACE_URI, localPart = "removeEvent")
    @ResponsePayload
    public EventResponse remove(@RequestPayload RemoveEventRequest removeEventRequest) {
        eventService.remove(removeEventRequest.getId());

        return new EventResponse();
    }

    private com.epam.cinema_web_service.Event toJaxbEvent(Event event) {
        com.epam.cinema_web_service.Event jaxbEvent = new com.epam.cinema_web_service.Event();
        jaxbEvent.setId(event.getId());
        jaxbEvent.setBasePrice(event.getBasePrice());
        jaxbEvent.setDate(CinemaDateUtils.dateToString(event.getDate(), CinemaConstants.VIEW_DATE_TIME_FORMAT));
        jaxbEvent.setDescription(event.getDescription());
        jaxbEvent.setName(event.getName());
        jaxbEvent.setRating(event.getRating().getSysName());

        return jaxbEvent;
    }

    private Event toEvent(com.epam.cinema_web_service.Event jaxbEvent) {
        Event event = new Event();
        event.setBasePrice(jaxbEvent.getBasePrice());
        event.setDescription(jaxbEvent.getDescription());
        event.setDate(CinemaDateUtils.getDateTimeByFormat(jaxbEvent.getDate(), CinemaConstants.VIEW_DATE_TIME_FORMAT));
        event.setName(jaxbEvent.getName());
        event.setRating(EventRating.getRatingBySysName(jaxbEvent.getRating()));
        event.setId(jaxbEvent.getId());

        return event;
    }
}
