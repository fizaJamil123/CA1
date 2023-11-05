import java.sql.DriverManager;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/banking?serverTimezone=UTC", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement createUser;
		try {
			createUser = connection.prepareStatement(
					"INSERT into user "
			+ "(name, password, password2)" +" VALUES (?, ?, ?)");
			
			 createUser.setString(1, request.getParameter("name"));
			 createUser.setString(2,  request.getParameter("password"));
			 createUser.setString(3,  request.getParameter("password2"));
			 int rowsUpdated = createUser.executeUpdate();
			  createUser.close();
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("login.html");
	}
	
}
