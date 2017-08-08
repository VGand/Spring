package com.epam.boot.report;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Полина on 08.08.2017.
 */

@Component
public class ReportGenerator implements Report {

    @Value("${report.name}")
    private String reportName;

    @Override
    public String getReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("report with name ")
                .append(reportName)
                .append(":\n").append("bla bla bla");
        return sb.toString();
    }
}
