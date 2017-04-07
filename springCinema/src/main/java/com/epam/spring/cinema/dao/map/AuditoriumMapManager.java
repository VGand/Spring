package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.domain.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class AuditoriumMapManager implements AuditoriumManager {

    private MapDB mapDB;

    @Autowired
    public AuditoriumMapManager(List<Auditorium> auditoriums, MapDB mapDB) {
        this.mapDB = mapDB;

        if (auditoriums != null) {
            Iterator<Auditorium> iterator = auditoriums.iterator();
            while(iterator.hasNext()) {
                Auditorium auditorium = iterator.next();
                Long id = this.mapDB.getNextAuditoriumId();
                auditorium.setId(id);
                this.mapDB.getAuditoriumMap().put(id, auditorium);
            }
        }
    }

    public void add(Auditorium auditorium) {
        Long id = mapDB.getNextAuditoriumId();
        auditorium.setId(id);
        mapDB.getAuditoriumMap().put(id, auditorium);
    }

    public Auditorium getByName(String name) {
        if (name != null) {
            for(Map.Entry<Long, Auditorium> entry : mapDB.getAuditoriumMap().entrySet()) {
                if (entry.getValue() != null && name.equals(entry.getValue().getName())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public List<Auditorium> getAll() {
        return new ArrayList<Auditorium>(mapDB.getAuditoriumMap().values());
    }

    @Override
    public NavigableSet<LocalDateTime> getAirDatesByEventId(Long eventId) {
        return null;
    }

    @Override
    public NavigableMap<LocalDateTime, Auditorium> getAuditoriumMapByEventId(Long eventId) {
        return null;
    }
}
