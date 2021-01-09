package rumos.banco.service;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Customer;
import rumos.banco.repository.ICustomerRepository;

public class CustomerService {

	private ICustomerRepository repository;
	
	/******************************************************************************
	 * Constructor
	 * 
	 * @return
	 ******************************************************************************/
	
	public CustomerService(ICustomerRepository repository) {
		this.repository = repository;
				
	}
	

	/******************************************************************************
	 * Save Customer
	 * 
	 * @return
	 ******************************************************************************/

	public void create(Customer customer) {
		if(customer == null) {
			throw new IllegalArgumentException("Failed to create customer - Invalid customer object");
		}
		repository.create(customer);
	}


	/******************************************************************************
	 * Delete Customer
	 * 
	 * @return
	 ******************************************************************************/
	public Integer deleteCustomerDetails(String name, String taxId) {

		Collection<Customer> customers = repository.getAll();
		
		for (Customer customer : customers) {
			if(customer.getName().equalsIgnoreCase(name) && customer.getTaxId().equals(taxId)) {
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
		Collection<Customer> customers = repository.getAll();

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
		Collection<Customer> customers = repository.getAll();
		
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
		
		Optional<Customer> customer = repository.getById(customerId);
		System.out.println(customer.toString());
		return;
	}
	
	
	/******************************************************************************
	 * Show customer using Name and TaxId
	 * 
	 * @return int Id customer
	 ******************************************************************************/
	public Integer findCustomerByNameAndTaxId(String name, String taxId) {

		Collection<Customer> customers = repository.getAll();
		
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
	 *TODO ISTO INVIABILIZA o OPTIONAL, NAO SEI COMO FAZER
	 ******************************************************************************/
	public Customer getCustomerById(Integer customerId) {
		
		Optional<Customer> customerOpt = repository.getById(customerId);
		if(customerOpt != null && customerOpt.isPresent()) {
			return customerOpt.get();
		}
		return null;
	}



}
