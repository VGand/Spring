<html>
    <head>
        <title>Create Event</title>
    </head>
    <body>
        <div id="content">
            <fieldset style="width: 400px">
                <legend>Create Event</legend>
                <form style="width: 400px" name="EventView" action="add" method="post">
                    <table width="400px">
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="name" /></td>
                        </tr>
                        <tr>
                            <td>Base price:</td>
                            <td><input type="number" name="basePrice" step=0.01 /></td>
                        </tr>
                        <tr>
                            <td>Location name:</td>
                            <td>
                                <select name="auditoriumLocation">
                                    <#list model["auditoriums"] as auditorium>
                                        <option value="${auditorium.name}">${auditorium.name}</option>
                                    </#list>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Rating:</td>
                            <td>
                                <select name="ratingName">
                                    <#list model["eventRating"] as rating>
                                        <option value="${rating}">${rating}</option>
                                    </#list>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Date:</td>
                            <td><input type="datetime-local" name="date"/></td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td><textarea name="description"></textarea></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="   Create   " />
                            </td>
                        </tr>
                    </table>
                </form>
            </fieldset>
        </div>
    </body>
</html>