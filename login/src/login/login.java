package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
		response.setContentType("text/html");  
        PrintWriter pw = response.getWriter();  
        String connectionURL = "jdbc:sqlite:C:\\databases\\chatbook.db";//  
        Connection connection;  
        try{  
          String email = request.getParameter("email");  
          String Password= request.getParameter("password");  
          //String email = request.getParameter("email");
          HttpSession session = request.getSession();
          //session.setAttribute("username", email);
          //session.setAttribute("email", email);
          //session.setAttribute("password", Password);
          
          
         Class.forName("org.sqlite.JDBC");  
          connection = DriverManager.getConnection(connectionURL);  
          Statement statement = connection.createStatement();
          ResultSet result = statement.executeQuery("SELECT * FROM user WHERE email = '"+email+"' AND password = '"+Password+"'");
          if (result.next()) {
        	  //while(result.next()) {
        		  String name = result.getString("name");
        		  //String emailid = result.getString("email");
        		  session.setAttribute("name", name);
        		  //session.setAttribute("email", emailid);        		 
        	 // }
        	  session.setAttribute("email", email);
              session.setAttribute("name", name);
        	  response.sendRedirect("home");
        	  //pw.print("you have logged in successfully");
        	  
          }
          else {
        	  pw.print("wrong password");
          }
         
          
       
        }  
        catch (Exception e){  
          pw.println(e);  
        }  
 
		
	}

}
