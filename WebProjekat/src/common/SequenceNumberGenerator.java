package common;

public class SequenceNumberGenerator {
	private static long number;
	
	private SequenceNumberGenerator() {	}

	public synchronized static long getNumber() {
		return number;
	}

	public synchronized static void setNumber(long number) {
		SequenceNumberGenerator.number = number;
	}
	
	public synchronized static long incrementAndGet() {
		number++;
		return number;
	}
}
