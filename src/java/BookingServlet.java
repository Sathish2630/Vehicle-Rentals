import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class BookingServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gowheels";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);


        int user = (int) session.getAttribute("user_id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT booking_id,date,location FROM bookings WHERE user_id = ?")) {

            stmt.setInt(1, user);
            ResultSet rs = stmt.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Your Bookings</title><link rel='stylesheet' href='css/booking.css'></head> <header>\n" +
"        <div class=\"logo\"><a href='home.html'>GoWheels</a></div>\n" +
"        <nav>\n" +
"            <a href=\"#\">Offers</a>\n" +
"            <a href=\"#locations-section\">Locations</a>\n" +
"            <a href=\"bikes.html\">Bikes & Services</a>\n" +
"            <a href=\"contact.html\">Contact</a>\n" +
"            <!-- User Profile Dropdown -->\n" +
"            <div class=\"profile-container\">\n" +
"                <img src=\"path/to/profile-pic.png\" alt=\"Profile Picture\" class=\"profile-pic\" onclick=\"toggleDropdown()\">\n" +
"                <div class=\"dropdown-menu\" id=\"dropdownMenu\">\n" +
"                    <div class=\"profile-info\">\n" +
"                        <div class=\"profile-icon\"></div>\n" +
"                        <p>0+ Rides Taken</p>\n" +
"                </div>\n" +
"            </div>\n" +
"        </nav>\n" +
"    </header><body><div class='big'><center>");
            out.println("<h1>Your Bookings</h1>");
            out.println("<table border='1'><tr><th>Booking ID</th><th>Date</th><th>Location</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("booking_id") + "</td>");
                out.println("<td>" + rs.getString("date") + "</td>");
                out.println("<td>" + rs.getString("location") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<a href='bikes.html'>Make a New Booking</a></center> </div>");
            out.println("    <!-- Footer -->\n" +
"    <footer class=\"footer\">\n" +
"        <div class=\"footer-section\">\n" +
"            <!-- Company Info -->\n" +
"            <div class=\"footer-column\">\n" +
"                <h3>GoWheels</h3>\n" +
"                <hr>\n" +
"                <p><a href=\"mailto:support@gowheels.com\">support@gowheels.com</a></p>\n" +
"                <p><a href=\"tel:+1234567890\">+919874563219</a></p>\n" +
"            </div>\n" +
"    \n" +
"            <!-- Company Links -->\n" +
"            <div class=\"footer-column\">\n" +
"                <h3>Company</h3>\n" +
"                <hr>\n" +
"                <a href=\"about.html\">About Us</a>\n" +
"                <a href=\"blog.html\">Blog</a>\n" +
"                <a href=\"contact.html\">Contact Us</a>\n" +
"            </div>\n" +
"    \n" +
"            <!-- Policies -->\n" +
"            <div class=\"footer-column\">\n" +
"                <h3>Policies</h3>\n" +
"                <hr>\n" +
"                <a href=\"privacy.html\">Privacy Policy</a>\n" +
"                <a href=\"terms.html\">Terms & Conditions</a>\n" +
"            </div>\n" +
"    \n" +
"        </div>\n" +
"    \n" +
"    </footer>\n" +
"\n" +
"    <script src='js/script.js'></script>\n" +
"    <script src='js/selectbike.js'></script></body></html>");

        } catch (SQLException e) {
            out.println("<p>Error loading bookings. Please try again later.</p>");
        }
    }
}
