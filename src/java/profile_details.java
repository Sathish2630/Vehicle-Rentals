import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class profile_details extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 

            String username = (String) session.getAttribute("username");
            String email = (String) session.getAttribute("email");
            String mobile = (String) session.getAttribute("mobile");
            
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("mobile", mobile);
            request.getRequestDispatcher("profile.html").forward(request, response);
        }
    }
