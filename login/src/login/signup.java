package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/signup")
public class signup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");  
		public static String TABLE_NAME = "user";
		public static String TABLE_COLUMN_NAME = "name";
		public static String TABLE_COLUMN_EMAIL = "email";
		public static String TABLE_COLUMN_PASSWORD = "password";
           PrintWriter pw = response.getWriter();  
           String connectionURL = "jdbc:sqlite:C:\\databases\\chatbook.db";
           Connection connection;  
           try{  
             String name = request.getParameter("username");  
             String Password= request.getParameter("password");  
             String email = request.getParameter("email");
             
             
            Class.forName("org.sqlite.JDBC");  
             connection = DriverManager.getConnection(connectionURL);  
             PreparedStatement pst = connection.prepareStatement("insert into "+ TABLE_NAME+"("+TABLE_COLUMN_NAME+", "+TABLE_COLUMN_EMAIL+", "+TABLE_COLUMN_PASSWORD+")"+"values(?, ?, ?)");
             pst.setString(1,name);
             pst.setString(2, email);
             pst.setString(3,Password);
            
             
             int i = pst.executeUpdate();  
             if(i!=0){  
            	 HttpSession session = request.getSession();
                 session.setAttribute("username", name);
                 session.setAttribute("email", email);
                
               response.sendRedirect("home");
                  
          
             }  
             else{  
               pw.println("failed to insert the data");  
              }  
           }  
           catch (Exception e){
             pw.println(e);  
           }  
         }  
        
   }
