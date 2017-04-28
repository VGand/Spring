package com.epam.spring.cinema.ws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Andrey_Vaganov on 4/28/2017.
 */
public class EventWSClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;
}
