package main;

public class Freshwater extends Fish{
	private int algae;
	
	public Freshwater(String id, String name, double size, int speed, int algae) {
		super(id, name, size, speed);
		this.algae = algae;
	}
	
	public double price(int quantity) {
		double calculate = size * speed* quantity * algae;
		return calculate;
	}
	
	public int getAlgae() {
		return algae;
	}

	public void setAlgae(int algae) {
		this.algae = algae;
	}
	

}
