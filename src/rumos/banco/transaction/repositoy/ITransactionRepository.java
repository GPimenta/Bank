package rumos.banco.transaction.repositoy;

import java.util.Collection;

import rumos.banco.common.repository.IRepository;
import rumos.banco.transaction.model.Transaction;

public interface ITransactionRepository extends IRepository<Transaction>{
	
	Collection<Transaction> findByCardId(Integer cardId);

	Collection<Transaction> findByAccountId(Integer accountId);
	


}
