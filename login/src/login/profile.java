package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

@WebServlet("/profile")
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
         
		   class A{
		   public void setPost() throws IOException {
				String text = request.getParameter("text");
                String email = session.getAttribute("email").toString();
                try {
                	Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\databases\\chatbook.db");
                	PreparedStatement pr = conn.prepareStatement("INSERT INTO post(email, text) VALUES (?, ?)");
                	pr.setString(1, email);
                	pr.setString(2, text);
                }
                catch(Exception e) {
                	PrintWriter out = response.getWriter();
                	out.println("something went wrong "+e.getMessage());
                }
			}
		  }
		
	}
	public static void main(String[] args) {
		
		
	    
		
	}

}
