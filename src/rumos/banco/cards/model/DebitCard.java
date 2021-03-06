package rumos.banco.cards.model;

public class DebitCard extends Card{

	public DebitCard(Integer id, Integer customerId, Integer accountId, String cardNumber, String pin) {
		super(id, customerId, accountId, cardNumber, pin);
	}
	
	public static class Builder{
		private Integer id;
		private Integer customerId;
		private Integer accountId;
		private String cardNumber;
		private String pin;
		
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
		
		
		public DebitCard build() {
			return new DebitCard(id, customerId, accountId, cardNumber, pin);
		}
		
	}
	


}
