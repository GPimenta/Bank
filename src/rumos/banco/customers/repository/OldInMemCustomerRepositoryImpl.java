package rumos.banco.customers.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.customers.model.old.OldCustomer;

public class OldInMemCustomerRepositoryImpl implements OldICustomerRepository {

	private static ArrayList<OldCustomer> customers = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public void deleteById(Integer id) {
		for (OldCustomer customer : customers) {
			if (customer.getId().equals(id)) {
				customers.remove(customer);
			}
		}

	}

	@Override
	public Collection<OldCustomer> getAll() {
		ArrayList<OldCustomer> result = new ArrayList<OldCustomer>();
		for (OldCustomer customer : customers) {
			OldCustomer newCustomer = new OldCustomer();
			newCustomer.setDateOfBirth(customer.getDateOfBirth());
			newCustomer.setEmail(customer.getEmail());
			newCustomer.setId(customer.getId());
			newCustomer.setName(customer.getName());
			newCustomer.setTaxId(customer.getTaxId());
			result.add(customer);
		}
		return result;

//		return Collections.unmodifiableCollection(customers);

	}

	@Override
	public void create(OldCustomer customer) {
		customer.setId(++id);
		customers.add(customer);

	}

	@Override
	public Optional<OldCustomer> getById(Integer id) {
		for (OldCustomer customer : customers) {
			if(customer.getId().equals(id)) {
				return Optional.of(customer);
			}
		}
		return Optional.empty();
	}

	@Override
	public void update(OldCustomer customer) {
		customers.add(customer); 
		// TODO Auto-generated method stub

	}

}
