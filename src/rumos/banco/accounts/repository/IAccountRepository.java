package rumos.banco.accounts.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.accounts.model.Account;
import rumos.banco.common.repository.IRepository;

public interface IAccountRepository extends IRepository<Account>{
	Optional <Account> findByHolderCustomerId(Integer customerId);
	Collection<Account> findBySecondaryCustomerId(Integer customerId);
	Collection<Account> findByAllCustomerId(Integer customerId);
}
