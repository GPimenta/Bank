package rumos.banco.cards.repository.old;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.old.OldDebitCard;

public class OldInMemDebitCardRepositoryImpl implements OldIDebitCardRepository {

	private static ArrayList<OldDebitCard> debitCards = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public Optional<OldDebitCard> getById(Integer id) {
		for (OldDebitCard debitCard : debitCards) {
			if (debitCard.getId().equals(id)) {
				return Optional.of(debitCard);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<OldDebitCard> getAll() {
		ArrayList<OldDebitCard> result = new ArrayList<OldDebitCard>();
		for (OldDebitCard debitCard : debitCards) {
			result.add(debitCard);
		}
		return result;
	}

	@Override
	public void create(OldDebitCard card) {
		card.setId(++id);
		card.setCustomerId(id);
		debitCards.add(card);

	}

	@Override
	public void deleteById(Integer id) {
		for (OldDebitCard debitCard : debitCards) {
			if (debitCard.getId().equals(id)) {
				debitCards.remove(debitCard);
				return;
			}
		}
	}
	
	@Override
	public void deleteByCustomerId(Integer customerId) {
		for(OldDebitCard debitCard : debitCards) {
			if(debitCard.getCustomerId().equals(customerId)) {
				debitCards.remove(debitCard);
				return;
			}
		}
	}

	@Override
	public void update(OldDebitCard card) {
		card.setId(++id);
		debitCards.add(card);

	}

}
