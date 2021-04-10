package rumos.banco.customers.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.customers.exceptions.CustomerConflictException;
import rumos.banco.customers.exceptions.CustomerNotFoundException;
import rumos.banco.customers.model.Customer;

public interface ICustomerService {
	
	Customer createCustomer(String name, String taxId, String email, LocalDate birthday) throws CustomerConflictException, CustomerNotFoundException;
	
	Optional<Customer> findByTaxId(String taxId) throws CustomerNotFoundException;
	
	Customer getCustomer(Integer customerId) throws CustomerNotFoundException;
	
	void deleteCustomer(Integer customerId) throws CustomerNotFoundException;
	
	Collection<Customer> getAllCustomers() throws CustomerNotFoundException;

}
