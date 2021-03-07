package rumos.banco.cards.model;

import java.math.BigInteger;

import rumos.banco.cards.model.DebitCard.Builder;
import rumos.banco.utils.IPreconditions;
import rumos.banco.utils.Preconditions;

public class CreditCard extends Card {
	public static final int ALLOWED_LIMIT_CASH_ADVANCE = 250;

	private Integer cashAdvance;

	public CreditCard(Integer id, Integer customerId, Integer accountId, String cardNumber, String pin,
			Integer cashAdvance, Boolean used) {
		super(id, customerId, accountId, cardNumber, pin, used);
		
		IPreconditions.checkArgument(cashAdvance < ALLOWED_LIMIT_CASH_ADVANCE, "The limit amount is: " + ALLOWED_LIMIT_CASH_ADVANCE);
		
		this.cashAdvance = IPreconditions.requireNonNullElse(cashAdvance, ALLOWED_LIMIT_CASH_ADVANCE);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cashAdvance == null) ? 0 : cashAdvance.hashCode());
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
		if (cashAdvance == null) {
			if (other.cashAdvance != null)
				return false;
		} else if (!cashAdvance.equals(other.cashAdvance))
			return false;
		return true;
	}

	public Integer getCashAdvance() {
		return cashAdvance;
	}

	public void setCashAdvance(Integer cashAdvance) {
		this.cashAdvance = cashAdvance;
	}

	public static class Builder {
		private Integer id;
		private Integer customerId;
		private Integer accountId;
		private String cardNumber;
		private String pin;
		private Integer cashAdvance;
		private Boolean used;

		private Builder() {

		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withCustomerId(Integer customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder withAccountId(Integer accountId) {
			this.accountId = accountId;
			return this;
		}

		public Builder withCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
			return this;
		}

		public Builder withPin(String pin) {
			this.pin = pin;
			return this;
		}

		public Builder withCashAdvance(Integer cashAdvance) {
			this.cashAdvance = cashAdvance;
			return this;
		}

		public Builder isUsed(Boolean used) {
			this.used = used;
			return this;
		}

		public CreditCard build() {
			return new CreditCard(id, customerId, accountId, cardNumber, pin, cashAdvance, used);
		}

	}

}
