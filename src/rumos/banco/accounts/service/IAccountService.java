package rumos.banco.accounts.service;

import java.util.Collection;

import rumos.banco.accounts.exceptions.AccountConflictException;
import rumos.banco.accounts.exceptions.AccountNotFoundException;
import rumos.banco.accounts.model.Account;

public interface IAccountService {
	
	Account createAccount(Integer customerId) throws AccountConflictException;
	
	void deleteAccount(Integer accountId) throws AccountNotFoundException;
	
	Account getAccount(Integer accountId) throws AccountNotFoundException;
	
	Account findAccountByHolderCustomerId(Integer customerId) throws AccountNotFoundException;
	
	Collection<Account> findAccountsBySecondaryCustomerId(Integer customerId);
	
	Collection<Account> findAllAccountsByCustomerId(Integer customerId);
	
	void debitAccount(Integer accountId, Integer amount) throws AccountConflictException, AccountNotFoundException;
	
	void withdrawAccount(Integer accountId, Integer amount) throws AccountConflictException, AccountNotFoundException;
	
	void transferMoney(Integer fromAccount, Integer toAccount, Integer amount) throws AccountConflictException, AccountNotFoundException;
	
	void addSecondaryOwner(Integer accountId, Integer customerId) throws AccountConflictException, AccountNotFoundException;
	
	void deleteSecondaryOwner(Integer AccountId, Integer customerId) throws AccountConflictException, AccountNotFoundException;
	
}
