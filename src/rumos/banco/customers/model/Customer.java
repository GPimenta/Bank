package rumos.banco.customers.model;

import java.time.LocalDate;

public class Customer {

	private final Integer id;
	private String name;
	private final String taxId;
	private String email;
	private final LocalDate birthday;
	
	
	
	public Customer(Integer id, String name, String taxId, String email, LocalDate birthday) {
		this.id = id;
		this.name = name;
		this.taxId = taxId;
		this.email = email;
		this.birthday = birthday;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public String getTaxId() {
		return taxId;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((taxId == null) ? 0 : taxId.hashCode());
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
		Customer other = (Customer) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (taxId == null) {
			if (other.taxId != null)
				return false;
		} else if (!taxId.equals(other.taxId))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", taxId=" + taxId + ", email=" + email + ", birthday="
				+ birthday + "]";
	}



	public static class Builder{
		private Integer id;
		private String name;
		private String taxId;
		private String email;
		private LocalDate birthday;
		
		
		public Builder() {

		}
		
		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		public Builder withTaxId(String taxId) {
			this.taxId = taxId;
			return this;
		}
		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder withBirthday(LocalDate birthday) {
			this.birthday= birthday;
			return this;
		}
		
		public Customer build() {
			return new Customer(id,name,taxId,email,birthday);
		}
	}
}
