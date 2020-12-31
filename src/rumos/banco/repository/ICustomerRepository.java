package rumos.banco.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Customer;

public interface ICustomerRepository {

	void deleteById(Integer id);
	
	Collection<Customer> getAll();
	
	void create(Customer customer);
	
	Optional<Customer> getById(Integer id);
	
	void update(Customer customer);

}
