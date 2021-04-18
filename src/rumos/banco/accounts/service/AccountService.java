package rumos.banco.accounts.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import rumos.banco.accounts.exceptions.AccountConflictException;
import rumos.banco.accounts.exceptions.AccountNoFundsException;
import rumos.banco.accounts.exceptions.AccountNotFoundException;
import rumos.banco.accounts.exceptions.AccountVoidDepositException;
import rumos.banco.accounts.exceptions.AccountVoidWithdrawException;
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
//		repository.deleteById(accountId);
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
		return repository.findByHolderCustomerId(customerId).orElseThrow(() -> new AccountNotFoundException("Account not found with customer Id: %d ", customerId));
	}

	@Override
	public Collection<Account> findAccountsBySecondaryCustomerId(Integer customerId) {
		return repository.findBySecondaryCustomerId(customerId);
	}
	
	
	
	
	@Override
	public void depositAccount(Integer accountId, Double amount)
			throws AccountConflictException, AccountNotFoundException, AccountVoidDepositException {
		if (amount == 0 || amount < 0) {
			throw new AccountVoidDepositException("Cannot deposit with '%.2f' ", amount);
		}
		Account account = getAccount(accountId);
		account.setBalance(account.getBalance() + amount);
		repository.update(account);
		
	}

	@Override
	public void withdrawAccount(Integer accountId, Double amount)
			throws AccountConflictException, AccountNotFoundException, AccountVoidWithdrawException, AccountNoFundsException {
		if(amount == 0 || amount < 0) {
			throw new AccountVoidWithdrawException("Cannot withdraw with '%.2f' ", amount);
		}
		Account account = getAccount(accountId);
		
		if (account.getBalance() == 0) {
			throw new AccountNoFundsException("There is no funds on the account Id: '%.2f' , in order to withdraw", accountId);
		}
		
		if (account.getBalance() - amount < 0) {
			throw new AccountNoFundsException("Not enough funds to withdraw '%.2f'", amount);
		}
		
		account.setBalance(account.getBalance() - amount);
		
		repository.update(account);
		
	}

	@Override
	public void transferMoney(Integer fromAccount, Integer toAccount, Double amount)
			throws AccountConflictException, AccountNotFoundException, AccountVoidWithdrawException, AccountNoFundsException, AccountVoidDepositException {
		Account withdrawFrom = getAccount(fromAccount);
		Account depositoTo = getAccount(toAccount);
		
		if(fromAccount.equals(toAccount)) {
			throw new AccountConflictException("Cannot tranfer money, from AccountId: '%d' to the same AccountId: '%d'", fromAccount, toAccount);
		}
		
		withdrawAccount(fromAccount, amount);
		repository.update(withdrawFrom);
		
		depositAccount(toAccount, amount);
		repository.update(depositoTo);
		
	}

	@Override
	public void addSecondaryOwner(Integer accountId, Integer customerId)
			throws AccountConflictException, AccountNotFoundException {
		
		Account account = getAccount(accountId);
		List<Integer> secondaryOwners = account.getSecondaryOwnersId();
		
		if(getAccount(accountId).getCustomerId() == customerId) {
			throw new AccountConflictException("account with ID '%s' already has customer with ID '%s' as primary owner", accountId, customerId); 
		}
		
		if (secondaryOwners.size() >= Account.MAX_NUMBER_SECONDARY_OWNERS) {
			throw new AccountConflictException("The account with Id '%d' has exceed the number of secondary Owners, which is: '%d'", accountId, Account.MAX_NUMBER_SECONDARY_OWNERS);
		}
		
		if (secondaryOwners.contains(customerId)) {
			throw new AccountConflictException("The customer with Id '%d' is already on the secondary Owner list of this account Id '%d'", customerId, accountId);
		}
//		if () {
//			
//		}
		
		account.getSecondaryOwnersId().add(customerId);
//		secondaryOwners.add(customerId);
//		account.setSecondaryOwnersId(secondaryOwners);
		repository.update(account);
	}

	@Override
	public void deleteSecondaryOwner(Integer accountId, Integer customerId)
			throws AccountConflictException, AccountNotFoundException {
		
		Account account = getAccount(accountId);
		Collection<Integer> secondaryOwners = account.getSecondaryOwnersId();
		
		if(getAccount(accountId).getCustomerId() == customerId) {
			throw new AccountConflictException("account with Id '%d' already has customer with Id '%d' as primary owner", accountId, customerId); 
		}
		if (secondaryOwners.isEmpty()) {
			throw new AccountConflictException("The account with Id '%d' is empty", accountId);
		}
		
		if(!secondaryOwners.remove(customerId)) {
			throw new AccountNotFoundException("Customer with Id: '%d' not found, on secondaryOwner List", customerId);
		}
		//TODO Need to had Validation if customer exists
		
		account.getSecondaryOwnersId().remove(customerId);
		repository.update(account);
	}



}