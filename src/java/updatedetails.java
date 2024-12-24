import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.sql.SQLException;

public class updatedetails extends HttpServlet {

    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gowheels";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        int userId = (int) session.getAttribute("user_id");

        String updatedUserName = request.getParameter("UserName");
        String updatedPassword = request.getParameter("Password");
        String updatedEmail = request.getParameter("email");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "UPDATE users SET username = ?, password = ?, email = ?, address = ? WHERE user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, updatedUserName);
            stmt.setString(2, updatedPassword);
            stmt.setString(3, updatedEmail);
            stmt.setInt(5, userId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                out.println("<script>alert('Profile updated successfully!');</script>");
                response.sendRedirect("profile.html");
            } else {
                out.println("<script>alert('Failed to update profile. Try again later.');</script>");
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            out.println("<h3>Something went wrong. Please try again later.</h3>");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
            }
        }
    }
}
