package rumos.banco.common.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.common.model.IdentificationItem;

public interface IRepository<T extends IdentificationItem> {

	Optional<T> create(T newItem);
	
	boolean deleteById(Integer id);
	
	Optional<T> update(T newItem);
	
	Optional<T> getById(Integer id);
	
	Collection<T> getAll();
	
}
