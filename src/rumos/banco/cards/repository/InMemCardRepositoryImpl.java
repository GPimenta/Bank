package rumos.banco.cards.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.Card;
import rumos.banco.cards.model.CreditCard;
import rumos.banco.cards.model.DebitCard;
import rumos.banco.common.repository.InMemRepository;

public class InMemCardRepositoryImpl extends InMemRepository<Card> implements ICardRepository{

	@Override
	public Collection<Card> findByAccountIdAndCustomerId(int accountId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Card> findByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Card> findByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CreditCard> getCreditCardByCustomerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DebitCard> getDebitCardByCustomerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
