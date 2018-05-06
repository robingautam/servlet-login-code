package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/home")
public class home extends HttpServlet {

   
	private static final long serialVersionUID = 1L;

	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
		HttpSession session = request.getSession();
		String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session!= null) {
			out.println("<html><head><style>#wrapper{"
				+ ""
				+ ""
				+ "width: 100%;height: 60px; background-color: darkslategray;}"
				+ ""
				+ "*{"
				+ ""
				+ "margin: 0px; padding: 0px;}#box {"
				+ ""
				+ "margin-left: 400px;}input{"
				+ " background-color: darkslategray; color: white;border: 1px solid darkslategray; width: 70px; height: 20px;cursor: pointer}"
				+ ""
				+ "textarea{"
				+ ""
				+ "width: 500px; height: 100px;}</style></head>");
		out.println("<body><div id='wrapper'></div><br><br>");
		out.println("<h1>Welcome "+name+"<br></h1><br><br>");
		out.println("Email  "+email+"<br><br>"+"Name "+name+"<br><br>");
		out.println("<div id='box'>"
				+ ""
				+ "<form method='post' action='profile'>"
				+ ""
				+ "<textarea name='text'></textarea><br>"
				+ ""
				+ "<input type='submit' name='post' value='Post'></form> </div>");
		out.println("<a href='logout'>Logout</a>");
		out.println("</body></html>");
		
		}
		
	
		
	  }

}
