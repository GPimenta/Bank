package rumos.banco.cards.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.OldCreditCard;

public interface OldICreditCardRepository {
	Optional<OldCreditCard> getById(Integer id);

	Collection<OldCreditCard> getAll();

	void create(OldCreditCard card);

	void deleteById(Integer id);
	
	void deleteByCustomerId(Integer customerId);

	void update(OldCreditCard card);

}
