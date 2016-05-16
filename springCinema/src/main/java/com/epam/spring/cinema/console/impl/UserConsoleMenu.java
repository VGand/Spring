package com.epam.spring.cinema.console.impl;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.console.ConsoleMenu;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.BookingService;
import com.epam.spring.cinema.service.EventService;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.session.Session;
import com.epam.spring.cinema.util.CinemaDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Andrey_Vaganov on 5/10/2016.
 */
@Component
public class UserConsoleMenu implements ConsoleMenu {
    @Autowired
    EventService eventService;

    @Autowired
    BookingService bookingService;

    @Autowired
    UserService userService;

    @Override
    public void start(Scanner scanner) {
        Integer command = -1;
        while (command != 0) {
            System.out.println("1-Отобразить события с определенной даты");
            System.out.println("2-Отобразить события за период");
            System.out.println("3-Получить цену билетов");
            System.out.println("4-Купить билеты");
            System.out.println("0-Выход");
            System.out.print("Выберите действие: ");
            command = scanner.nextInt();
            System.out.println();
            switch (command) {
                case 1: {
                    try {
                        System.out.print("Введите начальную дату и время в формате (дд.мм.гггг-чч:мм): ");
                        LocalDateTime inputDateTime = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_TIME_FORMAT);

                        System.out.printf("%20s%20s%20s\n", "Название", "Базовая цена", "Райтинг");
                        List<Event> eventList = eventService.getNextEvent(inputDateTime);
                        for (Event event : eventList) {
                            System.out.printf("%20s%20.2f%20s\n", event.getName(), event.getBasePrice(), event.getRating().getSysName());
                            System.out.printf("               %20s%20s\n", "Название аудитории", "Время");
                            for (LocalDateTime dateTime : event.getAuditoriums().keySet()) {
                                if (inputDateTime.isBefore(dateTime)) {
                                    Auditorium auditorium = event.getAuditoriums().get(dateTime);
                                    System.out.printf("               %20s%20s\n", auditorium.getName(), dateTime.toString());
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка получении событий с определенной даты " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.print("Введите начальную дату и время в формате (дд.мм.гггг-чч:мм): ");
                        LocalDateTime fromDateTime = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_TIME_FORMAT);

                        System.out.print("Введите конечную дату и время в формате (дд.мм.гггг-чч:мм): ");
                        LocalDateTime toDateTime = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_TIME_FORMAT);

                        System.out.printf("%20s%20s%20s\n", "Название", "Базовая цена", "Райтинг");
                        List<Event> eventList = eventService.getForDateRange(fromDateTime, toDateTime);
                        for (Event event : eventList) {
                            System.out.printf("%20s%20.2f%20s\n", event.getName(), event.getBasePrice(), event.getRating().getSysName());
                            System.out.printf("               %20s%20s\n", "Название аудитории", "Время");
                            for (LocalDateTime dateTime : event.getAuditoriums().keySet()) {
                                if (fromDateTime.isBefore(dateTime) && toDateTime.isAfter(dateTime)) {
                                    Auditorium auditorium = event.getAuditoriums().get(dateTime);
                                    System.out.printf("               %20s%20s\n", auditorium.getName(), dateTime.toString());
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка получении событий за период " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.print("Введите название мероприятия: ");
                        String eventName = scanner.next();
                        Event event = eventService.getByName(eventName);

                        System.out.print("Введите дату и время проведения мероприятия (дд.мм.гггг-чч:мм): ");
                        LocalDateTime inputDateTime = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_TIME_FORMAT);

                        Set<Long> seats = new HashSet<Long>();
                        Integer subCommand = -1;
                        while (subCommand != 0) {
                            try {
                                System.out.print("Введите номер места: ");
                                Long seatNumber = scanner.nextLong();
                                seats.add(seatNumber);

                                System.out.print("Для продололжения добавления введите любое число, для завершения введите 0: ");
                                subCommand = scanner.nextInt();
                            } catch (Exception e) {
                                System.out.println("Произошла ошибка при вводе места " + e.getMessage());
                                scanner.next();
                            }
                        }

                        String login = Session.currentSession().getUserLogin();
                        User user = userService.getBuLogin(login);

                        Double cost = bookingService.getTicketsPrice(event, inputDateTime, user, seats);
                        System.out.printf("Итоговая цена: %10.2f", cost);
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при подсчете цены билета " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 4: {
                    try {
                        List<Ticket> ticketList = new ArrayList<Ticket>();
                        Integer subCommand = -1;
                        while (subCommand != 0) {
                            try {
                                System.out.print("Введите название мероприятия: ");
                                String eventName = scanner.next();
                                Event event = eventService.getByName(eventName);

                                System.out.print("Введите дату и время проведения мероприятия (дд.мм.гггг-чч:мм): ");
                                LocalDateTime inputDateTime = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_TIME_FORMAT);

                                System.out.print("Введите номер желаемого места: ");
                                Long seatNumber = scanner.nextLong();

                                String login = Session.currentSession().getUserLogin();
                                User user = userService.getBuLogin(login);

                                Ticket ticket = bookingService.createTicket(event, inputDateTime, user, seatNumber);
                                if (ticket == null) {
                                    System.out.println("Такой билет уже продан или такого места не существует!");
                                } else {
                                    ticketList.add(ticket);
                                }

                                System.out.print("Для продололжения добавления введите любое число, для завершения введите 0: ");
                                subCommand = scanner.nextInt();
                            } catch (Exception e) {
                                System.out.println("Произошла ошибка при заполнении данных билета " + e.getMessage());
                                scanner.next();
                            }
                        }

                        bookingService.bookTicket(ticketList);
                        System.out.println("Билеты успешно куплены!");
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при покупке билетов " + e.getMessage());
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

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
