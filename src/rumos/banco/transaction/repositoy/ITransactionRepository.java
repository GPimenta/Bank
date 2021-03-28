package rumos.banco.transaction.repositoy;

import java.util.Collection;

import rumos.banco.common.repository.IRepository;
import rumos.banco.transaction.model.Transaction;

public interface ITransactionRepository extends IRepository<Transaction>{
	
	Collection<Transaction> findByCustomerId(Integer customerId);

	Collection<Transaction> findByAccountId(Integer accountId);
	
	Collection<Transaction> findByCustomerIdAndAccountId(Integer customerId, Integer accountId);


}
