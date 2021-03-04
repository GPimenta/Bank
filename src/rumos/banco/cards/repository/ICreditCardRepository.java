package rumos.banco.cards.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.CreditCard;

public interface ICreditCardRepository {
	Optional<CreditCard> getById(Integer id);

	Collection<CreditCard> getAll();

	void create(CreditCard card);

	void deleteById(Integer id);
	
	void deleteByCustomerId(Integer customerId);

	void update(CreditCard card);

}
