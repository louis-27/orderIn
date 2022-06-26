package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Connect;

public class UserRepository {

	static Connect conn = Connect.getConnection();
	
	public static boolean login(String email, String password) {
		String query = String.format("SELECT s.email, s.password FROM `Staff` s WHERE s.email = '%s' AND s.password = '%s'", email, password);
		
		ResultSet rs = conn.executeQuery(query);
		
		try {
			// Checks if ResultSet is not empty
			if (rs.next() == true) {
				String staffEmail = rs.getString("email");
				String staffPassword = rs.getString("password");
				
				if (staffEmail.equals(email) && staffPassword.equals(password)) return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
