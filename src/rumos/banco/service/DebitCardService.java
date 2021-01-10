package rumos.banco.service;

import java.util.Random;

import rumos.banco.model.DebitCard;
import rumos.banco.repository.IDebitCardRepository;

public class DebitCardService {

	private static final String NO_ADD_DEBIT_CARD = "You already have a Debit Card";
	private static final String NO_TAKE_DEBIT_CARD = "You do not have a Debit Card";
	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";

	private IDebitCardRepository repository;

	/******************************************************************************
	 * Constructor
	 * 
	 * 
	 ******************************************************************************/
	public DebitCardService(IDebitCardRepository repository) {
		this.repository = repository;

	}

	/******************************************************************************
	 * Save Cards
	 * 
	 * 
	 ******************************************************************************/
	public void create(DebitCard card) {
		if (card == null) {
			throw new IllegalArgumentException("Failed to create DebitCard - Invalid debitCard object");
		}
		repository.create(card);
	}

	/******************************************************************************
	 * Delete the Card at the beginning of the program - for fast and testing
	 * 
	 * 
	 ******************************************************************************/

	public void deleteDebitCardTesting(Integer customerId) {
		for (DebitCard card : repository.getAll()) {
			if (card.getCustomerId().equals(customerId)) {
				System.out.println("Cards deleted");
				repository.deleteById(customerId);
				return;
			}
		}
		System.err.println("Card not found");
		return;
	}

	/******************************************************************************
	 * Delete an existing card or create an unexisting Cards
	 * 
	 * 
	 ******************************************************************************/
	public void createDebitCard(DebitCard debitCard, String cardNumber, String pinCard) {

		if (debitCard.getDebitCardNumber() == null) {
			createDebitCardDetails(debitCard, cardNumber, pinCard);
			return;
		} else {
			System.err.println(NO_ADD_DEBIT_CARD);
			return;
		}
	}

	public void deleteDebitCard(DebitCard debitCard) {

		if (!debitCard.getDebitCardNumber().equals(null)) {
			repository.deleteById(debitCard.getCustomerId());
			System.out.println("Debit card removed");
			return;
		} else {
			System.err.println(NO_TAKE_DEBIT_CARD);
			return;
		}
	}

	public void createDebitCardDetails(DebitCard debitCard, String cardNumber, String pinCard) {

		debitCard.setDebitCardNumber(cardNumber);
		debitCard.setDebitCardPin(pinCard);
		repository.update(debitCard);
	}

	public void deleteDebitCardDetails(DebitCard debitCard) {
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
	public DebitCard checkCardNumberAndPassword(String cardNumber, String cardPin) {
		DebitCard card = new DebitCard();
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
		for (DebitCard card : repository.getAll()) {
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
	public DebitCard findDebitCard(String cardNumber, String cardPin) {

		for (DebitCard debitCard : repository.getAll()) {
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

	public DebitCard findCustomerDebitCard(Integer customerId) {

		for (DebitCard card : repository.getAll()) {
			if(card.equals(null)) continue;
			if (card.getCustomerId().equals(customerId)) {
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
