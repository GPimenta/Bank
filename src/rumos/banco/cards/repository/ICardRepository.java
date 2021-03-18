package rumos.banco.cards.repository;

import java.util.Collection;
import java.util.Optional;

import rumos.banco.cards.model.Card;
import rumos.banco.cards.model.CreditCard;
import rumos.banco.cards.model.DebitCard;
import rumos.banco.common.repository.IRepository;

public interface ICardRepository extends IRepository<Card>{
	Collection<Card> findByAccountIdAndCustomerId(int accountId, int customerId);
	
	Optional<Card> findByCardNumber(String cardNumber);
	
	Collection<Card> findByCustomerId(int customerId);
	
	Optional<CreditCard> getCreditCardByCustomerId(int id);
	
	Optional<DebitCard> getDebitCardByCustomerId(int id);

}
