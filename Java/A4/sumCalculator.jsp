<%@ page language="java" contentType="text/html"%>
<!--
    SET B - Question 2:
    Create a JSP page that accepts two numbers and displays their sum
    using scripting elements.
-->
<html>
<head>
    <title>Sum Calculator</title>
</head>
<body>
    <h2>Number Sum Calculator</h2>

    <form method="post" action="sumCalculator.jsp">
        <label>Enter First Number: </label>
        <input type="number" name="num1" step="any" required><br><br>

        <label>Enter Second Number: </label>
        <input type="number" name="num2" step="any" required><br><br>

        <input type="submit" value="Calculate Sum">
    </form>

    <%
        // Get parameters from request
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        if (num1Str != null && num2Str != null && !num1Str.isEmpty() && !num2Str.isEmpty()) {
            try {
                // Parse numbers
                double num1 = Double.parseDouble(num1Str);
                double num2 = Double.parseDouble(num2Str);

                // Calculate sum
                double sum = num1 + num2;

                // Display result
                out.println("<hr>");
                out.println("<h3>Result:</h3>");
                out.println("<p>" + num1 + " + " + num2 + " = <strong>" + sum + "</strong></p>");
            } catch (NumberFormatException e) {
                out.println("<p style='color: red;'>Please enter valid numbers.</p>");
            }
        }
    %>
</body>
</html>