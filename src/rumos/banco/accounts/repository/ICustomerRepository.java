package rumos.banco.accounts.repository;

import java.util.Optional;

import rumos.banco.common.IRepository;
import rumos.banco.customers.model.Customer;

public interface ICustomerRepository extends IRepository<Customer> {
	public Optional<Customer> getByTaxId(String taxID);
}
