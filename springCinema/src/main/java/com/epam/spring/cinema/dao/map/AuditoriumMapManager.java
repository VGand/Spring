package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.domain.Auditorium;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class AuditoriumMapManager implements AuditoriumManager {


    public AuditoriumMapManager() {
    }

    public AuditoriumMapManager(List<Auditorium> auditoriums) {
        if (auditoriums != null) {
            Iterator<Auditorium> iterator = auditoriums.iterator();
            while(iterator.hasNext()) {
                Auditorium auditorium = iterator.next();
                Long id = MapDB.getInstance().getNextAuditoriumId();
                auditorium.setId(id);
                MapDB.getInstance().getAuditoriumMap().put(id, auditorium);
            }
        }
    }

    public void add(Auditorium auditorium) {
        Long id = MapDB.getInstance().getNextAuditoriumId();
        auditorium.setId(id);
        MapDB.getInstance().getAuditoriumMap().put(id, auditorium);
    }

    public Auditorium getByName(String name) {
        if (name != null) {
            for(Map.Entry<Long, Auditorium> entry : MapDB.getInstance().getAuditoriumMap().entrySet()) {
                if (entry.getValue() != null && name.equals(entry.getValue().getName())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public List<Auditorium> getAll() {
        return new ArrayList<Auditorium>(MapDB.getInstance().getAuditoriumMap().values());
    }
}
