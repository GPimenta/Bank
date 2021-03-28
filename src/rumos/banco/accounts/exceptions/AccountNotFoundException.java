package rumos.banco.accounts.exceptions;

public class AccountNotFoundException extends AccountException{

	AccountNotFoundException(String message, Object ...args) {
		super(String.format(message, args));
	}

}
