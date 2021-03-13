package rumos.banco.common.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.common.model.IdentificationItem;
import rumos.banco.customers.model.Customer;


public abstract class InMemRepository<T extends IdentificationItem> implements IRepository<T>{
	private final ArrayList<T> repository = new ArrayList<T>(); 
//	private Integer id = 0; See where to put it

	@Override
	public Optional<T> create(T newItem) {
		if(newItem == null) {
			throw new IllegalArgumentException("New Item can not be null");
		}
		repository.add(newItem);
		return Optional.of(newItem);
	}

	@Override
	public boolean deleteById(Integer id) {
		return repository.removeIf(item->item.getId().equals(id));
	}

	@Override
	public Optional<T> update(T newItem) {
		if(deleteById(newItem.getId())){
			repository.add(newItem);
			return Optional.of(newItem);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<T> getById(Integer id) {
		for(T t : repository) {
			if(t.getId().equals(id)) {
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<T> getAll() {
		if(repository.isEmpty()) {
			throw new IllegalArgumentException("Customer repository is empty");
		}
		return repository;
	}

}
