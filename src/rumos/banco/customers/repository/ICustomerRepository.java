package rumos.banco.customers.repository;

import java.util.Optional;

import rumos.banco.common.model.IdentificationItem;
import rumos.banco.common.repository.IRepository;
import rumos.banco.customers.model.Customer;

public interface ICustomerRepository extends IRepository<Customer>,IdentificationItem {
	public Optional<Customer> getByTaxId(String taxID);
}
