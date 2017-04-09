<html>
<head>
    <title>All Events</title>
</head>
<body>
<div id="header">
    <h2 align="center">All Events</h2>
</div>
<div id="content">
    <a href="add">Create</a>
    <table  width="800px">
        <tr>
            <th width="200" align="left">Name</th>
            <th width="200" align="left">Location name</th>
            <th width="200" align="left">Date and Time</th>
            <th width="100" align="center">Price</th>
            <th width="100" align="center">Rating</th>
        </tr>
        <#list model["events"] as event>
            <tr>
                <td align="left"><a href="view/${event.id}">${event.name}</a></td>
                <td align="left">${event.auditoriumLocation}</td>
                <td align="left">${event.date}</td>
                <td align="center">${event.basePrice}</td>
                <td align="center">${event.ratingName}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>