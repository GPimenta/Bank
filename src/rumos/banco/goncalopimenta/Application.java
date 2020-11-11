package rumos.banco.goncalopimenta;

import java.util.Scanner;

public class Application {
	private static Customer[] customers = new Customer[3];
	private static Scanner scanner = new Scanner(System.in);
	private static Integer option;
	

	public static void main(String[] args) {

		displayMenu();
		
		option = scanner.nextInt();
		
		switch (option) {
		case 1:
			Customer customer = new Customer();
			for(int i = 0; i < customers.length; i++  ) {
				if(customers[i] == null) {
					customers[i] = customer;
					System.out.println("New customer created");
					return;
				}

			}
			break;
		case 2:
			//Show costumer by name
			break;
		case 3:
			//Show costumer by taxId
			break;
		case 4:
			//Show all costumers
			break;
		case 5:
			//Show customer
			break;
		case 6:
			//Edit customer by ID
			break;
		case 7:
			//Delete customer by ID
			break;
		default:
			break;
		}
	}


	private static void displayMenu() {
		System.out.println("Welcome, from the list below please choose the action that u want take: ");
		System.out.println("0 - Exit");
		System.out.println("1 - Create new customer");
		System.out.println("2 - Show customer by name");
		System.out.println("3 - Show customer by taxId"); 
		System.out.println("4 - Show all customers"); 
		System.out.println("5 - Show customer");
		System.out.println("6 - Edit customer by Id");
		System.out.println("7 - Delete customer by Id");
	}

}
