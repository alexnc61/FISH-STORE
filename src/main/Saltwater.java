package main;

public class Saltwater extends Fish {
	private String depth;
	
	public Saltwater(String id, String name, double size, int speed, String depth) {
		super(id, name, size, speed);
		this.depth = depth;
	}

	public double price(int quantity) {
		double calculate = size*speed*quantity*2.5;
		return calculate;
	}
	
	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}
		

}
