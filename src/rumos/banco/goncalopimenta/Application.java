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
				showCustomersByName();
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
				System.out.println("Please write the ID of the customer, in order to edit it");
				Integer id = scanner.nextInt();
				for (int i = 0; i < customers.length; i++) {
					if(customers[i].getId() == id){
						customers[i] = editCustomer(customers[i]);
						//Edit Customer
						return; // ERRO! Ao executar o caso 6 e apos sair do metodo editCustomer, o programa sai por completo em vez de continuar no Menu
					}

				}
				System.err.println("There is no customer with that ID");
				//Edit customer by ID
				break;
			case 7:
				//Delete customer by ID
				break;
			default:
				System.err.println("Invalid option in DisplayMenu");
				break;
			}
		}while(option != 0);
	}

	private static Customer editCustomer(Customer customer) {
		Integer change;
		do {
			displayEditMenu();
			change = scanner.nextInt();

			switch (change) {
			case 1:
				System.out.println("What is the new name?");
				customer.setName(scanner.next());
				//Change Name
				break;
			case 2:
				System.out.println("What is the new password?");
				customer.setPassword(scanner.next());
				//Change password
				break;
			case 3:
				System.out.println("What is the new email?");
				customer.setEmail(scanner.next());
				//change email
				break;

			default:
				System.err.println("Invalid option in EditMenu");
				break;
			}
		}while(change != 0);
		
		return customer;
	}


	private static void displayEditMenu() {
		System.out.println("What do you which to edit?");
		System.out.println("0 - Exit");
		System.out.println("1 - Name of the customer");
		System.out.println("2 - Password of the customer");
		System.out.println("3 - Email of the customer"); 
	}


	private static void showCustomersByName() {
		System.out.println("What is the customer name?");
		String name = scanner.next();

		for (int i = 0; i < customers.length; i++) {
			if(customers[i].getName().toLowerCase().equals(name.toLowerCase())) {
				for (int j = (i+1); j < customers.length; j++) {
					if(customers[j].getName().toLowerCase().equals(name.toLowerCase())) {
						System.err.println("There is another client with the same name. Its necessary to search by the taxId.");
						System.out.printf("Presenting the both clients with the same name \nFirst %s \nSecond %s",customers[i],customers[j]);
						showCustomerByTaxId();
						return;
					}
				}
				System.out.println(customers[i].toString());
				return;
			}
		}
		System.err.println("Name not found!");
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
		String taxId = scanner.next();
		for (int i = 0; i < customers.length; i++) {
			if(customers[i].equals(null)) continue;
			if(customers[i].getTaxId().equals(taxId)) {
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
				return;
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
