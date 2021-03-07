package rumos.banco.cards.repository.old;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.old.OldDebitCard;

public interface OldIDebitCardRepository {
	Optional<OldDebitCard> getById(Integer id);

	Collection<OldDebitCard> getAll();

	void create(OldDebitCard debitCard);

	void deleteById(Integer id);
	
	void deleteByCustomerId(Integer customerId);

	void update(OldDebitCard debitCard);

}
