package main;

import java.util.ArrayList;
import java.util.Scanner;

import db.Connect;
import factory.OrderFactory;
import factory.ProductFactory;
import model.Order;
import model.Product;
import repository.OrderRepository;
import repository.ProductRepository;
import repository.UserRepository;

public class Main {
	
	static Scanner scan = new Scanner(System.in);
	static Utils utils = new Utils();
	
	static Connect conn = Connect.getConnection();

	public Main() {
		boolean isRunning = true;
		while (isRunning) {
			utils.clearScreen();
			
			utils.orderInScreen();
			
			System.out.println("1. Customer");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			
			int inp;
			do {				
				System.out.print("Continue as: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 3));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					customerMenu();
					break;
				case 2:
					adminMenu();
					break;
				case 3:
					utils.orderInScreen();
					isRunning = false;
					break;
			}

		}
	}
	
	// Admin
	public void adminMenu() {
		Integer userLogin = login("Staff");
		if (userLogin == -1) return;
		
		boolean isRunning = true;
		while (isRunning) {
			utils.clearScreen();
			
			printMenu();
			
			System.out.println("1. Add to Menu");
			System.out.println("2. Remove from Menu");
			System.out.println("3. Exit");
			
			int inp;
			do {
				System.out.print("Choose: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 3));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					addToMenu();
					break;
				case 2:
					removeFromMenu();
					break;
				case 3:
					isRunning = false;
					break;
			}
		}
	}
	
	public Integer login(String userType) {
		String email, password, cancel;
		Integer userLogin = -1;
		do {
			System.out.print("\nEmail: ");
			email = scan.nextLine();
			
			System.out.print("\nPassword: ");
			password = scan.nextLine();
			
			userLogin = UserRepository.login(userType, email, password);
			
			if (userLogin == -1) {
				do {
					System.out.print("Incorrect user credentials. Do you want to go back? [Yes | No] ");
					cancel = scan.nextLine();
					
					if (cancel.equalsIgnoreCase("Yes")) {
						return -1;
					} else if (cancel.equalsIgnoreCase("No")) {
						break;
					}
				} while (!(cancel.equalsIgnoreCase("Yes") || cancel.equalsIgnoreCase("No")));
			}
		} while (userLogin == -1);
		
		return userLogin;
	}
	
	public void printMenu() {
		ArrayList<Product> menu = ProductRepository.select();
		
		System.out.println("Menu");
		System.out.println("=====================================================");
		System.out.printf("| %-3s | %-25s | %-15s |\n", "No.", "Food Name", "Food Price");
		System.out.println("=====================================================");

		for (int i = 0; i < menu.size(); i++) {
			String name = menu.get(i).getName();
			Integer price = menu.get(i).getPrice();
			
			System.out.printf("| %-3s | %-25s | Rp.%-12s |\n", i + 1, name, price);
		}
		
		System.out.println("=====================================================");
		
		System.out.println("\n");
	}
	
	public void addToMenu() {
		String name, type;
		Integer price;
		
		do {
			System.out.print("Input new menu's name: ");
			name = scan.nextLine();
		} while (!(name.length() > 0));
		
		do {
			System.out.print("Input new menu's type [Food | Beverage]: ");
			type = scan.nextLine();
		} while (!(type.equalsIgnoreCase("Food") || type.equalsIgnoreCase("Beverage")));
		
		do {
			System.out.print("Input new menu's price: ");
			price = scan.nextInt();
		} while (!(price > 0));
		
		Integer categoryId = null;
		if (type.equalsIgnoreCase("Food")) {
			categoryId = 1;
		} else if (type.equalsIgnoreCase("Beverage")) {
			categoryId = 2;
		}
		
		Product product = ProductFactory.newProduct(null, categoryId, name, price);
		ProductRepository.insert(product);
		
		System.out.println("Successfully added new menu");
		utils.waitUser();
	}
	
	public void removeFromMenu() {
		ArrayList<Product> menu = ProductRepository.select();

		int menuNumber;
		
		printMenu();
		
		do {
			System.out.printf("Input menu to be removed [1 - %d]: ", menu.size());
			menuNumber = scan.nextInt();
			scan.nextLine();
		} while (!(menuNumber >= 1 && menuNumber <= menu.size()));
		
		Product product = menu.get(menuNumber - 1);
		ProductRepository.delete(product);
		
		System.out.println("Successfully removed from menu");
		utils.waitUser();
	}
	
	// Customer
	public void customerMenu() {
		Integer userLogin = login("Customer");
		if (userLogin == -1) return;

		boolean isRunning = true;
		
		while (isRunning) {
			utils.clearScreen();
			
			System.out.println("Welcome! What do you want to do?");
			System.out.println("1. Add Order");
			System.out.println("2. Go Back");
			
			int inp;
			do {
				System.out.print("Choose: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 2));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					addOrder(userLogin);
					break;
				case 2:
					isRunning = false;
					break;
			}
		}
	}
	
	public void addOrder(Integer userLogin) {
		if (ProductRepository.isEmpty()) {
			System.out.println("There are no food. Try again later maybe ;)");
			utils.waitUser();
			return;
		}
		
		boolean isRunning = true;
		while (isRunning) {
			utils.clearScreen();
			
			printMenu();
			
			System.out.println("1. Place Order");
			System.out.println("2. Go Back");
			
			int inp;
			do {
				System.out.print("Choose: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 2));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					placeOrder(userLogin);
					break;
				case 2:
					isRunning = false;
					break;
			}
		}

	}
	
	public void placeOrder(Integer userLogin) {
		ArrayList<Product> menu = ProductRepository.select();

		printMenu();
		
		int foodNumber, qty;
		
		do {
			System.out.printf("Add Food Number [1 - %d]: ", menu.size());
			foodNumber = scan.nextInt();
			scan.nextLine();
		} while (!(foodNumber >= 1 && foodNumber <= menu.size()));
		
		do {
			System.out.print("Quantity: x ");
			qty = scan.nextInt();
			scan.nextLine();
		} while (!(qty > 0));
		
		Product chosen = menu.get(foodNumber - 1);
		Order order = OrderFactory.newOrder(null, userLogin, chosen.getId(), qty);
		OrderRepository.insert(order);
		
		System.out.println("Order placed!");
		utils.waitUser();
	}

	public static void main(String[] args) {
		new Main();
	}

}
