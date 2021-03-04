package rumos.banco.accounts.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.accounts.model.Account;
import rumos.banco.cards.model.Card;

public interface IAccountRepository {

	Optional<Account> getById(Integer id);
	
	Collection<Account> getAll();
	
	void create(Account account);
	
	void deleteById(Integer id);
	
	public void deleteByCustomerId(Integer customerId);
	
	void update(Account account);
}
