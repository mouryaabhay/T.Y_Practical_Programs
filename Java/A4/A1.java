// Write a Program to create a simple servlet that prints "Welcome to Java Servlet".

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/welcome")
public class A1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Welcome Servlet</title></head><body><h2>Welcome to Java Servlet</h2></body></html>");
    }
}