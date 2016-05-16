package com.epam.spring.cinema.console.impl;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.console.ConsoleMenu;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.service.AuditoriumService;
import com.epam.spring.cinema.service.BookingService;
import com.epam.spring.cinema.service.EventService;
import com.epam.spring.cinema.util.CinemaDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/10/2016.
 */
@Component
public class AdminConsoleMenu implements ConsoleMenu {
    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private BookingService bookingService;

    @Override
    public void start(Scanner scanner) {
        Integer command = -1;
        while (command != 0) {
            System.out.println("1-Добавить событие");
            System.out.println("2-Вывести список всех событий");
            System.out.println("3-Вывести список всех доступных мест проведения");
            System.out.println("4-Отобразить список проданных билетов");
            System.out.println("0-Выход");
            System.out.print("Выберите действие: ");
            command = scanner.nextInt();
            System.out.println();
            switch (command) {
                case 1: {
                    try {
                        String name;
                        double basePrice;
                        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<LocalDateTime, Auditorium>();
                        EventRating rating = EventRating.LOW;

                        System.out.print("Введите наименование мероприятия: ");
                        name = scanner.next();

                        System.out.print("Введите базовую стоимость билета: ");
                        basePrice = scanner.nextDouble();

                        Integer subCommand = -1;
                        while (subCommand != 0) {
                            try {
                                System.out.print("Введите дату и время проведения в формате (дд.мм.гггг-чч:мм): ");
                                LocalDateTime dateTime = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_TIME_FORMAT);

                                System.out.print("Введите наименование маста проведения: ");
                                String auditoriumName = scanner.next();

                                Auditorium auditorium = auditoriumService.getByName(auditoriumName);
                                auditoriums.put(dateTime, auditorium);

                                System.out.print("Для продололжения добавления введите любое число, для завершения введите 0: ");
                                subCommand = scanner.nextInt();
                            } catch (Exception e) {
                                System.out.println("Произошла ошибка при заполении полей места проведения " + e.getMessage());
                                scanner.next();
                            }
                        }

                        System.out.print("Введите райтинг мероприятия (LOW, MID, HIGH): ");
                        rating = EventRating.getRatingBySysName(scanner.next());

                        eventService.add(name, basePrice, auditoriums, rating);
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при добавлении события " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 2: {
                    try {
                        List<Event> eventList = eventService.getAll();
                        System.out.printf("%20s%20s%20s\n", "Название", "Базовая цена", "Райтинг");
                        for (Event event : eventList) {
                            System.out.printf("%20s%20.2f%20s\n", event.getName(), event.getBasePrice(), event.getRating().getSysName());
                            System.out.printf("               %20s%20s\n", "Название аудитории", "Время");
                            for (LocalDateTime dateTime : event.getAuditoriums().keySet()) {
                                Auditorium auditorium = event.getAuditoriums().get(dateTime);
                                System.out.printf("               %20s%20s\n", auditorium.getName(), dateTime.toString());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при выводе списка всех событий " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 3: {
                    try {
                        List<Auditorium> auditoriumList = auditoriumService.getAll();
                        System.out.printf("%20s%20s\n", "Название аудитории", "Число мест");
                        for (Auditorium auditorium : auditoriumList) {
                            System.out.printf("%20s%20d\n", auditorium.getName(), auditorium.getNumberOfSeats());
                        }
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при выводе всех доступных мест проведения " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 4: {
                    try {
                        String name;
                        System.out.print("Введите наименование мероприятия: ");
                        name = scanner.next();
                        Event event = eventService.getByName(name);

                        System.out.print("Введите дату и время проведения в формате (дд.мм.гггг-чч:мм): ");
                        LocalDateTime dateTime = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_TIME_FORMAT);

                        List<Ticket> ticketList = bookingService.getPurchasedTicketsForEvent(event, dateTime);
                        System.out.printf("%20s%20s\n", "Имя", "Номер места");
                        for (Ticket ticket : ticketList) {
                            String userName = ticket.getUser().getLastName() + " " + ticket.getUser().getFirstName();
                            System.out.printf("%20s%20d\n", userName, ticket.getSeat());
                        }
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при получении всех проданных билетов на мероприятие " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
            }
        }
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
