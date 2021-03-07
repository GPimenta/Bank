package rumos.banco.cards.repository.old;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.old.OldCreditCard;

public class OldInMemCreditCardRepositoryImpl implements OldICreditCardRepository {

	private static ArrayList<OldCreditCard> creditCards = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public Optional<OldCreditCard> getById(Integer id) {
		for (OldCreditCard creditCards : creditCards) {
			if (creditCards.getId().equals(id)) {
				return Optional.of(creditCards);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<OldCreditCard> getAll() {
		ArrayList<OldCreditCard> result = new ArrayList<OldCreditCard>();
		for (OldCreditCard creditCard : creditCards) {
			result.add(creditCard);
		}
		return result;
	}

	@Override
	public void create(OldCreditCard creditCard) {
		creditCard.setId(++id);
		creditCard.setCustomerId(id);
		creditCards.add(creditCard);

	}

	@Override
	public void deleteById(Integer id) {
		for (OldCreditCard creditCard : creditCards) {
			if (creditCard.getId().equals(id)) {
				creditCards.remove(creditCard);
				return;
			}
		}
	}
	
	@Override
	public void deleteByCustomerId(Integer customerId) {
		for(OldCreditCard creditCard : creditCards) {
			if(creditCard.getCustomerId().equals(customerId)) {
				creditCards.remove(creditCard);
				return;
			}
		}
	}

	@Override
	public void update(OldCreditCard creditCard) {
		creditCard.setId(++id);
		creditCards.add(creditCard);

	}

}
