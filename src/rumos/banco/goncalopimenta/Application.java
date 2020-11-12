package rumos.banco.goncalopimenta;

import java.time.LocalDate;
import java.util.Scanner;


public class Application {
	private static Customer[] customers = new Customer[3];
	private static Scanner scanner = new Scanner(System.in);
	private static Integer option;


	public static void main(String[] args) {

		do {
			displayMenu();
			option = scanner.nextInt();

			switch (option) {
			case 1:
				createNewCustomer();
				break;
			case 2:
				//Show costumer by name
				break;
			case 3:
				showCustomerByTaxId();
				//Show costumer by taxId
				break;
			case 4:
				showAllCustomers();
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
				System.err.println("Invalid option");
				break;
			}
		}while(option != 0);
	}


	private static void showAllCustomers() {
		System.out.println("Printing all clients");
		for(int i = 0; i< customers.length; i++) {
			if(customers[i] != null) {
				System.out.println(customers[i].toString());
			}
		}
	}


	private static void showCustomerByTaxId() {
		System.out.println("What is costumer by taxID ?");
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].equals(null)) continue;
			if(customers[i].getTaxId().equals(scanner.next())) {
				System.out.println("The costumer is: " + customers[i].toString());
				return;
			}
		}
	}


	private static void createNewCustomer() {
		for(int i = 0; i < customers.length; i++  ) {
			if(customers[i] == null) {
				customers[i] = populateEmptyDatabase();
				System.out.println("New customer created");
				return; // PERGUNTAR AO PROF PQ NAO ERA POSSIVEL O BREAK! SERA PQ O BREAK SAI SO DO FOR E QUEREMOS SAIR DO SWITCH??
			}
		}
		System.err.println("The Clients vector is full");
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

	private static Customer populateEmptyDatabase() {
		System.out.println("Creating new client");
		Customer newCustomer = new Customer();

		System.out.println("Please set Id");
		newCustomer.setId(scanner.nextInt());

		System.out.println("Please set Name");
		newCustomer.setName(scanner.next());

		System.out.println("Please set Password");
		newCustomer.setPassword(scanner.next());

		System.out.println("Please set TaxId");
		newCustomer.setTaxId(scanner.next());

		System.out.println("Please set email");
		newCustomer.setEmail(scanner.next());

		System.out.print("Customer day of birth ");
		Integer day = scanner.nextInt();
		System.out.print("Customer month of birth ");
		Integer month = scanner.nextInt();
		System.out.print("Customer year of birth ");
		Integer year = scanner.nextInt();
		newCustomer.setDateOfBirth(LocalDate.of(year, month, day));

		System.out.println("Please set the customer balance");
		newCustomer.setBalance(scanner.nextDouble());

		return newCustomer;
	}

}
