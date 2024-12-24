import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Booking extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingDate = request.getParameter("bookingDate");
        String location = request.getParameter("location");
         PrintWriter out = response.getWriter();
      
        Connection conn = null;
        PreparedStatement ps = null;

        try {
           
            String dbURL = "jdbc:mysql://localhost:3306/gowheels"; 
            String dbUsername = "root";
            String dbPassword = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            HttpSession session = request.getSession(false);
            int userId = (int) session.getAttribute("user_id");
          
            String sql = "INSERT INTO bookings(user_id,location,date) VALUES (?,?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, location);
            ps.setString(3, bookingDate);
           
            int result = ps.executeUpdate();
            
            if (result > 0) {
                
                out.println("<script type='text/javascript'>");
                out.println("alert('Booking Sucess!');");
                out.println("window.location = 'booking.html';"); // Redirect to the home page
                out.println("</script>");
            } else {
               
                response.getWriter().write("Booking failed. Please try again.");
            }
        } catch (SQLException e) {
            response.getWriter().write("Database error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
