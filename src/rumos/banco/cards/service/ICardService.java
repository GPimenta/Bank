package rumos.banco.cards.service;

import java.util.Collection;

import rumos.banco.cards.model.Card;
import rumos.banco.cards.model.CreditCard;
import rumos.banco.cards.model.DebitCard;

public interface ICardService {
	
	DebitCard createDebitCard(Integer accountId, Integer customerId);
	
	CreditCard createCreditCard(Integer accountId, Integer customerId);
	
	void deleteCard(Integer cardId);
	
	boolean checkCardNumberAndPassword(String cardNumber, String password);
	
	Collection<Card> findAllCardsByCustomerId(Integer customerId);
	
	Collection<DebitCard> findAllDebitCardsByCustomerId(Integer customerId);
	
	DebitCard getDebitCardByCardId(String cardId);
	
	CreditCard getCreditCardByCardId(String cardId);
	
	Card getCardById(Integer cardId);
	
}
