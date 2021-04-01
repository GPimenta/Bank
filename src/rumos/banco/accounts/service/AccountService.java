package rumos.banco.accounts.service;

import java.util.Collection;

import javax.security.auth.login.AccountNotFoundException;

import rumos.banco.accounts.exceptions.AccountConflictException;
import rumos.banco.accounts.model.Account;
import rumos.banco.accounts.repository.IAccountRepository;
import rumos.banco.utils.IPreconditions;

public class AccountService implements IAccountService{
	
	private final IAccountRepository repository;
	
	public AccountService(IAccountRepository repository) {
		this.repository =  IPreconditions.checkNotNull(repository, "Account Repository cannot be null");
	}

	@Override
	public Account createAccount(Integer customerId) throws AccountConflictException {
		Account account = new Account.Builder()
				.withCustomerId(customerId)
				.withAccountNumber("1234")
				.withBalance(0D)
				.build();
		return null;
	}

	@Override
	public void deleteAccount(Integer accountId) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccount(Integer accountId) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Account> findAccountsByCustomerId(Integer customerId) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
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
