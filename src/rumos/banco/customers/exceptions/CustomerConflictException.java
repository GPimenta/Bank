package rumos.banco.customers.exceptions;

public class CustomerConflictException extends CustomerException{
	public CustomerConflictException (String message, Object ...args) {
		super(String.format(message, args));
	}
}
