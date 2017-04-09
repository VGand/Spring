package com.epam.spring.cinema.aspects;

import com.epam.spring.cinema.dao.AspectCounterManager;
import com.epam.spring.cinema.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey_Vaganov on 5/17/2016.
 */
@Aspect
@Component
public class DiscountAspect {

    private final String GET_DISCOUNT_BY_TYPE_PREFIX = "GET_DISCOUNT_BY_TYPE_";
    private final String GET_DISCOUNT_BY_TYPE_FOR_USER_PREFIX = "GET_DISCOUNT_BY_TYPE_FOR_USER_";

    @Autowired
    AspectCounterManager aspectCounterManager;

    @Pointcut("execution(* com.epam.spring.cinema.service.impl.discount.*.getDiscount(..))")
    private void getDiscount() {}

    @AfterReturning(pointcut = "getDiscount() && args(user,..)", returning = "returnObject")
    public void getDiscountAfter(JoinPoint joinPoint, User user, Object returnObject) {
        if ((Double)returnObject > 0) {
            //Скидка была сделана
            String discountName = joinPoint.getTarget().getClass().getSimpleName();
            if (user != null) {
                StringBuilder stringBuilder = new StringBuilder(GET_DISCOUNT_BY_TYPE_FOR_USER_PREFIX);
                stringBuilder.append(discountName).append("_").append(user.getLogin());
                aspectCounterManager.incItem(stringBuilder.toString());
            }
            String key = GET_DISCOUNT_BY_TYPE_PREFIX + discountName;
            aspectCounterManager.incItem(key);
        }
    }
}
