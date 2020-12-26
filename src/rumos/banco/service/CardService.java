package rumos.banco.service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import rumos.banco.model.Card;

public class CardService {

	private static final int EXIT = 0;

	private static final int CREATE_DEBIT_CARD = 1;
	private static final int DELETE_DEBIT_CARD = 2;
	private static final int CREATE_CREDIT_CARD = 3;
	private static final int DELETE_CREDIT_CARD = 4;

	private static final String TITLE = "Rumos Digital Bank";

	private static final String NO_ADD_DEBIT_CARD = "You already have a Debit Card";
	private static final String NO_ADD_CREDIT_CARD = "You already have a Credit Card";
	private static final String NO_TAKE_DEBIT_CARD = "You do not have a Debit Card";
	private static final String NO_TAKE_CREDIT_CARD = "You do not have a Credit Card";
	private static final String INVALID_OPTION = "Invalid Option!";
	private static final String PREVIOUS_MENU = "Returning to previous Menu";
	private static final String INVALID_NAME_OR_PASSWORD = "Incorrect Name or Password";

	private static ArrayList<Card> cards = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static Integer id = 0;

	/******************************************************************************
	 * Save Card
	 * 
	 * 
	 ******************************************************************************/
	public Card save(Card card) {
		id++;
		card.setCustomerId(id);
		cards.add(card);

		return card;
	}

	/******************************************************************************
	 * Populate the Cards
	 * 
	 * 
	 ******************************************************************************/
	public Card populateCard() {
		Card newCard = new Card();

		System.out.println("Do you which to have a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			newCard.setDebitCard(true);
			System.out.println("Please indicate the debit card number");
			String cardNumber = scanner.next();
			newCard.setDebitCardNumber(cardNumber);
			System.out.println("Please indicate the pin of the debit card number");
			String pinCard = scanner.next();
			newCard.setDebitCardPin(pinCard);
		} else
			newCard.setDebitCard(false);

		System.out.println("Do you which to have a Credit Card? y/n");
		if (scanner.next().equals("y")) {
			newCard.setCreditCard(true);
			System.out.println("Please indicate the credit card number");
			String cardNumber = scanner.next();
			newCard.setCreditCardNumber(cardNumber);
			System.out.println("Please indicate the pin of the credit card number");
			String pinCard = scanner.next();
			newCard.setCreditCardPin(pinCard);
		} else
			newCard.setCreditCard(false);

		return newCard;
	}

	/******************************************************************************
	 * Delete the Card
	 * 
	 * 
	 ******************************************************************************/

	public void deleteCard(Integer customerId) {
		for (Card card : cards) {
			if (card.getCustomerId().equals(customerId)) {
				System.out.println("Cards deleted");
				cards.remove(card);
				return;
			}
		}
		System.err.println("Card not found");
		return;
	}

	/******************************************************************************
	 * Delete and create the Cards
	 * 
	 * 
	 ******************************************************************************/

