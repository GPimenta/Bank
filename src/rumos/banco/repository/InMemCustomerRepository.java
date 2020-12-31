package rumos.banco.repository;

import java.util.ArrayList;
import java.util.Collection;

import rumos.banco.model.Customer;

public class InMemCustomerRepository implements ICustomerRepository {

	private static ArrayList<Customer> customers = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public void deleteById(Integer id) {

		for (Customer customer : customers) {
			if (customer.getId().equals(id)) {
				customers.remove(customer);
			}
		}

	}

	@Override
	public Collection<Customer> getAll() {
		ArrayList<Customer> result = new ArrayList<Customer>();
		for (Customer customer : customers) {
			Customer newCustomer = new Customer();
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
	public void create(Customer customer) {
		customer.setId(++id);
		customers.add(customer);

	}

	@Override
	public void getById(Integer Id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Customer customer) {
		customers.add(customer); 
		// TODO Auto-generated method stub

	}

}
