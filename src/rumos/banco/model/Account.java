package rumos.banco.model;

import java.util.ArrayList;

public class Account {

	
	
	private String password;
	private Integer customerId;
	
	
	
	
	private String accountHolderNumber; // 5 digits
	private Double accountHolderBalance;
	private String[] secondaryAccountNumber = { "", "", "", "" };
	private ArrayList<String> transactionHistory = new ArrayList<String>();
	
	
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
