package rumos.banco.utils;

import java.util.Random;

import javax.sound.midi.Soundbank;

public class NumbersGenerator {

	public final static String ALLNUMBERS = "0123456789";
	public final static Random RANDOM = new Random();
	
	public static String createString(int digits) {
		StringBuilder newPin = new StringBuilder();
		
		while(newPin.length() < 4) {
			int index = (int) (RANDOM.nextFloat() * ALLNUMBERS.length());
			newPin.append(ALLNUMBERS.charAt(index));
		}
		System.out.println(newPin.toString());
		return newPin.toString();
	}
	
}
