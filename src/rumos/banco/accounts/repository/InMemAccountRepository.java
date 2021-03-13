package rumos.banco.accounts.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.accounts.model.Account;
import rumos.banco.common.repository.InMemRepository;

public class InMemAccountRepository extends InMemRepository<Account> implements IAccountRepository {

	private static Integer accountIdCounter = 0;

	private static int generateAccountID() {
		return ++accountIdCounter;
	}

	@Override
	public Optional<Account> create(Account newItem) {
		Account account = newItem;
		account.set
		return null;
	}

	@Override
	public Account findByHolderCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Account> findBySecundaryCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Account> findByAllCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
