package rumos.banco.accounts.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import rumos.banco.utils.IPreconditions;

public class Account {

	public static final int ACCOUNT_NUMBER_LENGTH = 5;
	public static final int MAX_NUMBER_SECONDARY_OWNERS = 4;
	public static final int ACCOUNT_PASSWORD_LENGTH = 4;

	private final Integer id;
	private final Integer customerId;
	private String password;
	private final String accountNumber; // 5 digits
	private Double balance;
	private List<Integer> secondaryOwnersId;

	public Account(Integer id, Integer customerId, String passwordAccount, String accountNumber, Double balance,
			List<Integer> secondaryOwnersId) {

		IPreconditions.checkArgument(balance >= 0.0, "Balance can not be less than 0");

		this.id = id;
		this.customerId = customerId;
		this.password = IPreconditions.checkLength(passwordAccount, ACCOUNT_PASSWORD_LENGTH,
				"The account password can not be less than 4 digits");
		this.accountNumber = IPreconditions.checkLength(accountNumber, ACCOUNT_NUMBER_LENGTH,
				"The account number can not be less than 5 digits");
		this.balance = balance;
		this.secondaryOwnersId = IPreconditions.requireNonNullElse(secondaryOwnersId, Collections.emptyList());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((secondaryOwnersId == null) ? 0 : secondaryOwnersId.hashCode());
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
		Account other = (Account) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (secondaryOwnersId == null) {
			if (other.secondaryOwnersId != null)
				return false;
		} else if (!secondaryOwnersId.equals(other.secondaryOwnersId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", password=" + password + ", accountNumber="
				+ accountNumber + ", balance=" + balance + ", secondaryOwnersId=" + secondaryOwnersId + "]";
	}

	public static class Builder {

		private Integer id;
		private Integer customerId;
		private String password;
		private String accountNumber;
		private Double balance;
		private List<Integer> secondaryOwnersId;

		public Builder() {

		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withCustomerId(Integer customerId) {
			this.customerId = customerId;
			return this;
		}

		public Builder withPasswordAccount(String password) {
			this.password = password;
			return this;
		}

		public Builder withAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder withBalance(Double balance) {
			this.balance = balance;
			return this;
		}

		public Builder withSecondaryOwnersId(Collection<Integer> secondaryOwnersId) {
			this.secondaryOwnersId = new ArrayList<>(secondaryOwnersId);
			return this;
		}

		public Account build() {
			return new Account(id, customerId, password, accountNumber, balance, secondaryOwnersId);
		}
	}
}
