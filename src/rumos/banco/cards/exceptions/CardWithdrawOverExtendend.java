package rumos.banco.cards.exceptions;

public class CardWithdrawOverExtendend extends CardException{
	public CardWithdrawOverExtendend(String message, Object ...args) {
		super(String.format(message, args));
	}
}
