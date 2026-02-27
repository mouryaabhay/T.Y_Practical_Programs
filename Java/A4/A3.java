/*
 * SET A - Question 3:
 * Write a servlet to accept user name as input via URL parameters and display a greeting.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/greet")
public class GreetingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get username from URL parameter
        String userName = request.getParameter("name");

        out.println("<html>");
        out.println("<head><title>Greeting Servlet</title></head>");
        out.println("<body>");

        if (userName != null && !userName.isEmpty()) {
            out.println("<h2>Hello, " + userName + "! Welcome to Servlets.</h2>");
        } else {
            out.println("<h2>Please provide your name in the URL.</h2>");
            out.println("<p>Usage: http://localhost:8080/YourProject/greet?name=YourName</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}