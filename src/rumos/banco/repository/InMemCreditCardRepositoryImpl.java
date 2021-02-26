package rumos.banco.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import rumos.banco.model.CreditCard;

public class InMemCreditCardRepositoryImpl implements ICreditCardRepository {

	private static ArrayList<CreditCard> creditCards = new ArrayList<>();
	private static Integer id = 0;

	@Override
	public Optional<CreditCard> getById(Integer id) {
		for (CreditCard creditCards : creditCards) {
			if (creditCards.getId().equals(id)) {
				return Optional.of(creditCards);
			}
		}
		return Optional.empty();
	}

	@Override
	public Collection<CreditCard> getAll() {
		ArrayList<CreditCard> result = new ArrayList<CreditCard>();
		for (CreditCard creditCard : creditCards) {
			result.add(creditCard);
		}
		return result;
	}

	@Override
	public void create(CreditCard creditCard) {
		creditCard.setId(++id);
		creditCard.setCustomerId(id);
		creditCards.add(creditCard);

	}

	@Override
	public void deleteById(Integer id) {
		for (CreditCard creditCard : creditCards) {
			if (creditCard.getId().equals(id)) {
				creditCards.remove(creditCard);
				return;
			}
		}
	}
	
	@Override
	public void deleteByCustomerId(Integer customerId) {
		for(CreditCard creditCard : creditCards) {
			if(creditCard.getCustomerId().equals(customerId)) {
				creditCards.remove(creditCard);
				return;
			}
		}
	}

	@Override
	public void update(CreditCard creditCard) {
		creditCard.setId(++id);
		creditCards.add(creditCard);

	}

}
