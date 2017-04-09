package com.epam.spring.cinema.aspects;

import java.util.Map;

/**
 * Created by Andrey_Vaganov on 5/17/2016.
 */
public class AspectUtil {

    public static void incMapItem(String key, Map<String, Long> countMap) {
        Long count = new Long(0);
        if (countMap.containsKey(key)) {
            count = countMap.get(key);
        }
        count = count + 1;
        countMap.put(key, count);
    }
}
