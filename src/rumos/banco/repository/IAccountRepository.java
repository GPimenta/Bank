package rumos.banco.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Account;
import rumos.banco.model.Card;

public interface IAccountRepository {

	Optional<Account> getById(Integer id);
	
	Collection<Account> getAll();
	
	void create(Account account);
	
	void deleteById(Integer id);
	
	public void deleteByCustomerId(Integer customerId);
	
	void update(Account account);
}
