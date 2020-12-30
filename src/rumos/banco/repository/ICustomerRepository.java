package rumos.banco.repository;

import java.util.Collection;

import rumos.banco.model.Customer;

public interface ICustomerRepository {

	void deleteById(Integer id);
	
	Collection<Customer> getAll();
	
	void create(Customer customer);
	
	void getById(Integer Id);
	
	void update(Customer customer);

}
