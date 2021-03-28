package rumos.banco.accounts.exceptions;

public class AccountConflictException extends AccountException {

	AccountConflictException(String message, Object ...args) {
		super(String.format(message, args));
	}
	
}
