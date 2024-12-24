import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.SQLException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Signup extends HttpServlet {

   
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/gowheels";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
  
    private static final String INSERT_USER_SQL = 
        "INSERT INTO users (username, password, mobilenumber, email) VALUES (?, ?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        response.setContentType("text/html");
          PrintWriter out = response.getWriter();

       
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mobilenumber = request.getParameter("mobilenumber");
        String email = request.getParameter("email");

      
        if (username == null || password == null || mobilenumber == null || email == null) {
            out.println("<h3>All fields are required!</h3>");
            return;
        }


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
     
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
          
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password); 
            preparedStatement.setString(3, mobilenumber);
            preparedStatement.setString(4, email);

            
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                response.sendRedirect("login.html");
                 out.println("<h3>Sign-up successful!</h3>");
            } else {
                out.println("<h3>Failed to sign up. Please try again.</h3>");
            }

        } catch (SQLException e) {
            out.println("<h3>Error connecting to the database: " + e.getMessage() + "</h3>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
