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
		if (repository.getByTaxId(taxId).isPresent()) {
			throw new CustomerNotFoundException("Customer cannot be created "
					+ "since there is already a Customer with taxId '%s' given", taxId);
		}

		return repository.create(customer)
				.orElseThrow(() -> new CustomerConflictException("Conflict on creating "
						+ "account with customer Id: '%i'", customer.getId()));
	}

	@Override
	public Customer findByTaxId(String taxId) throws CustomerNotFoundException {
		
		return repository.getByTaxId(taxId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with taxId '%s' cannot be found", taxId));
	}

	@Override
	public Customer getCustomer(Integer customerId) throws CustomerNotFoundException {
		return repository.getById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer with Id '%s' cannot be found", customerId));
	}

	@Override
	public void deleteCustomer(Integer customerId) throws CustomerNotFoundException {
		if (!repository.deleteById(customerId)) {
			throw new CustomerNotFoundException("Customer with Id: '%d' cannot be found", customerId);
		}
		
	}

	@Override
	public Collection<Customer> getAllCustomers() throws CustomerNotFoundException {
		return repository.getAll();
	}

}
