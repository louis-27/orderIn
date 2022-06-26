package main;

import java.util.ArrayList;
import java.util.Scanner;

import db.Connect;
import factory.ProductFactory;
import model.Product;
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
			System.out.println("2. Driver");
			System.out.println("3. Admin");
			System.out.println("4. Exit");
			
			int inp;
			do {				
				System.out.print("Continue as: ");
				inp = scan.nextInt();
				scan.nextLine();
			} while (!(inp >= 1 && inp <= 4));
			
			utils.clearScreen();
			switch (inp) {
				case 1:
					customerMenu();
					break;
				case 2:
					driverMenu();
					break;
				case 3:
					adminMenu();
					break;
				case 4:
					utils.orderInScreen();
					isRunning = false;
					break;
			}

		}
	}

	
	
	// Admin
	public void adminMenu() {
		if (!adminLogin()) return;
		
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
	
	public boolean adminLogin() {
		String email, password, cancel;
		do {
			System.out.print("\nEmail: ");
			email = scan.nextLine();
			
			System.out.print("\nPassword: ");
			password = scan.nextLine();
			
			if (!UserRepository.login(email, password)) {
				do {
					System.out.print("Incorrect user credentials. Do you want to go back? [Yes | No] ");
					cancel = scan.nextLine();
					
					if (cancel.equalsIgnoreCase("Yes")) {
						return false;
					} else if (cancel.equalsIgnoreCase("No")) {
						break;
					}
				} while (!(cancel.equalsIgnoreCase("Yes") || cancel.equalsIgnoreCase("No")));
			}
		} while (!UserRepository.login(email, password));
		
		return true;
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

	
	
	// Driver
	public void driverMenu() {
		// TODO Auto-generated method stub
		
	}

	
	
	// Customer
	public void customerMenu() {
		boolean isRunning = true;
		
		while (isRunning) {
			utils.clearScreen();
			
			printOrders();
			
			System.out.println("Welcome! What do you want to do?");
			System.out.println("1. Add Order");
			System.out.println("2. Cancel Order");
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
//					addOrder();
					break;
				case 2:
//					cancelOrder();
					break;
				case 3:
					isRunning = false;
					break;
			}
		}

	}
	
	public void printOrders() {
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
