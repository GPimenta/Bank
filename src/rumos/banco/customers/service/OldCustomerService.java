package rumos.banco.customers.service;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.customers.model.OldCustomer;
import rumos.banco.customers.repository.OldICustomerRepository;

public class OldCustomerService {

	private OldICustomerRepository repository;
	
	/******************************************************************************
	 * Constructor
	 * 
	 * @return
	 ******************************************************************************/
	
	public OldCustomerService(OldICustomerRepository repository) {
		this.repository = repository;
				
	}
	

	/******************************************************************************
	 * Save Customer
	 * 
	 * @return
	 ******************************************************************************/

	public void create(OldCustomer customer) {
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

		Collection<OldCustomer> customers = repository.getAll();
		
		for (OldCustomer customer : customers) {
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
		Collection<OldCustomer> customers = repository.getAll();

		for (OldCustomer customer : customers) {
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
		Collection<OldCustomer> customers = repository.getAll();
		
		for (OldCustomer customer : customers) {
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
		
		Optional<OldCustomer> customer = repository.getById(customerId);
		System.out.println(customer.toString());
		return;
	}
	
	
	/******************************************************************************
	 * Show customer using Name and TaxId
	 * 
	 * @return int Id customer
	 ******************************************************************************/
	public Integer findCustomerByNameAndTaxId(String name, String taxId) {

		Collection<OldCustomer> customers = repository.getAll();
		
		for (OldCustomer customer : customers) {
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
	public OldCustomer getCustomerById(Integer customerId) {
		
		Optional<OldCustomer> customerOpt = repository.getById(customerId);
		if(customerOpt != null && customerOpt.isPresent()) {
			return customerOpt.get();
		}
		return null;
	}



}
