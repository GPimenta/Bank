package rumos.banco.accounts.service;

import java.util.Collection;

import rumos.banco.accounts.exceptions.AccountConflictException;
import rumos.banco.accounts.exceptions.AccountNoFundsException;
import rumos.banco.accounts.exceptions.AccountNotFoundException;
import rumos.banco.accounts.exceptions.AccountVoidDepositException;
import rumos.banco.accounts.exceptions.AccountVoidWithdrawException;
import rumos.banco.accounts.model.Account;

public interface IAccountService {
	
	Account createAccount(Integer customerId) throws AccountConflictException;
	
	void deleteAccount(Integer accountId) throws AccountNotFoundException;
	
	Account getAccount(Integer accountId) throws AccountNotFoundException;
	
	Account findAccountByHolderCustomerId(Integer customerId) throws AccountNotFoundException;
	
	Collection<Account> findAccountsBySecondaryCustomerId(Integer customerId);
	
	Collection<Account> findAllAccountsByCustomerId(Integer customerId);
	
	void depositAccount(Integer accountId, Double amount) throws AccountConflictException, AccountNotFoundException, AccountVoidDepositException;
	
	void withdrawAccount(Integer accountId, Double amount) throws AccountConflictException, AccountNotFoundException, AccountVoidWithdrawException, AccountNoFundsException;
	
	void transferMoney(Integer fromAccount, Integer toAccount, Double amount) throws AccountConflictException, AccountNotFoundException, AccountVoidWithdrawException, AccountNoFundsException, AccountVoidDepositException;
	
	void addSecondaryOwner(Integer accountId, Integer customerId) throws AccountConflictException, AccountNotFoundException;
	
	void deleteSecondaryOwner(Integer AccountId, Integer customerId) throws AccountConflictException, AccountNotFoundException;
	
}
