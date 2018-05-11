package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//import javax.servlet.RequestDispatcher;
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
		  response.setContentType("text/html"); 
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
				+ "width: 500px; height: 100px;}#status{ background-color: white; width: 500px; height: auto; margin-left: 400px; margin-top: 5px;}body{"
				+ ""
				+ "background-color: #ddd;}</style></head>");
		out.println("<body><div id='wrapper'></div><br><br>");
		out.println("<h1>Welcome "+name+"<br></h1><br><br>");
		out.println("Email  "+email+"<br><br>"+"Name "+name+"<br><br>");
		out.println("<div id='box'>"
				+ ""
				+ "<form method='post' action='profile'>"
				+ ""
				+ "<textarea name='textarea'></textarea><br>"
				+ ""
				+ "<input type='submit' name='post' value='Post'></form> </div>");
		out.println("<a href='logout'>Logout</a>");
		out.println("</body></html>");
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\databases\\chatbook.db");
			Statement statement = conn.createStatement();
	          ResultSet result = statement.executeQuery("SELECT user.name, post.* FROM user, post WHERE post.email = '"+email+"' AND user.email = '"+email+"' ORDER BY pid DESC");
	          while(result.next()) {
	        	  String name1 = result.getString("name");
	        	  String posttext = result.getString("text");
	        	  int id = result.getInt("pid");
	        	  out.println("<div id ='status'>"
	        	  		+ ""
	        	  		+ "<p1 style='text-style: bold; margin-left: 10px;'><strong>"+name1+"</strong></p1><a "
	        	  				+ ""
	        	  				+ "style='float: right; margin-right: 15px; text-decoration: none; font-size: 13px;'href='delete?pid="+id+"'>Delete</a><br><br><p2 style='margin-left: 10px !important;"
	        	  						+ ""
	        	  						+ "margin-right: 10px !important;'>"+posttext+"</p2></div>");
	        	  
	          }
	          
		}
		catch(Exception e) {
		out.println("value not fetched" +e.getMessage());
			
		}
		
		}
		
	
		
	  }

}
