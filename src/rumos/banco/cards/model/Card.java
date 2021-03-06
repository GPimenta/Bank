package rumos.banco.cards.model;

public class Card {
	
	private final Integer id;
	private final Integer customerId;
	private final Integer accountId;
	private final String cardNumber;
	private String pin;
	
	public Card(Integer id, Integer customerId, Integer accountId, String cardNumber, String pin) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.pin = pin;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
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
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", customerId=" + customerId + ", accountId=" + accountId + ", cardNumber="
				+ cardNumber + ", pin=" + pin + "]";
	}
	
	
	
	
}
