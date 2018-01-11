package at.fhhagenberg.sqe.ecc;

public class Floor {

	private static int floorsCounter = 1;
	
	private int number;
	
	public Floor() {
		this.number = floorsCounter++;
	}
	
	public int getNumber() {
		return number;
	}
}
