package rumos.banco.accounts.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Optional<Account> findByHolderCustomerId(Integer customerId) {
		
		return getAll().stream().filter(account -> account.getCustomerId() == customerId).findAny();
	}

	@Override
	public Collection<Account> findBySecondaryCustomerId(Integer customerId) {
		Account accountHolder = findByHolderCustomerId(customerId).get();
		return getAll().stream().filter(account -> account.getSecondaryOwnersId()
				.equals(accountHolder)).collect(Collectors.toList());
	}
	@Override
	public Collection<Account> findByAllCustomerId(Integer customerId) {
		Collection<Account> fullList = findBySecondaryCustomerId(customerId);
		fullList.add(findByHolderCustomerId(customerId).get());
		return fullList;
	}

}
