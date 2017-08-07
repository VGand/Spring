package com.epam.human;
import com.epam.human.config.HumanConfig;
import com.epam.human.service.Human;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Полина on 07.08.2017.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HumanConfig.class);//new ClassPathXmlApplicationContext("springConfig.xml");

        Human zina = (Human) context.getBean("zina");
        zina.setFirstName("Liza");

        Human nina = (Human) context.getBean("nina");

        System.out.println(zina.getFirstName() + " " + zina.getLastName() + " from " + zina.getAddress().getCity());
        System.out.println(nina.getFirstName() + " " + nina.getLastName() + " from " + nina.getAddress().getCity());
    }
}
