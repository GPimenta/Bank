package rumos.banco.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


public abstract class InMemRepository<T> implements IRepository<T>{
	private final ArrayList<T> repository = new ArrayList<T>(); 
//	private Integer id = 0; See where to put it

	@Override
	public Optional<T> create(T newItem) {
		repository.add(newItem);
		return Optional.of(newItem);
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<T> update(T newItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<T> getById(Integer id) {
		return null;
	}

	@Override
	public Collection<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
