import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet { 
	
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
		
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = statement.executeQuery("select * from user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
			while(rs.next()) {
				if(rs.getString(1).equalsIgnoreCase(request.getParameter("name")) &&
						(rs.getString(2).equalsIgnoreCase(request.getParameter("password")) && 
								(rs.getString(3).equalsIgnoreCase(request.getParameter("password3")))));
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<html><body> LOGIN WAS SUCCESSFUL</body></html>");
				} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
