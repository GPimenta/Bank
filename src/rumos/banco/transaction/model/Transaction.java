package rumos.banco.transaction.model;

import java.time.LocalDateTime;

import rumos.banco.common.model.IdentificationItem;

public class Transaction implements IdentificationItem{
	private Integer id;
	private Integer accountId;
	private Integer cardId;
	private LocalDateTime timestamp;
	private String amount;

	public Transaction(Integer id, Integer accountID, Integer cardID, LocalDateTime timestamp, String amount) {
		super();
		this.id = id;
		this.accountId = accountID;
		this.cardId = cardID;
		this.timestamp = timestamp;
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		Transaction other = (Transaction) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountID=" + accountId + ", cardID=" + cardId + ", timestamp=" + timestamp
				+ ", amount=" + amount + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountID() {
		return accountId;
	}

	public void setAccountID(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getCardID() {
		return cardId;
	}

	public void setCardID(Integer cardId) {
		this.cardId = cardId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public static class Builder {
		private Integer id;
		private Integer accountId;
		private Integer cardId;
		private LocalDateTime timestamp;
		private String amount;

		public Builder() {

		}

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withAccountId(Integer accountId) {
			this.accountId = accountId;
			return this;
		}
		
		public Builder withCardId(Integer cardId) {
			this.cardId = cardId;
			return this;
		}
		public Builder withTimeStamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		public Builder withAmount(String amount) {
			this.amount = amount;
			return this;
		}
		
		public Transaction build() {
			return new Transaction(id, accountId,cardId, timestamp,amount);
		}
	}

}
