package factory;

import model.Product;
import model.Food;
import model.Beverage;

public class ProductFactory {

	public static Product newProduct(Integer id, Integer categoryId, String name, Integer price) {
		Product product = null;
		
		switch (categoryId) {
			case 1:
				product = new Food(id, name, price);
				break;
			case 2:
				product = new Beverage(id, name, price);
		}
		
		return product;
	}

}
