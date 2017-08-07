package com.epam.human.config;

import com.epam.human.service.Address;
import com.epam.human.service.Human;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Полина on 07.08.2017.
 */
@Configuration
public class HumanConfig {

    @Bean
    public Address izhevsk() {
        return new Address("izhevsk");
    }

    @Bean
    public Address samara() {
        return new Address("izhevsk");
    }

    @Bean(initMethod = "humanInit")
    public Human zina() {
        Human zina = new Human();
        zina.setFirstName("Zina");
        zina.setLastName("MIchalna");
        zina.setAddress(izhevsk());

        return zina;
    }

    @Bean(initMethod = "humanInit")
    public Human nina() {
        Human nina = new Human();
        nina.setFirstName("Nina");
        nina.setLastName("MIchalna");
        nina.setAddress(samara());

        return nina;
    }
}
