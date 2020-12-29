package rumos.banco.service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import rumos.banco.model.Card;
import rumos.banco.model.CreditCard;
import rumos.banco.model.DebitCard;

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

	private static ArrayList<DebitCard> debitCards = new ArrayList<>();
	private static ArrayList<CreditCard> creditCards = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);
	private static Integer id = 0;

	/******************************************************************************
	 * Save Cards
	 * 
	 * 
	 ******************************************************************************/
	public DebitCard save(DebitCard debitCard) {
		id++;
		debitCard.setCustomerId(id);
		debitCards.add(debitCard);

		return debitCard;
	}
	public CreditCard save(CreditCard creditCard) {
		id++;
		creditCard.setCustomerId(id);
		creditCards.add(creditCard);

		return creditCard;
	}
	/******************************************************************************
	 * Populate the Cards
	 * 
	 * 
	 ******************************************************************************/
	public DebitCard populateDebitCard() {	
		DebitCard newDebitCard = new DebitCard();
		CreditCard newCreditCard = new CreditCard();
		String cardNumber;
		String pinCard;
		
		

		System.out.println("Do you which to have a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			System.out.println("Please indicate the debit card number");
			cardNumber = scanner.next();
			newDebitCard.setDebitCardNumber(cardNumber);
			System.out.println("Please indicate the pin of the debit card number");
			pinCard = scanner.next();
			newDebitCard.setDebitCardPin(pinCard);
		}

		return newDebitCard;
	}
	public CreditCard populateCreditCard() {	
		CreditCard newCreditCard = new CreditCard();
		String cardNumber;
		String pinCard;
	

		System.out.println("Do you which to have a Credit Card? y/n");
		if (scanner.next().equals("y")) {
			System.out.println("Please indicate the credit card number");
			cardNumber = scanner.next();
			newCreditCard.setCreditCardNumber(cardNumber);
			System.out.println("Please indicate the pin of the credit card number");
			pinCard = scanner.next();
			newCreditCard.setCreditCardPin(pinCard);
		}

		return newCreditCard;
	}
	
		

	/******************************************************************************
	 * Delete the Card
	 * 
	 * 
	 ******************************************************************************/

	public void deleteDebitCard(Integer customerId) {
		for (Card card : debitCards) {
			if (card.getCustomerId().equals(customerId)) {
				System.out.println("Cards deleted");
				debitCards.remove(card);
				return;
			}
		}
		System.err.println("Card not found");
		return;
	}
	public void deleteCreditCard(Integer customerId) {
		for (Card card : creditCards) {
			if (card.getCustomerId().equals(customerId)) {
				System.out.println("Cards deleted");
				creditCards.remove(card);
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

	public void deleteDebitCard(DebitCard debitCard) {
		System.out.println("Do you whish to delete a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (!debitCard.getDebitCardNumber().equals(null)) {
				debitCard.setDebitCardNumber(null);
				debitCard.setDebitCardPin(null);
				deleteDebitCardDetails(debitCard);
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

	public void deleteCreditCard(CreditCard creditCard) {
		System.out.println("Do you whish to delete a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (!creditCard.getCreditCardNumber().equals(null)) {
				creditCard.setCreditCardNumber(null);
				creditCard.setCreditCardPin(null);
				deleteCreditCardDetails(creditCard);
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

	public void createDebitCard(DebitCard debitCard) {
		System.out.println("Do you whish to create a Debit Card? y/n");
		if (scanner.next().equals("y")) {
			if (debitCard.getDebitCardNumber().isBlank()) {
				createDebitCardDetails(debitCard);
				return;
			} else {
				System.err.println(NO_ADD_DEBIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	public void createCreditCard(CreditCard creditCard) {
		System.out.println("Do you whish to create a Credit Card? y/n");
		if (scanner.next().equals("y")) {
			if (creditCard.getCreditCardNumber().isBlank()) {
				createCreditCardDetails(creditCard);
				return;
			} else {
				System.err.println(NO_ADD_CREDIT_CARD);
				return;
			}
		}
		System.out.println(PREVIOUS_MENU);
		return;
	}

	public void createDebitCardDetails(DebitCard debitCard) {
		System.out.println("Creating the details of the debit card");
		System.out.println("Please indicate the number of the debit card");
		String cardNumber = scanner.next();
		debitCard.setDebitCardNumber(cardNumber);
		System.out.println("Please indicate the pin number of the debit card");
		String pinCard = scanner.next();
		debitCard.setDebitCardPin(pinCard);
	}

	public void createCreditCardDetails(CreditCard creditCard) {
		System.out.println("Creating the details of the credit card");
		System.out.println("Please indicate the number of the credit card");
		String cardNumber = scanner.next();
		creditCard.setCreditCardNumber(cardNumber);
		System.out.println("Please indicate the pin number of the credit card");
		String pinCard = scanner.next();
		creditCard.setCreditCardPin(pinCard);
	}

	public void deleteDebitCardDetails(DebitCard debitCard) {
		System.out.println("Deleting the details of the debit card");
		debitCard.setDebitCardNumber(null);
		System.out.println("Debit card number deleted");
		debitCard.setDebitCardPin(null);
		System.out.println("Debit card pin number deleted");
	}

	public void deleteCreditCardDetails(CreditCard creditCard) {
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
	public Card checkCardNumberAndPassword() {
		Card card = new Card();
		String choose;

		do {
			System.out.println("Please choose what card are you going to use to enter, debit or credit card ");
			choose = scanner.next().toLowerCase();
			if (choose.equals("debit")) {
				card = findDebitCard();
				return card;
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

	public void showDebitCardsDetails() {
		for (DebitCard card : debitCards) {
			System.out.println("The DebitCards: ");
			System.out.println(card.toString());
		}
	}
	public void showCreditCardsDetails() {
		for (CreditCard card : creditCards) {
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
	public DebitCard findDebitCard() {
		String cardNumber;
		String cardPin;

		System.out.println("Please indicate the debit card number");
		cardNumber = scanner.next();
		System.out.println("Please indicate the pin");
		cardPin = scanner.next();

		for (DebitCard debitCard : debitCards) {
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

	public CreditCard findCreditCard() {
		String cardNumber;
		String cardPin;

		System.out.println("Please indicate the credit card number");
		cardNumber = scanner.next();
		System.out.println("Please indicate the pin");
		cardPin = scanner.next();

		for (CreditCard creditCard : creditCards) {
			if (creditCard.getCreditCardNumber() != null && creditCard.getCreditCardPin() != null) {
				if (creditCard.getCreditCardNumber().equals(cardNumber) && creditCard.getCreditCardPin().equals(cardPin)) {
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

	public DebitCard findCustomerDebitCard(Integer customerId) {

		for (DebitCard card : debitCards) {
			if (card.getCustomerId().equals(customerId)) {
				System.out.println("Card found");
				return card;
			}
		}
		System.out.println("Customer do not have a card");
		return null;

	}
	public CreditCard findCustomerCreditCard(Integer customerId) {

		for (CreditCard card : creditCards) {
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
