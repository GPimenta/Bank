package rumos.banco.cards.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.DebitCard;

public class InMemDebitCardRepositoryImpl implements IDebitCardRepository {

	private static ArrayList<DebitCard> debitCards = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public Optional<DebitCard> getById(Integer id) {
		for (DebitCard debitCard : debitCards) {
			if (debitCard.getId().equals(id)) {
				return Optional.of(debitCard);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<DebitCard> getAll() {
		ArrayList<DebitCard> result = new ArrayList<DebitCard>();
		for (DebitCard debitCard : debitCards) {
			result.add(debitCard);
		}
		return result;
	}

	@Override
	public void create(DebitCard card) {
		card.setId(++id);
		card.setCustomerId(id);
		debitCards.add(card);

	}

	@Override
	public void deleteById(Integer id) {
		for (DebitCard debitCard : debitCards) {
			if (debitCard.getId().equals(id)) {
				debitCards.remove(debitCard);
				return;
			}
		}
	}
	
	@Override
	public void deleteByCustomerId(Integer customerId) {
		for(DebitCard debitCard : debitCards) {
			if(debitCard.getCustomerId().equals(customerId)) {
				debitCards.remove(debitCard);
				return;
			}
		}
	}

	@Override
	public void update(DebitCard card) {
		card.setId(++id);
		debitCards.add(card);

	}

}
