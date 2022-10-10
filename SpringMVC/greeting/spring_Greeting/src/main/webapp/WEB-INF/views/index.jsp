<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="/convert" method="post">
    <fieldset>
        <legend>Convert USA APP</legend>
        <lable>USD</lable>
        <input type="number" name="usd" required> <br>
        <input type="submit" value="Convert">
    </fieldset>
</form>
</body>
</html>