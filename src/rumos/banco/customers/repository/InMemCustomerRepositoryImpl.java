package rumos.banco.customers.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.customers.model.Customer;

public class InMemCustomerRepositoryImpl implements ICustomerRepository {

	private final ArrayList<Customer> repository = new ArrayList<>();//see if it is better to use SET instead of ArrayList
	
	
	@Override
	public Optional<Customer> create(Customer newItem) {
		if(newItem == null) {
			throw new IllegalArgumentException("Customer can not be null");
		}
		repository.add(newItem);
		return Optional.of(newItem);
	}

	@Override
	public boolean deleteById(Integer id) {
//		Optional<Customer> customer = getById(id);
		return repository.removeIf(item->item.getId().equals(id));
	}

	@Override
	public Optional<Customer> update(Customer newItem) {
		if(deleteById(newItem.getId())){
			repository.add(newItem);
			return Optional.of(newItem);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<Customer> getById(Integer id) {
		for(Customer customer : repository) {
			if(customer.getId().equals(id)) {
				return Optional.of(customer);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<Customer> getAll() {
		if(repository.isEmpty()) {
			throw new IllegalArgumentException("Customer repository is empty");
		}
		return repository;
	}

	@Override
	public Optional<Customer> getByTaxId(String taxID) {
		for(Customer customer : repository) {
			if(customer.getTaxId().equals(taxID)) {
				return Optional.of(customer);
			}
		}
		return Optional.empty();
	}




}
