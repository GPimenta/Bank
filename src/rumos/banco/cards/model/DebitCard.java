package rumos.banco.cards.model;

public class DebitCard extends Card {

	private String debitCardNumber;
	private String debitCardPin;
	private Boolean usedDebit = false;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((debitCardNumber == null) ? 0 : debitCardNumber.hashCode());
		result = prime * result + ((debitCardPin == null) ? 0 : debitCardPin.hashCode());
		result = prime * result + ((usedDebit == null) ? 0 : usedDebit.hashCode());
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
		DebitCard other = (DebitCard) obj;
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
		if (usedDebit == null) {
			if (other.usedDebit != null)
				return false;
		} else if (!usedDebit.equals(other.usedDebit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DebitCard [customerId = "+ getCustomerId() + " debitCardNumber=" + debitCardNumber + ", debitCardPin=" + debitCardPin + ", usedDebit="
				+ usedDebit + "]";
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

	public Boolean getUsedDebit() {
		return usedDebit;
	}

	public void setUsedDebit(Boolean usedDebit) {
		this.usedDebit = usedDebit;
	}

}
