package rumos.banco.goncalopimenta;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
	private Integer id;
	private String name;
	private String password;
	private String taxId;
	private String email;
	private LocalDate birthday;
	private Double balance;

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

	public Integer calculateAge() {
		LocalDate today = LocalDate.now();
		Period period = Period.between(this.birthday, today);

		return period.getYears();
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", password=" + password + ", taxId=" + taxId + ", email="
				+ email + ", birthday=" + birthday + ", balance=" + balance + "]";
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
