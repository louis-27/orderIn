package main;

import java.util.Scanner;

public class Utils {
	
	static Scanner scan = new Scanner(System.in);

	public void clearScreen() {
		for (int i = 0; i < 25; i++) {
			System.out.println("\n");
		}
	}
	
	public void delayedPrint(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			try {
				Thread.sleep(100);
				System.out.print(strings[i]);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void orderInScreen() {
		String[] orderInText = {"\n"
				, "_______/\\\\\\\\\\____________________________/\\\\\\________________________________/\\\\\\\\\\\\\\\\\\\\\\_______________        \n"
				, " _____/\\\\\\///\\\\\\_________________________\\/\\\\\\_______________________________\\/////\\\\\\///________________       \n"
				, "  ___/\\\\\\/__\\///\\\\\\_______________________\\/\\\\\\___________________________________\\/\\\\\\___________________      \n"
				, "   __/\\\\\\______\\//\\\\\\__/\\\\/\\\\\\\\\\\\\\_________\\/\\\\\\______/\\\\\\\\\\\\\\\\___/\\\\/\\\\\\\\\\\\\\______\\/\\\\\\______/\\\\/\\\\\\\\\\\\___     \n"
				, "    _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\/////\\\\\\___/\\\\\\\\\\\\\\\\\\____/\\\\\\/////\\\\\\_\\/\\\\\\/////\\\\\\_____\\/\\\\\\_____\\/\\\\\\////\\\\\\__    \n"
				, "     _\\//\\\\\\______/\\\\\\__\\/\\\\\\___\\///___/\\\\\\////\\\\\\___/\\\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\___\\///______\\/\\\\\\_____\\/\\\\\\__\\//\\\\\\_   \n"
				, "      __\\///\\\\\\__/\\\\\\____\\/\\\\\\_________\\/\\\\\\__\\/\\\\\\__\\//\\\\///////___\\/\\\\\\_____________\\/\\\\\\_____\\/\\\\\\___\\/\\\\\\_  \n"
				, "       ____\\///\\\\\\\\\\/_____\\/\\\\\\_________\\//\\\\\\\\\\\\\\/\\\\__\\//\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\__________/\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\___\\/\\\\\\_ \n"
				, "        ______\\/////_______\\///___________\\///////\\//____\\//////////__\\///__________\\///////////__\\///____\\///__\n"
				, ""};
		
		delayedPrint(orderInText);
	}
	
	public void waitUser() {
		System.out.println("Press ENTER to continue");
		scan.nextLine();
	}

}