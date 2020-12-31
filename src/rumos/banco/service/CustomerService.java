package rumos.banco.service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import rumos.banco.model.Customer;

public class CustomerService {

	private static ArrayList<Customer> customers = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static Integer id = 0;

	/******************************************************************************
	 * Save Customer
	 * 
	 * @return
	 ******************************************************************************/
	public Customer save(Customer customer) {
		id++;
		customer.setId(id);
		customers.add(customer);

		return customer;
	}


	/******************************************************************************
	 * Delete Customer
	 * 
	 * @return
	 ******************************************************************************/
	public Integer deleteCustomerDetails() {
		System.out.println("Please indicate the name of the customer");
		String customerName;
		customerName = scanner.next();
		System.out.println("Please indicate the taxId of the customer");
		String taxId;
		taxId = scanner.next();
		
		
		for (Customer customer : customers) {
			if(customer.getName().equalsIgnoreCase(customerName) && customer.getTaxId().equals(taxId)) {
				customers.remove(customer);
				return customer.getId();
			}
		}
		System.err.println("No customer found");
		return null;
		
	}
	



	/******************************************************************************
	 * Check if there is already an taxID
	 * 
	 * @param taxId
	 * @return
	 ******************************************************************************/
	public Boolean taxIDalreadyExists(String taxId) {

		for (Customer customer : customers) {
			if (customer != null) {
				if (taxId.equals(customer.getTaxId()))
					return true;
			}
		}

		return false;
	}

	/******************************************************************************
	 * Show all customers
	 * 
	 *
	 ******************************************************************************/
	public void showCustomersDetails() {
		for (Customer customer : customers) {
			System.out.println("The customer: ");
			System.out.println(customer.toString());
		}
	}
	/******************************************************************************
	 * Show all customers
	 * 
	 *
	 ******************************************************************************/
	public void showCustomer(Integer customerId) {
		Customer customer = findCustomerById(customerId);
		System.out.println(customer.toString());
		return;
	}
	
	
	/******************************************************************************
	 * Show customer using Name and TaxId
	 * 
	 * @return int Id customer
	 ******************************************************************************/
	public Integer findCustomerByNameAndTaxId() {
		String name;
		String taxId;

		System.out.println("Please indicate the name of the Customer");
		name = scanner.next();
		System.out.println("Please indicate the taxId of the Customer");
		taxId = scanner.next();

		for (Customer customer : customers) {
			if (customer.getName().equals(name) && customer.getTaxId().equals(taxId)) {
				System.out.println(customer.toString());
				return customer.getId();
			}
		}
		System.err.println("Customer not found");
		return null;
	}

	/******************************************************************************
	 * Show customer using Id customer
	 * 
	 *
	 ******************************************************************************/
	public Customer findCustomerById(int customerId) {
		for (Customer customer : customers) {
			if (customer.getId().equals(customerId)) {
				return customer;
			}
		}
		System.err.println("Customer not found");
		return null;
	}



}
