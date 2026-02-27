<%@ page language="java" contentType="text/html"%>
<!--
    SET C - Question 1: Welcome Page
-->
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %>!</h2>

    <h3>Session Information:</h3>
    <table border="1" cellpadding="10">
        <tr>
            <th>Session ID</th>
            <td><%= session.getId() %></td>
        </tr>
        <tr>
            <th>Login Time</th>
            <td><%= session.getAttribute("loginTime") %></td>
        </tr>
        <tr>
            <th>Session Created</th>
            <td><%= new java.util.Date(session.getCreationTime()) %></td>
        </tr>
        <tr>
            <th>Last Accessed</th>
            <td><%= new java.util.Date(session.getLastAccessedTime()) %></td>
        </tr>
        <tr>
            <th>Max Inactive Interval</th>
            <td><%= session.getMaxInactiveInterval() %> seconds</td>
        </tr>
    </table>

    <br>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>