import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;


public class Login extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gowheels";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            
            rs = stmt.executeQuery();

            
            if (rs.next()) {
                // Create a session and store the user information
                HttpSession session = request.getSession();
                session.setAttribute("username", username);  
                session.setAttribute("user_id", rs.getInt("user_id"));  
                session.setAttribute("mobile", rs.getString("mobilenumber"));  
                session.setAttribute("email", rs.getString("email")); 

                out.println("<script type='text/javascript'>");
                out.println("alert('Login Successful!');");
                out.println("window.location = 'home.html';"); 
                out.println("</script>");
            } else {
     
                out.println("<script type='text/javascript'>");
                out.println("alert('Invalid username or password!');");
                out.println("window.location = 'login.html';"); 
                out.println("</script>");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h3>Something went wrong. Please try again later.</h3>");
        }
    }
}
