/*
 * SET B - Question 4:
 * Write a servlet to create a cookie and send it to the browser,
 * then read and display cookies received from the browser.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/cookieDemo")
public class CookieDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Create new cookies
        Cookie userCookie = new Cookie("username", "JohnDoe");
        Cookie visitCookie = new Cookie("visitCount", "1");
        Cookie preferenceCookie = new Cookie("theme", "dark");

        // Set cookie properties
        userCookie.setMaxAge(60 * 60 * 24); // 1 day
        visitCookie.setMaxAge(60 * 60 * 24); // 1 day
        preferenceCookie.setMaxAge(60 * 60 * 24); // 1 day

        // Add cookies to response
        response.addCookie(userCookie);
        response.addCookie(visitCookie);
        response.addCookie(preferenceCookie);

        out.println("<html>");
        out.println("<head><title>Cookie Demo</title></head>");
        out.println("<body>");
        out.println("<h2>Cookie Operations</h2>");

        out.println("<h3>Cookies Created and Sent to Browser:</h3>");
        out.println("<ul>");
        out.println("<li>username = JohnDoe</li>");
        out.println("<li>visitCount = 1</li>");
        out.println("<li>theme = dark</li>");
        out.println("</ul>");

        out.println("<hr>");
        out.println("<h3>Reading Cookies from Request:</h3>");

        // Read cookies from request
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>Cookie Name</th><th>Cookie Value</th></tr>");

            for (Cookie cookie : cookies) {
                out.println("<tr>");
                out.println("<td>" + cookie.getName() + "</td>");
                out.println("<td>" + cookie.getValue() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } else {
            out.println("<p>No cookies found in the request.</p>");
        }

        out.println("<br><a href='cookieDemo'>Refresh Page</a>");
        out.println("</body>");
        out.println("</html>");
    }
}