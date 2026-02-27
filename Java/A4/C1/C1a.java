/*
 * SET C - Question 1:
 * Demonstrate session tracking using HTTP Session by creating login
 * and welcome pages using Servlet and JSP.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication (in real app, check against database)
        if (username != null && password != null &&
            username.equals("admin") && password.equals("admin123")) {

            // Create or get session
            HttpSession session = request.getSession();

            // Store user info in session
            session.setAttribute("username", username);
            session.setAttribute("loginTime", new java.util.Date().toString());

            // Set session timeout (in seconds)
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            // Redirect to welcome page
            response.sendRedirect("welcome");

        } else {
            // Login failed
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head><title>Login Failed</title></head>");
            out.println("<body>");
            out.println("<h3 style='color: red;'>Invalid username or password!</h3>");
            out.println("<a href='login.html'>Try Again</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}