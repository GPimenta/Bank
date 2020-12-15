package rumos.banco.service;

import java.util.ArrayList;
import java.util.Scanner;

import rumos.banco.model.Card;
import rumos.banco.model.Customer_Old;

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
	
	public Card save(Card card) {
		id++;
		card.setCustomerId(id);
		cards.add(card);
		
		return card;
	}
	
	
	/******************************************************************************
	 * Bank Cards
	 ******************************************************************************/
	public void editBankCards() {
		Integer option;
		Card card;

		card = checkCardNumberAndPassword();

		if (card == null) {
			return;
		}

		do {

			displayBankCardsMenu();

			option = scanner.nextInt();

			switch (option) {
			case CREATE_DEBIT_CARD:
				createDebitCard(card);
				// Add Debit card
				break;
			case DELETE_DEBIT_CARD:
				deleteDebitCard(card);
				// Remove Debit card
				break;
			case CREATE_CREDIT_CARD:
				createCreditCard(card);
				// Add Credit Card
				break;
			case DELETE_CREDIT_CARD:
				deleteCreditCard(card);
				// Remove Credit Card
				break;
			case EXIT:
				System.out.println(PREVIOUS_MENU);
				break;

			default:
				System.err.println(INVALID_OPTION + " in EditBankCards");
				break;
			}
		} while (option != 0);
	}

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
	 * check if the name and password are correct 
	 * 
	 * @return
	 ******************************************************************************/
	public Card checkCardNumberAndPassword() {
		Card card = new Card();
		String choose;
		System.out.println("Please choose what card are you going to use to enter, debit or credit card ");
		choose = scanner.next().toLowerCase();
		
		if(choose.equals("debit")) 
			card = findDebitCard();
		if(choose.equals("credit"))
			card = findCreditCard();
		
		return card;
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
		
		for(Card card : cards) {
			if(card.getDebitCardNumber().equals(cardNumber) && card.getDebitCardPin().equals(cardPin)) {
				System.out.println(card.toString());
				return card;	
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
		
		for(Card card : cards) {
			if(card.getCreditCardNumber().equals(cardNumber) && card.getCreditCardPin().equals(cardPin)) {
				System.out.println(card.toString());
				return card;
			}
		}
		System.out.println(INVALID_NAME_OR_PASSWORD);
		return null;
	}
	

	
	
	
	
	/******************************************************************************
	 * Display menu
	 * 
	 * 
	 *
	 ******************************************************************************/
	
	
	
	
	private static void displayBankCardsMenu() {
		System.out.println("############################ " + TITLE + " #############################");
		System.out.println("Please choose what action to take");
		System.out.println("0 - Return to previous Menu");
		System.out.println("1 - Create Debit Card");
		System.out.println("2 - Delete Debit Card");
		System.out.println("3 - Create Credit Card");
		System.out.println("4 - Delete Credit Card");
		System.out.println("###########################################################################");
	}
	
}
