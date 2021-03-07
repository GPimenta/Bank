package rumos.banco.common;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<T> {

	Optional<T> create(T newItem);
	
	boolean deleteById(T id);
	
	Optional<T> update(T newItem);
	
	Optional<T> getById(Integer id);
	
	Collection<T> getAll();
	
}
