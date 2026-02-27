/*
 * SET B - Question 3:
 * Write a servlet to forward a request to another servlet and include content
 * using RequestDispatcher.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>RequestDispatcher Example</title></head>");
        out.println("<body>");
        out.println("<h2>First Servlet - Before Forwarding</h2>");
        out.println("<p>This content will not be visible after forward.</p>");

        // Set attribute to pass data
        request.setAttribute("message", "Data from First Servlet");

        // Forward request to second servlet
        RequestDispatcher rd = request.getRequestDispatcher("/second");
        rd.forward(request, response);

        // This code won't execute after forward
        out.println("<p>This will not be displayed.</p>");
    }
}