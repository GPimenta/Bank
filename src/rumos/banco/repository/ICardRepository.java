package rumos.banco.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Card;

public interface ICardRepository {
	Optional<Card> getById(Integer id);

	Collection<Card> getAll();

	void create(Card card);

	void deleteById(Integer id);

	void update(Card card);

}
