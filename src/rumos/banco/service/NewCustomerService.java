package rumos.banco.service;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Customer;
import rumos.banco.repository.ICustomerRepository;

public class NewCustomerService {

	private ICustomerRepository repository;

	//TODO pq é necessario ter este construtor?
	//TODO nao usamos static na classe service nem na classe model, pq so fazemos static na lista q se encontra no repositorio??
	public NewCustomerService(ICustomerRepository repository) {
		this.repository = repository;
	}

	public void deleteByTaxID(Integer taxId) {
		Optional<Customer> optCustomer = getByTaxId(taxId);
		if (optCustomer.isPresent()) {
			deleteById(optCustomer.get().getId());
		}

		// TODO Equivalente ao de cima
//		getByTaxId(taxId).ifPresent(customer->{
//			deleteById(customer.getId());
//		});

	}

	public void deleteById(Integer Id) {
		repository.deleteById(Id);
	}

	/**
	 * ~ Validar os inputs e extender funcionalidades nao disponiveis do
	 * repositorio/
	 * 
	 * @param taxId
	 * @return
	 */

	public Optional<Customer> getByTaxId(Integer taxId) {
		if (taxId == null) {
			return Optional.empty();
		}
		Collection<Customer> customers = repository.getAll();

		for (Customer customer : customers) {
			if (customer.getTaxId().equals(taxId)) {
				return Optional.of(customer);
			}
		}
		return Optional.empty();
	}
	public void create(Customer customer) {
		if(customer == null) {
			//lancar erro 
			throw new IllegalArgumentException("Failed to create customer - Invalid customer object");
		}
		if(customer.getTaxId() == null) {
			throw new IllegalArgumentException("Failed to create customer - Invalid taxID ");
		}
		repository.create(customer);
	}
}
