package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pid = Integer.parseInt(request.getParameter("pid"));
        HttpSession session = request.getSession();
		//String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		String connectionURL = "jdbc:sqlite:C:\\databases\\chatbook.db";
		String COLUMN_EMAIL = "email";
		String COLUMN_PID = "pid";
		String COLUMN_TEXT = "text";
		String TABLE_NAME = "post";
        Connection connection;  
        try {
        	connection = DriverManager.getConnection(connectionURL);
        	//Statement stat = connection.createStatement();
        	//stat.executeQuery("DELETE * FROM post WHERE email = '"+email+"' AND pid='"+pid+"'");
        	PreparedStatement pst = connection.prepareStatement("DELETE "+COLUMN_EMAIL+", "+COLUMN_TEXT+" FROM "+TABLE_NAME+" WHERE "+COLUMN_EMAIL+" = ?");
        	pst.setString(1, email);
            //pst.setInt(2, pid);
        	int i = pst.executeUpdate();
        	if (i!=0) {
        		PrintWriter out = response.getWriter();
            	out.println("value  deleted ");
        	}
        	else {
        		PrintWriter out = response.getWriter();
            	out.println("value not  deleted ");
        	}
        	
        	
        }
        catch(Exception e) {
        	PrintWriter out = response.getWriter();
        	out.println("value no deleted "+e.getMessage());
        }
		
	}

}
