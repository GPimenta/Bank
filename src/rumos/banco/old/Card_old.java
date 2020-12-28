package rumos.banco.old;

public class Card_old {

	private Integer id;
	private Integer customerId;

	private Boolean debitCard = false;
	private String debitCardNumber;
	private String debitCardPin;
	private Boolean usedDebit = false;

	private Boolean creditCard = false;
	private String creditCardNumber;
	private String creditCardPin;
	private Boolean usedCredit = false;



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((creditCardPin == null) ? 0 : creditCardPin.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((debitCard == null) ? 0 : debitCard.hashCode());
		result = prime * result + ((debitCardNumber == null) ? 0 : debitCardNumber.hashCode());
		result = prime * result + ((debitCardPin == null) ? 0 : debitCardPin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usedCredit == null) ? 0 : usedCredit.hashCode());
		result = prime * result + ((usedDebit == null) ? 0 : usedDebit.hashCode());
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
		Card_old other = (Card_old) obj;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
		if (creditCardNumber == null) {
			if (other.creditCardNumber != null)
				return false;
		} else if (!creditCardNumber.equals(other.creditCardNumber))
			return false;
		if (creditCardPin == null) {
			if (other.creditCardPin != null)
				return false;
		} else if (!creditCardPin.equals(other.creditCardPin))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (debitCard == null) {
			if (other.debitCard != null)
				return false;
		} else if (!debitCard.equals(other.debitCard))
			return false;
		if (debitCardNumber == null) {
			if (other.debitCardNumber != null)
				return false;
		} else if (!debitCardNumber.equals(other.debitCardNumber))
			return false;
		if (debitCardPin == null) {
			if (other.debitCardPin != null)
				return false;
		} else if (!debitCardPin.equals(other.debitCardPin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usedCredit == null) {
			if (other.usedCredit != null)
				return false;
		} else if (!usedCredit.equals(other.usedCredit))
			return false;
		if (usedDebit == null) {
			if (other.usedDebit != null)
				return false;
		} else if (!usedDebit.equals(other.usedDebit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [customerId=" + customerId + ", debitCard=" + debitCard + ", debitCardNumber=" + debitCardNumber
				+ ", debitCardPin=" + debitCardPin + ", creditCard=" + creditCard + ", creditCardNumber="
				+ creditCardNumber + ", creditCardPin=" + creditCardPin + "]";
	}
	
	
	
	

	public Boolean isUsedDebit() {
		return usedDebit;
	}

	public void setUsedDebit(Boolean usedDebit) {
		this.usedDebit = usedDebit;
	}

	public Boolean isUsedCredit() {
		return usedCredit;
	}

	public void setUsedCredit(Boolean usedCredit) {
		this.usedCredit = usedCredit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Boolean isDebitCard() {
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

	public Boolean isCreditCard() {
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
