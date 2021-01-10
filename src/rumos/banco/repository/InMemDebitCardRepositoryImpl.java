package rumos.banco.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.Card;
import rumos.banco.model.DebitCard;

public class InMemDebitCardRepositoryImpl implements IDebitCardRepository {
	
	private static ArrayList<DebitCard> DebitCards = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public Optional<DebitCard> getById(Integer id) {
		for(DebitCard debitCard : DebitCards) {
			if(debitCard.getId().equals(id)) {
				return Optional.of(debitCard);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<DebitCard> getAll() {
		ArrayList<DebitCard> result = new ArrayList<DebitCard>();
		for (DebitCard debitCard : DebitCards) {
			result.add(debitCard);
		}
		return result;
	}

	@Override
	public void create(DebitCard card) {
//		card.setId(++id);
		card.setCustomerId(++id);
		DebitCards.add(card);
		
	}

	@Override
	public void deleteById(Integer id) {
		for(DebitCard debitCard : DebitCards) {
			if(debitCard.getId().equals(id)) {
				DebitCards.remove(debitCard);
			}
		}
		
	}

	@Override
	public void update(DebitCard card) {
		DebitCards.add(card);
		
	}

}
