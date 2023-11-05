import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bank extends HttpServlet{
		int balance = 0;
		
		void withdraw(String username, int withdraw) {
			if (balance >= withdraw) {
				System.out.println(username + "withdrawl" + withdraw);
				balance = balance - withdraw;
				System.out.println("Balance after withdrawl: " + balance);
			} else {
				System.out.println("Cannot withdraw as your balance is: " + balance);
			}
		}
		
		void deposit(String username, int deposit) {
			System.out.println(username + "deposited" + deposit);
			balance = balance + deposit;
			System.out.println("Balance after deposit:" + balance);
		}
		
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
			+ "(balance)" +" VALUES (?)");
			 createUser.setString(4,  request.getParameter("balance"));
			 int rowsUpdated = createUser.executeUpdate();
			  createUser.close();
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
