package main;

public abstract class Fish {
	protected String id;
	protected String name;
	protected double size;
	protected int speed;
	
	
	protected abstract double price(int quantity);
	
	public Fish(String id, String name, double size, int speed) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.speed = speed;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getSize() {
		return size;
	}



	public void setSize(double size) {
		this.size = size;
	}



	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
