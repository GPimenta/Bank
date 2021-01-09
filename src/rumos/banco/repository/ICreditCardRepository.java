package rumos.banco.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.CreditCard;

public interface ICreditCardRepository {
	Optional<CreditCard> getById(Integer id);

	Collection<CreditCard> getAll();

	void create(CreditCard card);

	void deleteById(Integer id);

	void update(CreditCard card);

}
