package rumos.banco.customers.exceptions;

public class CustomerConflictException extends CustomerException{
	CustomerConflictException (String message, Object ...args) {
		super(String.format(message, args));
	}
}
