package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.AspectCounterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Полина on 07.04.2017.
 */
@Component
public class AspectCounterMapManager implements AspectCounterManager {

    @Autowired
    private MapDB mapDB;

    @Override
    public void incItem(String itemName) {
        Long aspectCount = mapDB.getAspectCountMap().get(itemName);
        if(aspectCount != null) {
            aspectCount = aspectCount + 1;
        } else {
            aspectCount = new Long(1);
        }

        mapDB.getAspectCountMap().put(itemName, aspectCount);
    }
}
