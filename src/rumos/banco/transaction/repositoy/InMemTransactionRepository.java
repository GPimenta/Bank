package rumos.banco.transaction.repositoy;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import rumos.banco.common.repository.InMemRepository;
import rumos.banco.transaction.model.Transaction;

public class InMemTransactionRepository extends InMemRepository<Transaction> implements ITransactionRepository{

	private static Integer transactionIdCounter = 0;

	private static int generateTransactionId() {
		return ++transactionIdCounter;
	}
	
	@Override
	public Optional<Transaction> create(Transaction newItem){
		
		if(getAll().stream().anyMatch(transaction -> transaction.getId().equals(newItem.getId()))) {
			return Optional.empty();
		}
		Transaction transaction = newItem;
		transaction.setId(generateTransactionId());
		return super.create(transaction);
		
	}
	@Override
	public Collection<Transaction> findByCardId(Integer cardId) {
		return getAll().stream()
				.filter(transaction -> transaction.getCardId().equals(cardId))
				.collect(Collectors.toList());
	}
	

	@Override
	public Collection<Transaction> findByAccountId(Integer accountId) {
		return getAll().stream()
				.filter(transaction -> transaction.getAccountId().equals(accountId))
				.collect(Collectors.toList());
	}

	
}
