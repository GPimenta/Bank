package rumos.banco.model;

public class CreditCard extends Card {

	private String creditCardNumber;
	private String creditCardPin;
	private Boolean checkCashAdvanceEligability = true;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((checkCashAdvanceEligability == null) ? 0 : checkCashAdvanceEligability.hashCode());
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((creditCardPin == null) ? 0 : creditCardPin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (checkCashAdvanceEligability == null) {
			if (other.checkCashAdvanceEligability != null)
				return false;
		} else if (!checkCashAdvanceEligability.equals(other.checkCashAdvanceEligability))
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
		return true;
	}

	@Override
	public String toString() {
		return "CreditCard [creditCardNumber=" + creditCardNumber + ", creditCardPin=" + creditCardPin
				+ ", checkCashAdvanceEligability=" + checkCashAdvanceEligability + "]";
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

	public Boolean getCheckCashAdvanceEligability() {
		return checkCashAdvanceEligability;
	}

	public void setCheckCashAdvanceEligability(Boolean checkCashAdvanceEligability) {
		this.checkCashAdvanceEligability = checkCashAdvanceEligability;
	}

}