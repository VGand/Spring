package com.epam.spring.cinema.config.app;

import com.epam.spring.cinema.service.DiscountService;
import com.epam.spring.cinema.service.impl.discount.BirthdayStrategy;
import com.epam.spring.cinema.service.impl.DiscountServiceImpl;
import com.epam.spring.cinema.service.impl.discount.Every10thTicketStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 15.05.2016.
 */
@Configuration
@PropertySource("classpath:cinema.properties")
public class DiscountConfig {

    @Autowired
    private Environment env;

    @Bean
    public DiscountService birthdayStrategy() {
        BirthdayStrategy birthdayStrategy = new BirthdayStrategy();
        birthdayStrategy.setBaseDiscount(Double.parseDouble(env.getProperty("baseDiscount.birthdayStrategy")));
        return birthdayStrategy;
    }

    @Bean
    public DiscountService every10thTicketStrategy() {
        Every10thTicketStrategy every10thTicketStrategy = new Every10thTicketStrategy();
        every10thTicketStrategy.setBaseDiscount(Double.parseDouble(env.getProperty("baseDiscount.every10thTicketStrategy")));
        return every10thTicketStrategy;
    }

    @Bean
    public DiscountService discountService() {
        DiscountServiceImpl discountService = new DiscountServiceImpl();
        List<DiscountService> discountServiceList = new ArrayList<>();
        discountServiceList.add(birthdayStrategy());
        discountServiceList.add(every10thTicketStrategy());
        discountService.setDiscountStrategies(discountServiceList);

        return discountService;
    }
}
