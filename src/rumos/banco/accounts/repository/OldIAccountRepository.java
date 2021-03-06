package rumos.banco.accounts.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.accounts.model.OldAccount;
import rumos.banco.cards.model.OldCard;

public interface OldIAccountRepository {

	Optional<OldAccount> getById(Integer id);
	
	Collection<OldAccount> getAll();
	
	void create(OldAccount account);
	
	void deleteById(Integer id);
	
	public void deleteByCustomerId(Integer customerId);
	
	void update(OldAccount account);
}
