package rumos.banco.accounts.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.customers.model.Customer;

public class InMemCustomerRepositoryImpl implements ICustomerRepository {

	@Override
	public Optional<Customer> create(Customer newItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(Customer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Customer> update(Customer newItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Customer> getByTaxId(String taxID) {
		// TODO Auto-generated method stub
		return null;
	}

}
