package rumos.banco.cards.service.old;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import rumos.banco.cards.model.old.OldCreditCard;
import rumos.banco.cards.repository.old.OldICreditCardRepository;

public class OldCreditCardService {

	private static final String NO_ADD_DEBIT_CARD = "You already have a Debit Card";
	private static final String NO_ADD_CREDIT_CARD = "You already have a Credit Card";
	private static final String NO_TAKE_DEBIT_CARD = "You do not have a Debit Card";
	private static final String NO_TAKE_CREDIT_CARD = "You do not have a Credit Card";
	private static final String PREVIOUS_MENU = "Returning to previous Menu";
	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";

	private OldICreditCardRepository repository;

	/******************************************************************************
	 * Constructor
	 * 
	 * 
	 ******************************************************************************/
	public OldCreditCardService(OldICreditCardRepository repository) {
		this.repository = repository;
	}

	/******************************************************************************
	 * Save Cards
	 * 
	 * 
	 ******************************************************************************/
	public void create(OldCreditCard card) {
		if (card == null) {
			throw new IllegalArgumentException("Failed to create CreditCard - Invalid creditCard Object");
		}
		repository.create(card);
	}

	/******************************************************************************
	 * Delete the Card
	 * 
	 * 
	 ******************************************************************************/

	public void deleteCreditCard(Integer customerId) {
		for (OldCreditCard card : repository.getAll()) {
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
	 * Create the Cards
	 * 
	 * 
	 ******************************************************************************/
	public void createCreditCard(OldCreditCard creditCard, String cardNumber, String pinCard, Integer customerId) {

		if (creditCard == null) {
			creditCard = new OldCreditCard();
			createCreditCardDetails(creditCard, cardNumber, pinCard, customerId);
			return;
		} else {
			System.err.println(NO_ADD_CREDIT_CARD);
			return;
		}
	}

	public void createCreditCardDetails(OldCreditCard creditCard, String cardNumber, String pinCard, Integer customerId) {
		creditCard.setCreditCardNumber(cardNumber);
		creditCard.setCreditCardPin(pinCard);
		creditCard.setCustomerId(customerId);
		repository.update(creditCard);
	}

	/******************************************************************************
	 * Delete the Cards
	 * 
	 * 
	 ******************************************************************************/

	public void deleteCreditCard(OldCreditCard creditCard) {

		if (creditCard != null) {
			repository.deleteByCustomerId(creditCard.getCustomerId());
			System.out.println("Credit card removed");
			return;
		} else {
			System.err.println(NO_TAKE_CREDIT_CARD);
			return;
		}
	}

	public void deleteCreditCardDetails(OldCreditCard creditCard) {
		System.out.println("Deleting the details of the credit card");
		creditCard.setCreditCardNumber(null);
		System.out.println("Credit card number deleted");
		creditCard.setCreditCardPin(null);
		System.out.println("Credit card pin number deleted");
	}

	/******************************************************************************
	 * check if the cardnumber and password are correct
	 * 
	 * @return
	 ******************************************************************************/
	public OldCreditCard checkCardNumberAndPassword(String cardNumber, String cardPin) {
		OldCreditCard card = new OldCreditCard();

		card = findCreditCard(cardNumber, cardPin);
		return card;
	}

	/******************************************************************************
	 * Show cards
	 * 
	 * 
	 *
	 ******************************************************************************/

	public void showCreditCardsDetails() {
		for (OldCreditCard card : repository.getAll()) {
			System.out.println("The CreditCards: ");
			System.out.println(card.toString());
		}
	}

	/******************************************************************************
	 * Find cards
	 * 
	 * 
	 *
	 ******************************************************************************/

	public OldCreditCard findCreditCard(String cardNumber, String cardPin) {

		for (OldCreditCard creditCard : repository.getAll()) {
			if (creditCard.getCreditCardNumber() != null && creditCard.getCreditCardPin() != null) {
				if (creditCard.getCreditCardNumber().equals(cardNumber)
						&& creditCard.getCreditCardPin().equals(cardPin)) {
					if (creditCard.getUsedCredit().equals(false)) {
						creditCard.setUsedCredit(true);
						creditCard.setCreditCardPin(generateRandomPinCard(creditCard.getCustomerId()));
						System.out.println(creditCard.toString());
						return creditCard;
					}
					System.out.println(creditCard.toString());
					return creditCard;
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

	public OldCreditCard findCustomerCreditCard(Integer customerId) {

		for (OldCreditCard card : repository.getAll()) {
			if (card.getCreditCardNumber() == null) {
				continue;
			}
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
