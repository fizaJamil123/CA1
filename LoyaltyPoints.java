import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoyaltyPoints extends HttpServlet{
		
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/loyalty?serverTimezone=UTC", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
	     String answer = request.getParameter("answer");
	     String answer1 = request.getParameter("answer1");

	     //if(answer ==  "lodge") 
	     if(answer=="100")
	     {
	    	 response.sendRedirect("lodge.html");
	    	 String username = request.getParameter("username");
				String balance = request.getParameter("points");
				response.setContentType("text/html");
				PrintWriter out=null;
				out=response.getWriter();
				out.println(request.getParameter("username") + ":" + "You have lodged points into your account");
				out.println(request.getParameter("points"));
				PreparedStatement createUser;
				createUser = connection.prepareStatement(
						"INSERT into user "
				+ "(balance)" +" VALUES (?)");
				 createUser.setString(4,  request.getParameter("points"));
				 int rowsUpdated = createUser.executeUpdate();
				  createUser.close();
			
	     } 
	     //if(answer ==  "withdraw") 
	     else if(answer1 == "20")
	     {
	    	 String username = request.getParameter("username");
				response.setContentType("text/html");
				PrintWriter out=null;
				out=response.getWriter();
				out.println(request.getParameter("username") + ":" + "You have withdrawed points out of your account");
				out.println(request.getParameter("points"));
				int balance = 0;
				if(balance < 0) {
					out.println(request.getParameter("username") + ":" + "You cannot take out anymore points!! Balance is low");
				}
				PreparedStatement createUser;
				createUser = connection.prepareStatement(
						"INSERT into user "
				+ "(balance)" +" VALUES (?)");
				 createUser.setString(4,  request.getParameter("points"));
				 int rowsUpdated = createUser.executeUpdate();
				  createUser.close();
		}
	     
		} catch  (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		response.sendRedirect("lodge.html");
	     
	   response.sendRedirect("withdraw.html");
	   
}
}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		