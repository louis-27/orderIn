package repository;

import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.Connect;
import factory.ProductFactory;
import model.Beverage;
import model.Food;
import model.Product;

public class ProductRepository {

	static Connect conn = Connect.getConnection();
	
	public static ArrayList<Product> select() {
		ArrayList<Product> result = new ArrayList<>();
		
		String query = "SELECT * FROM `Product`";
		ResultSet rs = conn.executeQuery(query);
		
		try {
			while (rs.next()) {
				Integer productId = Integer.parseInt(rs.getString("id"));
				Integer productCategoryId = Integer.parseInt(rs.getString("categoryId"));
				String productName = rs.getString("name");
				Integer productPrice = Integer.parseInt(rs.getString("price"));
				
				Product product = ProductFactory.newProduct(productId, productCategoryId, productName, productPrice);
				
				result.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Product selectById(Integer id) {
		Product result = null;
		
		String query = String.format("SELECT * FROM `Product` WHERE id = '%d'", id);
		ResultSet rs = conn.executeQuery(query);
		
		try {
			if (rs.next() == true) {
				Integer productId = Integer.parseInt(rs.getString("id"));
				Integer productCategoryId = Integer.parseInt(rs.getString("categoryId"));
				String productName = rs.getString("name");
				Integer productPrice = Integer.parseInt(rs.getString("price"));
				
				result = ProductFactory.newProduct(productId, productCategoryId, productName, productPrice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void insert(Product product) {
		Integer categoryId = null;
		
		if (product instanceof Food) {
			categoryId = 1;
		} else if (product instanceof Beverage) {
			categoryId = 2;
		}
		
		String query = String.format("INSERT INTO `Product` (`categoryId`, `name`, `price`) VALUES ('%d', '%s', '%d')", categoryId, product.getName(), product.getPrice());
		
		conn.executeUpdate(query);
	}
	
	public static void delete(Product product) {
		String query = String.format("DELETE FROM `Product` WHERE `Name` = '%s'", product.getName());
		
		conn.executeUpdate(query);
	}
	
	public static boolean isEmpty() {
		String query = "SELECT id FROM `Product` ORDER BY id DESC LIMIT 1";
		ResultSet rs = conn.executeQuery(query);
		
		try {
			if (rs.next() == true) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
