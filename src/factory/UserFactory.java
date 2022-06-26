package factory;

import model.Customer;
import model.User;

public class UserFactory {

	public static User newCustomer(Integer id, String name, String gender, String phone, String address, String email, String password) {
		User user = null;
		
		user = new Customer(id, name, gender, phone, address, email, password);
		
		return user;
	}

}
