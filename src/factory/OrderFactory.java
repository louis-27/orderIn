package factory;

import model.Order;
import model.Product;
import model.User;
import repository.ProductRepository;
import repository.UserRepository;

public class OrderFactory {

	public static Order newOrder(Integer id, Integer customerId, Integer productId, Integer quantity) {
		Order order = null;
		User customer = UserRepository.selectById(customerId);
		Product product = ProductRepository.selectById(productId);
		
		order = new Order(id, customer, product, quantity);
		
		return order;
	}

}
