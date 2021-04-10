package rumos.banco.customers.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.accounts.exceptions.AccountConflictException;
import rumos.banco.customers.exceptions.CustomerConflictException;
import rumos.banco.customers.exceptions.CustomerNotFoundException;
import rumos.banco.customers.model.Customer;
import rumos.banco.customers.repository.ICustomerRepository;
import rumos.banco.utils.IPreconditions;

public class CustomerService implements ICustomerService{
	
	private final ICustomerRepository repository;
	
	public CustomerService(ICustomerRepository repository) {
		this.repository = IPreconditions.checkNotNull(repository, "Customer Repositoru cannot be null");
	}

	@Override
	public Customer createCustomer(String name, String taxId, String email, LocalDate birthday)
			throws CustomerConflictException, CustomerNotFoundException {
		Customer customer = new Customer.Builder()
				.withName(name)
				.withTaxId(taxId)
				.withEmail(email)
				.withBirthday(birthday)
				.build();
		if (findByTaxId(taxId).isPresent()) {
			throw new CustomerNotFoundException("Customer cannot be created "
					+ "since there is already a Customer with Id '%d' given", findByTaxId(taxId).get().getTaxId());
		}

		return repository.create(customer)
				.orElseThrow(() -> new CustomerConflictException("Conflict on creating "
						+ "account with customer Id: '%i'", customer.getId()));
	}

	@Override
	public Customer findByTaxId(String taxId) throws CustomerNotFoundException {
		return getAllCustomers().stream()
				.filter(customer -> customer.getTaxId().equals(taxId)).findAny()
				.orElseThrow(() -> CustomerNotFoundException("Customer with taxId '%s' not found", taxId));
	}

	@Override
	public Customer getCustomer(Integer customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Integer customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Customer> getAllCustomers() throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
