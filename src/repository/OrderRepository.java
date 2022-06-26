package repository;

import db.Connect;
import model.Order;

public class OrderRepository {
	
	static Connect conn = Connect.getConnection();

	public static void insert(Order order) {
		Integer categoryId = null;
		
		String query = String.format("INSERT INTO `OrderHeader` (`customerId`, `productId`, `quantity`) VALUES ('%d', '%d', '%d')", order.getCustomer().getId(), order.getProduct().getId(), order.getQuantity());
		
		conn.executeUpdate(query);
	}

}
