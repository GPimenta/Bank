package rumos.banco.cards.service;

import java.util.Random;

import rumos.banco.cards.model.old.OldDebitCard;
import rumos.banco.cards.repository.old.OldIDebitCardRepository;

public class OldDebitCardService {

	private static final String NO_ADD_DEBIT_CARD = "You already have a Debit Card";
	private static final String NO_TAKE_DEBIT_CARD = "You do not have a Debit Card";
	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";

	private OldIDebitCardRepository repository;

	/******************************************************************************
	 * Constructor
	 * 
	 * 
	 ******************************************************************************/
	public OldDebitCardService(OldIDebitCardRepository repository) {
		this.repository = repository;

	}

	/******************************************************************************
	 * Save Cards
	 * 
	 * 
	 ******************************************************************************/
	public void create(OldDebitCard card) {
		if (card == null) {
			throw new IllegalArgumentException("Failed to create DebitCard - Invalid debitCard object");
		}
//		if(card.getDebitCardNumber().equals(null)) {
//			return;
//			
//		}
		repository.create(card);
	}

	/******************************************************************************
	 * Delete the Card at the beginning of the program - for fast and testing
	 * 
	 * 
	 ******************************************************************************/

	public void deleteDebitCardTesting(Integer customerId) {
		for (OldDebitCard card : repository.getAll()) {
			if (card.getCustomerId().equals(customerId)) {
				System.out.println("Cards deleted");
				repository.deleteByCustomerId(customerId);
				return;
			}
		}
		System.err.println("Card not found");
		return;
	}

	/******************************************************************************
	 * Create DebitCard
	 * 
	 * 
	 ******************************************************************************/
	public void createDebitCard(OldDebitCard debitCard, String cardNumber, String pinCard, Integer customerId) {

		if (debitCard == null) {
			debitCard = new OldDebitCard();
			createDebitCardDetails(debitCard, cardNumber, pinCard, customerId);
			return;
		} else {
			System.err.println(NO_ADD_DEBIT_CARD);
			return;
		}
	}
	
	public void createDebitCardDetails(OldDebitCard debitCard, String cardNumber, String pinCard, Integer customerId) {

		debitCard.setDebitCardNumber(cardNumber);
		debitCard.setDebitCardPin(pinCard);
		debitCard.setCustomerId(customerId);
		repository.update(debitCard);
	}

	/******************************************************************************
	 * Delete DebitCard
	 * 
	 * 
	 ******************************************************************************/
	public void deleteDebitCard(OldDebitCard debitCard) {

		if (debitCard != null) {
			repository.deleteByCustomerId(debitCard.getCustomerId());
			System.out.println("Debit card removed");
			return;
		} else {
			System.err.println(NO_TAKE_DEBIT_CARD);
			return;
		}
	}

	public void deleteDebitCardDetails(OldDebitCard debitCard) {
		System.out.println("Deleting the details of the debit card");
		debitCard.setDebitCardNumber(null);
		System.out.println("Debit card number deleted");
		debitCard.setDebitCardPin(null);
		System.out.println("Debit card pin number deleted");
	}

	/******************************************************************************
	 * check if the cardnumber and password are correct
	 * 
	 * @return
	 ******************************************************************************/
	public OldDebitCard checkCardNumberAndPassword(String cardNumber, String cardPin) {
		OldDebitCard card = new OldDebitCard();
		card = findDebitCard(cardNumber, cardPin);
		return card;
	}

	/******************************************************************************
	 * Show cards
	 * 
	 * 
	 *
	 ******************************************************************************/

	public void showDebitCardsDetails() {
		for (OldDebitCard card : repository.getAll()) {
			System.out.println("The DebitCards: ");
			System.out.println(card.toString());
		}
	}

	/******************************************************************************
	 * Find cards
	 * 
	 * 
	 *
	 ******************************************************************************/
	public OldDebitCard findDebitCard(String cardNumber, String cardPin) {

		for (OldDebitCard debitCard : repository.getAll()) {
			if (debitCard.getDebitCardNumber() != null && debitCard.getDebitCardPin() != null) {
				if (debitCard.getDebitCardNumber().equals(cardNumber) && debitCard.getDebitCardPin().equals(cardPin)) {
					if (debitCard.getUsedDebit().equals(false)) {
						debitCard.setUsedDebit(true);
						debitCard.setDebitCardPin(generateRandomPinCard(debitCard.getCustomerId()));
						System.out.println(debitCard.toString());
						return debitCard;
					}
					System.out.println(debitCard.toString());
					return debitCard;
				}
			}
		}
		System.out.println(INVALID_NAME_OR_PASSWORD);
		return null;
	}

	/******************************************************************************
	 * Return the card from customer
	 * 
	 * 
	 *
	 ******************************************************************************/

	public OldDebitCard findCustomerDebitCard(Integer customerId) {

		for (OldDebitCard card : repository.getAll()) {
			if (card.getCustomerId().equals(customerId)) {
				if (card.getDebitCardNumber() == null) {
					System.out.println("Customer do not have a card");
					return null;
				}
				System.out.println("Card found");
				return card;
			}
		}
		System.out.println("Customer do not have a card");
		return null;

	}

	/******************************************************************************
	 * Generate random pin for the customer card
	 * 
	 * 
	 *
	 ******************************************************************************/
	public String generateRandomPinCard(Integer customerId) {
//		Card card;
//		card = findCustomerCard(customerId);

		String allNumbers = "0123456789";
		StringBuilder newPin = new StringBuilder();
		Random rnd = new Random();
		while (newPin.length() < 4) {
			int index = (int) (rnd.nextFloat() * allNumbers.length());
			newPin.append(allNumbers.charAt(index));
		}
		System.out.println(newPin.toString());
		return newPin.toString();

	}

}
