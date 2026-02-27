<%@ page language="java" contentType="text/html"%>
<!--
    SET A - Question 2:
    Write a Program to Create a JSP page that displays "Hello User!"
    using scriptlet and expression tag.
-->
<html>
<head>
    <title>Hello User JSP</title>
</head>
<body>
    <h1>JSP Example: Hello User</h1>

    <%-- Using Scriptlet --%>
    <%
        String message = "Hello User!";
    %>

    <%-- Using Expression --%>
    <h2>Message using expression: <%= message %></h2>

    <%-- Alternative using scriptlet out --%>
    <%
        out.println("<h2>Message using scriptlet: " + message + "</h2>");
    %>
</body>
</html>