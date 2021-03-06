package rumos.banco.utils;

public class Preconditions {
	private Preconditions() {
		
	}
	
	public static void checkArgument(boolean expression, String errorMessage) {
		if(!expression) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
	
	public static String checkLength(String value, int length, String errorMessage) {
		String valueClean = value.strip();
		if(value == null | valueClean.length() <= length) {
			throw new IllegalArgumentException(errorMessage);
		}
		return valueClean;
	}
	
	public static <T> T checkNotNull(T value, String errorMessage) {
		if(value == null) {
			throw new IllegalArgumentException(errorMessage);
		}
		return value;
	}
	
}
