<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Getting Started: Serving Web Content</title>
</head>
<body>
    <form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
        <form:label path="username">Enter your user-name</form:label>
        <form:input id="username" name="username" path="username" /><br>
        <form:label path="username">Please enter your password</form:label>
        <form:password id="password" name="password" path="password" /><br>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
