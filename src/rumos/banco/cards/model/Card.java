package rumos.banco.cards.model;

import rumos.banco.utils.IPreconditions;

public class Card {
	
	private final Integer id;
	private final Integer customerId;
	private final Integer accountId;
	private final String cardNumber;
	private String pin;
	private Boolean used;
	private final boolean isCreditCard;
	
	public Card(Integer id, Integer customerId, Integer accountId, String cardNumber, String pin, Boolean used, Boolean isCreditCard) {
		this.id = id;
		this.customerId = customerId;
		this.accountId = accountId;
		this.cardNumber = IPreconditions.checkLength(cardNumber, 5, "Card number must have 5 digits");
		this.pin = IPreconditions.checkLength(pin, 4, "Card Pin must have 4 digits");
		this.used = used;
		this.isCreditCard = isCreditCard;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}
	
	public boolean isCreditCard() {
		return isCreditCard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isCreditCard ? 1231 : 1237);
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result + ((used == null) ? 0 : used.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isCreditCard != other.isCreditCard)
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (used == null) {
			if (other.used != null)
				return false;
		} else if (!used.equals(other.used))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", customerId=" + customerId + ", accountId=" + accountId + ", cardNumber="
				+ cardNumber + ", pin=" + pin + ", used=" + used + ", isCreditCard=" + isCreditCard + "]";
	}




	
	
	
}
