package rumos.banco.customers.repository.old;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.customers.model.old.OldCustomer;

public interface OldICustomerRepository {

	void deleteById(Integer id);
	
	Collection<OldCustomer> getAll();
	
	void create(OldCustomer customer);
	
	Optional<OldCustomer> getById(Integer id);
	
	void update(OldCustomer customer);

}
