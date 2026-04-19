package lr6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.DecimalFormat;

@WebServlet("/volume")
public class VolumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String shape = request.getParameter("shape");
        String precisionParam = request.getParameter("precision");

        int precision = 2;

        try {
            if (precisionParam != null) {
                precision = Integer.parseInt(precisionParam);
            }
        } catch (NumberFormatException e) {
            precision = 2;
        }

        DecimalFormat df =
                new DecimalFormat("0." + "0".repeat(Math.max(0, precision)));

        try {

            if (shape == null || shape.isEmpty()) {
                throw new MissingParameterException("shape");
            }

            shape = shape.toLowerCase();

            double volume;

            switch (shape) {

                case "cuboid":
                    double length = getPositiveDouble(request, "length");
                    double width = getPositiveDouble(request, "width");
                    double height = getPositiveDouble(request, "height");
                    volume = length * width * height;
                    break;

                case "cube":
                    double side = getPositiveDouble(request, "side");
                    volume = Math.pow(side, 3);
                    break;

                case "sphere":
                    double radius = getPositiveDouble(request, "radius");
                    volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
                    break;

                case "tetrahedron":
                    double edge = getPositiveDouble(request, "edge");
                    volume = Math.pow(edge, 3) / (6 * Math.sqrt(2));
                    break;

                case "torus":
                    double R = getPositiveDouble(request, "majorRadius");
                    double r = getPositiveDouble(request, "minorRadius");
                    volume = 2 * Math.PI * Math.PI * R * Math.pow(r, 2);
                    break;

                case "ellipsoid":
                    double a = getPositiveDouble(request, "a");
                    double b = getPositiveDouble(request, "b");
                    double c = getPositiveDouble(request, "c");
                    volume = (4.0 / 3.0) * Math.PI * a * b * c;
                    break;

                default:
                    throw new InvalidShapeException(shape);
            }

            response.sendRedirect("index.html?result=" + df.format(volume));

        } catch (Exception e) {

            response.sendRedirect("index.html?error=" + e.getMessage());
        }
    }

    private double getPositiveDouble(HttpServletRequest request, String name)
            throws MissingParameterException, InvalidDimensionException {

        String value = request.getParameter(name);

        if (value == null || value.isEmpty()) {
            throw new MissingParameterException(name);
        }

        try {
            double number = Double.parseDouble(value);

            if (number <= 0) {
                throw new InvalidDimensionException(name, value);
            }

            return number;

        } catch (NumberFormatException e) {
            throw new InvalidDimensionException(name, value);
        }
    }
}