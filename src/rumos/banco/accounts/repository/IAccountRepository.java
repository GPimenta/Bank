package rumos.banco.accounts.repository;

import java.util.Collection;

import rumos.banco.accounts.model.Account;
import rumos.banco.common.repository.IRepository;

public interface IAccountRepository extends IRepository<Account>{
	Account findByHolderCustomerId(String customerId);
	Collection<Account> findBySecundaryCustomerId(String customerId);
	Collection<Account> findByAllCustomerId(String customerId);
}
