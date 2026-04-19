package lr6;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class VolumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String shape = request.getParameter("shape");

        // ❗ ЕСЛИ НЕТ ПАРАМЕТРА — ПОКАЗЫВАЕМ ФОРМУ
        if (shape == null) {
            out.println("<html><body>");
            out.println("<h2>Volume Calculator</h2>");
            out.println("<form action='/volume' method='get'>");
            out.println("Shape: <input name='shape'><br>");
            out.println("Radius: <input name='radius'><br>");
            out.println("<button type='submit'>Calculate</button>");
            out.println("</form>");
            out.println("</body></html>");
            return;
        }

        try {
            double volume = 0;

            switch (shape) {

                case "sphere": {
                    double r = Double.parseDouble(request.getParameter("radius"));
                    volume = (4.0 / 3.0) * Math.PI * r * r * r;
                    break;
                }

                case "cube": {
                    double a = Double.parseDouble(request.getParameter("a"));
                    volume = a * a * a;
                    break;
                }

                case "cylinder": {
                    double r = Double.parseDouble(request.getParameter("radius"));
                    double h = Double.parseDouble(request.getParameter("height"));
                    volume = Math.PI * r * r * h;
                    break;
                }

                default:
                    out.println("Unknown shape");
                    return;
            }

            out.println("<html><body>");
            out.println("<h2>Result</h2>");
            out.println("<p>Volume = " + volume + "</p>");
            out.println("<a href='/volume'>Back</a>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("Error: wrong parameters");
        }
    }
}
