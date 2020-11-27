package rumos.banco.goncalopimenta;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class Customer {
	// client parameters
	private Integer id;
	private String name;
	private String password;
	private String taxId;
	private String email;
	private LocalDate birthday;

	// Balance parameters
	private String accountHolderNumber; //5 digits
	private Double accountHolderBalance;
	private String[] secondaryAccountNumber = {"","","",""};



	// Credit and debit card parameters
	private Boolean debitCard = false;
	private Boolean creditCard = false;

//	public Customer() {
//
//	}
//
//	public Customer(Integer id, String name, String password) {
//		this();
//		this.id = id;
//		this.name = name;
//		this.password = password;
//	}



	@Override
	public String toString() {
		return "Customer:\n"
				+ " id=" + id + ", name=" + name + ", password=" + password + ", taxId=" + taxId + ", email="
				+ email + ", birthday=" + birthday + ", accountHolderNumber=" + accountHolderNumber
				+ ", accountHolderBalance=" + accountHolderBalance + ",\nsecundaryAccountNumber="
				+ Arrays.toString(secondaryAccountNumber) + ", debitCard=" + debitCard + ", creditCard=" + creditCard
				+ "]";
	}



	/**
	 * Getters and Setters
	 * 
	 */
	
	
	public String[] getSecundaryAccountNumber() {
		return secondaryAccountNumber;
	}

	public void setSecundaryAccountNumber(String[] secundaryAccountNumber) {
		this.secondaryAccountNumber = secundaryAccountNumber;
	}
	// Quando formos usar este método, o código há de ficar algo como
	// customer.calculateAge();
	// Minha sugestão é que este método se chame 'getAge()' que retorna a idade
	// do customer
	// o nome 'calculateAge' parece que eu quero calcular a idade dele em outra data
	// OBS: Não é porque existe um método chamado 'getAge();' que o objecto
	// precisa ter um atributo age
	// YuriValle 11/11/2020
	// R: Obrigado pela dica Professor!!
	
	public Integer getAge() {
		LocalDate today = LocalDate.now();
		Period period = Period.between(this.birthday, today);

		return period.getYears();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return birthday;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.birthday = dateOfBirth;
	}

	public Double getHolderAccountBalance() {
		return accountHolderBalance;
	}

	public void setHolderAccountBalance(Double balance) {
		this.accountHolderBalance = balance;
	}
	public boolean isDebitCard() {
		return debitCard;
	}

	public void setDebitCard(boolean debitCard) {
		this.debitCard = debitCard;
	}

	public boolean isCreditCard() {
		return creditCard;
	}

	public void setCreditCard(boolean creditCard) {
		this.creditCard = creditCard;
	}

	public String getAccountHolderNumber() {
		return accountHolderNumber;
	}

	public void setAccountHolderNumber(String accountNumber) {
		this.accountHolderNumber = accountNumber;
	}
}
