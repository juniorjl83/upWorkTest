package com.onlinetest.upwork;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Scanner;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UpWorkTestApplication implements CommandLineRunner {
	

	private static String CARRIAGE_RETURN = "\n";
	private Scanner scanner = new Scanner(System.in);
	private Network network;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(UpWorkTestApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		int option = 0;

		String mainMenu = printMainMenu();

		while (option != 9) {
			System.out.println(mainMenu);

			try {
				option = Integer.valueOf(scanner.next());
			} catch (NumberFormatException e) {
				option = 0;
			}

			switch (option) {
			case 1:
				elementCreation();
				break;
			case 2:
				connect();
				break;
			case 3:
				System.out.println("opcion 3:");
				break;
			case 9:
				System.out.println("exit:");
				break;
			default:
				System.out.println("wrong option:");
			}
		}
		scanner.close();
	}
	
	private void elementCreation() {
		if (network == null) {
			System.out.println(printCreationMenu());
			int size = 0;

			while (size == 0) {
				try {
					size = Integer.valueOf(scanner.next());
				} catch (NumberFormatException e) {
					System.out.println("wrong value.." + CARRIAGE_RETURN);
					System.out.println("Type the amount of elements:" + CARRIAGE_RETURN);
					size = 0;
				}
			}
			network = new Network(size);
			System.out.println("Elements created successfully .." + CARRIAGE_RETURN);
		} else {
			System.out.println("Error - Elements already exists .." + CARRIAGE_RETURN);
		}
	}
	
	private void connect() {
		if (network != null) {
			boolean created = false;
			do {
				System.out.println(printConnectMenu());
				String[] values = scanner.next().split(",");
				try {
					checkArgument(values.length == 2, "wrong format");
					int left = Integer.valueOf(values[0]);
					int right = Integer.valueOf(values[1]);
					network.connect(left, right);
					System.out.println("Elements conected successfully .." + CARRIAGE_RETURN);
					created = true;
				} catch (Exception e) {
					System.out.println("invalid input.." + e.getMessage() + CARRIAGE_RETURN);
				}
			}while(!created);
			
		} else {
			System.out.println("Error - You must create the elements first .." + CARRIAGE_RETURN);
		}
	}

	private String printMainMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append("***********Connections Program***********");
		menu.append(CARRIAGE_RETURN);
		menu.append("Menu Options:");
		menu.append(CARRIAGE_RETURN);
		menu.append(" 1. Elements creation.");
		menu.append(CARRIAGE_RETURN);
		menu.append(" 2. Connect two elements.");
		menu.append(CARRIAGE_RETURN);
		menu.append(" 3. Varify if two elements are connected.");
		menu.append(CARRIAGE_RETURN);
		menu.append(" 9. Exit.");
		menu.append(CARRIAGE_RETURN);
		return menu.toString();
	}
	
	private String printCreationMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append(CARRIAGE_RETURN);
		menu.append("***********Elements Creation***********");
		menu.append(CARRIAGE_RETURN);
		menu.append("Type the amount of elements:");
		menu.append(CARRIAGE_RETURN);
		return menu.toString();
	}
	
	private String printQueryMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append(CARRIAGE_RETURN);
		menu.append("***********Elements Creation***********");
		menu.append(CARRIAGE_RETURN);
		menu.append("Type the amount of elements:");
		menu.append(CARRIAGE_RETURN);
		return menu.toString();
	}
	
	private String printConnectMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append(CARRIAGE_RETURN);
		menu.append("***********Elements Connection***********");
		menu.append(CARRIAGE_RETURN);
		menu.append("Type elements to connect format -> x,y:");
		menu.append(CARRIAGE_RETURN);
		return menu.toString();
	}
}
