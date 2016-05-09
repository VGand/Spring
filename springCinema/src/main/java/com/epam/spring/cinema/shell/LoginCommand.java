package com.epam.spring.cinema.shell;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class LoginCommand implements CommandMarker {

    @CliAvailabilityIndicator({"hw login"})
    public boolean isSimpleAvailable() {
        //always available
        return true;
    }

    @CliCommand(value = "hw login", help = "Enter -login")
    public String login(
            @CliOption(key = { "login" }, mandatory = true)
            final String login
    ) {
        StringBuilder finalMessage = new StringBuilder();
        finalMessage.append("login:").append(login);
        return finalMessage.toString();
    }
}
