package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Connect;
import factory.ProductFactory;
import factory.UserFactory;
import model.Product;
import model.User;

public class UserRepository {

	static Connect conn = Connect.getConnection();
	
	public static Integer login(String userType, String email, String password) {
		String query = String.format("SELECT s.id, s.email, s.password FROM `%s` s WHERE s.email = '%s' AND s.password = '%s'", userType, email, password);
		
		ResultSet rs = conn.executeQuery(query);
		
		try {
			// Checks if ResultSet is not empty
			if (rs.next() == true) {
				Integer rsId = Integer.parseInt(rs.getString("id"));
				String rsEmail = rs.getString("email");
				String rsPassword = rs.getString("password");
				
				if (rsEmail.equals(email) && rsPassword.equals(password)) return rsId;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public static User selectById(Integer id) {
		User result = null;
		
		String query = String.format("SELECT * FROM `Customer` WHERE id = '%d'", id);
		ResultSet rs = conn.executeQuery(query);
		
		try {
			if (rs.next() == true) {
				Integer customerId = Integer.parseInt(rs.getString("id"));
				String customerName = rs.getString("name");
				String customerGender = rs.getString("gender");
				String customerPhone = rs.getString("phone");
				String customerAddress = rs.getString("address");
				String customerEmail = rs.getString("email");
				String customerPassword = rs.getString("password");
				
				result = UserFactory.newCustomer(customerId, customerName, customerGender, customerPhone, customerAddress, customerEmail, customerPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
