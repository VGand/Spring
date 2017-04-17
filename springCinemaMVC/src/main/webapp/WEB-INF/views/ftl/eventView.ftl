<html>
    <head>
        <title>View Event</title>
    </head>

    <body>
        <div>
            <h2 align="center">View Event</h2>
        </div>
        <a href="/springCinema/">List</a>
        <div>
            <fieldset style="width: 800px">
                <legend>View Event</legend>
                <table width="800px">
                    <tr>
                        <td>Name:</td>
                        <td>${model.event.name}</td>
                    </tr>
                    <tr>
                        <td>Base price:</td>
                        <td>${model.event.basePrice}</td>
                    </tr>
                    <tr>
                        <td>Location name:</td>
                        <td>${model.event.auditoriumLocation}</td>
                    </tr>
                    <tr>
                        <td>Rating:</td>
                        <td>${model.event.ratingName}</td>
                    </tr>
                    <tr>
                        <td>Date:</td>
                        <td>${model.event.date}</td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td>${model.event.description}</td>
                    </tr>
                </table>
            </fieldset>
        </div>
        <div>
            <h2 align="center">View Tickets</h2>
        </div>
        <a href="soldTicketPrint/${model.event.id}">Print sold ticket</a>
        <div>
            <table>
                <#list model["event"].ticketList as ticket>
                    <#if ticket.userLogin??>
                    <#else>
                        <tr>
                            <td>Number of seat: ${ticket.seat}</td>
                            <td>Price: ${ticket.ticketPrice}</td>
                            <td style="color: green"><a href="buy/${ticket.id}">buy</a></td>
                            <#if ticket.vip>
                                <td style="color: red">VIP</td>
                            </#if>
                        </tr>
                    </#if>
                </#list>
            </table>
        </div>
    </body>
</html>