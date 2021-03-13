package rumos.banco.accounts.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.accounts.model.Account;
import rumos.banco.common.repository.InMemRepository;

public class InMemAccountRepository extends InMemRepository<Account> implements IAccountRepository {

	private static Integer accountIdCounter = 0;

	private static int generateAccountId() {
		return ++accountIdCounter;
	}

	@Override
	public Optional<Account> create(Account newItem) {

		if (getAll().stream().anyMatch(account -> account.getAccountNumber().equals(newItem.getAccountNumber()))) {
			return Optional.empty();
		}
		Account account = newItem;
		account.setId(generateAccountId());
		return super.create(account);
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
