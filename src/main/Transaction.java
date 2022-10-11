package main;

import db.Connect;

public class Transaction {
	private int transactionId;
	private String fishId;
	private String email;
	private int quantity;
	
	public Transaction(int transactionId, String fishId, String email, int quantity) {
		super();
		this.transactionId = transactionId;
		this.fishId = fishId;
		this.email = email;
		this.quantity = quantity;
	}
	
	public void insert() {
		Connect con = Connect.getConnection();
		String query = String.format("INSERT INTO transaction VALUES('%d', '%s', '%d', '%s')"
				,transactionId,email,quantity,fishId );
		con.executeUpdate(query);
		
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getFishId() {
		return fishId;
	}

	public void setFishId(String fishId) {
		this.fishId = fishId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
