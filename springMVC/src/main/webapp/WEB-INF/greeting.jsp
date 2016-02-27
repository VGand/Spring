<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Getting Started: Serving Web Content</title>
</head>
<body>
    <h1> Hello <c:out value="${name}" /> </h1>
    <form action="greeting" method="get">
        <input type="text" name="cn" value="${name}">
        <button type="submit">Search</button>
    </form>
</body>
</html>