import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/third")
public class ThirdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<div style='border: 2px solid blue; padding: 10px; margin: 10px;'>");
        out.println("<h4>Third Servlet - Included Content</h4>");
        out.println("<p>This content is included using RequestDispatcher.include()</p>");
        out.println("<p>Current time: " + new java.util.Date() + "</p>");
        out.println("</div>");
    }
}