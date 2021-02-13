package rumos.banco.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Card;
import rumos.banco.model.DebitCard;

public interface IDebitCardRepository {
	Optional<DebitCard> getById(Integer id);

	Collection<DebitCard> getAll();

	void create(DebitCard debitCard);

	void deleteById(Integer id);
	
	void deleteByCustomerId(Integer customerId);

	void update(DebitCard debitCard);

}
