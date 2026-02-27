/*
 * SET B - Question 1:
 * Create a servlet to accept user input (name, age) using HTML form
 * and display it using GET method.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/displayUser")
public class DisplayUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get parameters from request
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        out.println("<html>");
        out.println("<head><title>User Details</title></head>");
        out.println("<body>");
        out.println("<h2>User Information Received via GET</h2>");
        out.println("<table border='1' cellpadding='10'>");
        out.println("<tr><th>Field</th><th>Value</th></tr>");
        out.println("<tr><td>Name</td><td>" + name + "</td></tr>");
        out.println("<tr><td>Age</td><td>" + age + "</td></tr>");
        out.println("</table>");
        out.println("<br><a href='index.html'>Go Back</a>");
        out.println("</body>");
        out.println("</html>");
    }
}