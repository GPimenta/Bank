package rumos.banco.accounts.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import rumos.banco.accounts.exceptions.AccountConflictException;
import rumos.banco.accounts.exceptions.AccountNotFoundException;
import rumos.banco.accounts.model.Account;
import rumos.banco.accounts.repository.IAccountRepository;
import rumos.banco.utils.INumbersGenerator;
import rumos.banco.utils.IPreconditions;

public class AccountService implements IAccountService{
	
	
	private final IAccountRepository repository;
	
	public AccountService(IAccountRepository repository) {
		this.repository =  IPreconditions.checkNotNull(repository, "Account Repository cannot be null");
	}
	
	private static String generateAccountNumber() {
		return INumbersGenerator.createString(Account.ACCOUNT_NUMBER_LENGTH);
	}
	
	private static String generateAccountPassword() {
		return INumbersGenerator.createString(Account.ACCOUNT_PASSWORD_LENGTH);
	}

	@Override
	public Account createAccount(Integer customerId) throws AccountConflictException {
		Account account = new Account.Builder()
				.withCustomerId(customerId)
				.withAccountNumber(generateAccountNumber())
				.withBalance(0D)
				.withPasswordAccount(generateAccountPassword())
				.build();
		return repository.create(account)
				.orElseThrow(() -> new AccountConflictException("Conflict on creating account with customer Id: '%i'", customerId));
	}

	@Override
	public void deleteAccount(Integer accountId) throws AccountNotFoundException {
		if(!repository.deleteById(accountId)) {
			throw new AccountNotFoundException("Account with Id: '%d' not found to delete", accountId);
		}
		//TODO SERA Q ELIMINA?
	}

	@Override
	public Account getAccount(Integer accountId) throws AccountNotFoundException {
		
		return repository.getById(accountId)
				.orElseThrow(() -> new AccountNotFoundException("Account with Id: '%d' not found to get Account", accountId));
	}

	@Override
	public Collection<Account> findAllAccountsByCustomerId(Integer customerId) {
		if (Objects.isNull(customerId) || customerId == 0) {
			return Collections.emptyList();
		}
		return repository.findByAllCustomerId(customerId);
	}
	
	
	@Override
	public Account findAccountByHolderCustomerId(Integer customerId) throws AccountNotFoundException {
		return repository.findByHolderCustomerId(customerId).orElseThrow(() -> new AccountNotFoundException("Account not found with customer Id: %i ", customerId));
	}

	@Override
	public Collection<Account> findAccountsBySecondaryCustomerId(Integer customerId) {
		return repository.findBySecondaryCustomerId(customerId);
	}
	
	
	
	
	@Override
	public void debitAccount(Integer accountId, Integer amount)
			throws AccountConflictException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawAccount(Integer accountId, Integer amount)
			throws AccountConflictException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferMoney(Integer fromAccount, Integer toAccount, Integer amount)
			throws AccountConflictException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSecondaryOwner(Integer accountId, Integer customerId)
			throws AccountConflictException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSecondaryOwner(Integer AccountId, Integer customerId)
			throws AccountConflictException, AccountNotFoundException {
		// TODO Auto-generated method stub
		
	}



}
