package com.epam.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.epam.boot.report.Report;

/**
 * Created by Полина on 08.08.2017.
 */
@SpringBootApplication
public class BootMain implements CommandLineRunner {

    @Autowired
    private Report report;

    public static void main(String[] args) {
        SpringApplication.run(BootMain.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(report.getReport());
    }
}
