package common;

import java.util.Random;

public class RandomStringGenerator {
	
	public static String get() {
		StringBuilder builder = new StringBuilder(20);
		Random rand = new Random();
		
		int low = 97;
		int high = 122;

		for (int i = 0; i < 20; i++) {
			char randChar = (char) (low + rand.nextInt(high - low + 1));
			builder.append(randChar);
		}
		
		return builder.toString();
	}
	
	
}
