package rumos.banco.model;

public class Card {
	
	private Integer customerId;
	private String passwordCard;
	
	
	private Boolean debitCard = false;
	private String debitCardNumber;
	private String debitCardPin;

	private Boolean creditCard = false;
	private String creditCardNumber;
	private String creditCardPin;
	
	
	
	
	public Boolean getDebitCard() {
		return debitCard;
	}
	public void setDebitCard(Boolean debitCard) {
		this.debitCard = debitCard;
	}
	public String getDebitCardNumber() {
		return debitCardNumber;
	}
	public void setDebitCardNumber(String debitCardNumber) {
		this.debitCardNumber = debitCardNumber;
	}
	public String getDebitCardPin() {
		return debitCardPin;
	}
	public void setDebitCardPin(String debitCardPin) {
		this.debitCardPin = debitCardPin;
	}
	public Boolean getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(Boolean creditCard) {
		this.creditCard = creditCard;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCreditCardPin() {
		return creditCardPin;
	}
	public void setCreditCardPin(String creditCardPin) {
		this.creditCardPin = creditCardPin;
	}
	
	
}
