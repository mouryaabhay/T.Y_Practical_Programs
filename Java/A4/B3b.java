import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get attribute from first servlet
        String message = (String) request.getAttribute("message");

        out.println("<html>");
        out.println("<head><title>Second Servlet</title></head>");
        out.println("<body>");
        out.println("<h2>Second Servlet - After Forward</h2>");
        out.println("<p>Message received from First Servlet: <strong>" + message + "</strong></p>");

        // Include content from third servlet
        out.println("<h3>Including content from Third Servlet:</h3>");
        RequestDispatcher rd = request.getRequestDispatcher("/third");
        rd.include(request, response);

        out.println("<p><a href='first'>Back to First Servlet</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}