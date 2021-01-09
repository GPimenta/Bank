package rumos.banco.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Card;

public class CreditCardRepositoryImpl implements ICreditCardRepository {
	
	private static ArrayList<Card> cards = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public Optional<Card> getById(Integer id) {
		for(Card card : cards) {
			if(card.getId().equals(id)) {
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<Card> getAll() {
		ArrayList<Card> result = new ArrayList<Card>();
		for (Card card : cards) {
			result.add(card);
		}
		return result;
	}

	@Override
	public void create(Card card) {
		card.setId(++id);
		cards.add(card);
		
	}

	@Override
	public void deleteById(Integer id) {
		for(Card card : cards) {
			if(card.getId().equals(id)) {
				cards.remove(card);
			}
		}
		
	}

	@Override
	public void update(Card card) {
		cards.add(card);
		
	}

}
