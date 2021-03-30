package rumos.banco.customers.exceptions;

public class CustomerNotFoundException extends CustomerException{
	CustomerNotFoundException(String message, Object ...args) {
		super(String.format(message, args));
	}
}