	public void deleteDebitCard(Card card) {
		System.out.println("Do you whish to delete a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (card.isDebitCard()) {
				card.setDebitCard(false);
				deleteDebitCardDetails(card);
				System.out.println("Debit card removed");
				return;
			} else {
				System.err.println(NO_TAKE_DEBIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	public void deleteCreditCard(Card card) {
		System.out.println("Do you whish to delete a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (card.isCreditCard()) {
				card.setCreditCard(false);
				deleteCreditCardDetails(card);
				System.out.println("Credit card removed");
				return;
			} else {
				System.err.println(NO_TAKE_CREDIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	public void createDebitCard(Card card) {
		System.out.println("Do you whish to create a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (!card.isDebitCard()) {
				card.setDebitCard(true);
				createDebitCardDetails(card);
				return;
			} else {
				System.err.println(NO_ADD_DEBIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	public void createCreditCard(Card card) {
		System.out.println("Do you whish to create a Credit Card? y/n");
		if (scanner.next().equals("y")) {
			if (!card.isCreditCard()) {
				card.setCreditCard(true);
				createCreditCardDetails(card);
				return;
			} else {
				System.err.println(NO_ADD_CREDIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	public void createDebitCardDetails(Card card) {
		System.out.println("Creating the details of the debit card");
		System.out.println("Please indicate the number of the debit card");
		String cardNumber = scanner.next();
		card.setDebitCardNumber(cardNumber);
		System.out.println("Please indicate the pin number of the debit card");
		String pinCard = scanner.next();
		card.setDebitCardPin(pinCard);
	}

	public void createCreditCardDetails(Card card) {
		System.out.println("Creating the details of the credit card");
		System.out.println("Please indicate the number of the credit card");
		String cardNumber = scanner.next();
		card.setCreditCardNumber(cardNumber);
		System.out.println("Please indicate the pin number of the credit card");
		String pinCard = scanner.next();
		card.setCreditCardPin(pinCard);
	}

	public void deleteDebitCardDetails(Card card) {
		System.out.println("Deleting the details of the debit card");
		card.setDebitCardNumber(null);
		System.out.println("Debit card number deleted");
		card.setDebitCardPin(null);
		System.out.println("Debit card pin number deleted");
	}

	public void deleteCreditCardDetails(Card card) {
		System.out.println("Deleting the details of the credit card");
		card.setCreditCardNumber(null);
		System.out.println("Credit card number deleted");
		card.setCreditCardPin(null);
		System.out.println("Credit card pin number deleted");
	}

	/******************************************************************************
	 * check if the cardnumber and password are correct
	 * 
	 * @return
	 ******************************************************************************/
	public Card checkCardNumberAndPassword() {
		Card card = new Card();
		String choose;

		do {
			System.out.println("Please choose what card are you going to use to enter, debit or credit card ");
			choose = scanner.next().toLowerCase();
			if (choose.equals("debit")) {
				card = findDebitCard();
			}
			if (choose.equals("credit")) {
				card = findCreditCard();
				return card;
			}
			System.err.println("Please write debit or credit");
		} while (!(choose.equals("debit") || choose.equals("credit")));
		return null;
	}

	/******************************************************************************
	 * Show cards
	 * 
	 * 
	 *
	 ******************************************************************************/

	public void showCardsDetails() {
		for (Card card : cards) {
			System.out.println("The Cards: ");
			System.out.println(card.toString());
		}
	}

	/******************************************************************************
	 * Find cards
	 * 
	 * 
	 *
	 ******************************************************************************/
	public Card findDebitCard() {
		String cardNumber;
		String cardPin;

		System.out.println("Please indicate the debit card number");
		cardNumber = scanner.next();
		System.out.println("Please indicate the pin");
		cardPin = scanner.next();

		for (Card card : cards) {
			if (card.getDebitCardNumber() != null && card.getDebitCardPin() != null) {
				if (card.getDebitCardNumber().equals(cardNumber) && card.getDebitCardPin().equals(cardPin)) {
					if (card.isUsedDebit().equals(false)) {
						card.setUsedDebit(true);
						card.setDebitCardPin(generateRandomPinCard(card.getCustomerId()));
						System.out.println(card.toString());
						return card;
					}
					System.out.println(card.toString());
					return card;
				}
			}
		}
		System.out.println(INVALID_NAME_OR_PASSWORD);
		return null;
	}

	public Card findCreditCard() {
		String cardNumber;
		String cardPin;

		System.out.println("Please indicate the credit card number");
		cardNumber = scanner.next();
		System.out.println("Please indicate the pin");
		cardPin = scanner.next();

		for (Card card : cards) {
			if (card.getCreditCardNumber() != null && card.getCreditCardPin() != null) {
				if (card.getCreditCardNumber().equals(cardNumber) && card.getCreditCardPin().equals(cardPin)) {
					if (card.isUsedCredit().equals(false)) {
						card.setUsedCredit(true);
						card.setCreditCardPin(generateRandomPinCard(card.getCustomerId()));
						System.out.println(card.toString());
						return card;
					}
					System.out.println(card.toString());
					return card;
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

	public Card findCustomerCard(Integer customerId) {

		for (Card card : cards) {
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
		Card card;
		card = findCustomerCard(customerId);

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
