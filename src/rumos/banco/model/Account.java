package rumos.banco.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Account {

	
	private Integer customerId;
	private String passwordAccount;

	private String accountHolderNumber; // 5 digits
	private Double accountHolderBalance;
	private String[] secondaryAccountNumber = { "", "", "", "" };
	private ArrayList<String> transactionHistory = new ArrayList<String>();
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountHolderBalance == null) ? 0 : accountHolderBalance.hashCode());
		result = prime * result + ((accountHolderNumber == null) ? 0 : accountHolderNumber.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((passwordAccount == null) ? 0 : passwordAccount.hashCode());
		result = prime * result + Arrays.hashCode(secondaryAccountNumber);
		result = prime * result + ((transactionHistory == null) ? 0 : transactionHistory.hashCode());
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
		if (accountHolderBalance == null) {
			if (other.accountHolderBalance != null)
				return false;
		} else if (!accountHolderBalance.equals(other.accountHolderBalance))
			return false;
		if (accountHolderNumber == null) {
			if (other.accountHolderNumber != null)
				return false;
		} else if (!accountHolderNumber.equals(other.accountHolderNumber))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (passwordAccount == null) {
			if (other.passwordAccount != null)
				return false;
		} else if (!passwordAccount.equals(other.passwordAccount))
			return false;
		if (!Arrays.equals(secondaryAccountNumber, other.secondaryAccountNumber))
			return false;
		if (transactionHistory == null) {
			if (other.transactionHistory != null)
				return false;
		} else if (!transactionHistory.equals(other.transactionHistory))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [customerId=" + customerId + ", passwordAccount=" + passwordAccount + ", accountHolderNumber="
				+ accountHolderNumber + ", accountHolderBalance=" + accountHolderBalance + ", secondaryAccountNumber="
				+ Arrays.toString(secondaryAccountNumber) + ", transactionHistory=" + transactionHistory + "]";
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getPasswordAccount() {
		return passwordAccount;
	}
	public void setPasswordAccount(String passwordAccount) {
		this.passwordAccount = passwordAccount;
	}
	public String getAccountHolderNumber() {
		return accountHolderNumber;
	}
	public void setAccountHolderNumber(String accountHolderNumber) {
		this.accountHolderNumber = accountHolderNumber;
	}
	public Double getAccountHolderBalance() {
		return accountHolderBalance;
	}
	public void setAccountHolderBalance(Double accountHolderBalance) {
		this.accountHolderBalance = accountHolderBalance;
	}
	public String[] getSecondaryAccountNumber() {
		return secondaryAccountNumber;
	}
	public void setSecondaryAccountNumber(String[] secondaryAccountNumber) {
		this.secondaryAccountNumber = secondaryAccountNumber;
	}
	public ArrayList<String> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(ArrayList<String> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	
}
