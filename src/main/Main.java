package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import db.Connect;

public class Main {
	Vector<Fish> listFish = new Vector<>();
	Vector<Transaction> listTrans = new Vector<>();
	Scanner scanner = new Scanner(System.in);
	Connect con = Connect.getConnection();
	public void mainMenu() {
		System.out.println("Exotic Fish Store");
		System.out.println("==================");
		System.out.println("1. Purchase Exotic Fish");
		System.out.println("2. View Transaction");
		System.out.println("3. Cancel Transaction");
		System.out.println("4. Exit Menu");
	}
	public void loadSalt() {
		String id,name,depth;
		double size;
		int speed;
		
		String query = "SELECT * FROM saltwaterfish";
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				id = rs.getString("FishID");
				name = rs.getString("FishName");
				size = rs.getDouble("FishSize");
				speed = rs.getInt("FishSpeed");
				depth = rs.getString("FishDepthTolerance");
				listFish.add(new Saltwater(id, name, size, speed, depth));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadFresh() {
		String id,name;
		double size;
		int speed,algae;
		String query = "SELECT * FROM freshwaterfish";
		ResultSet rs = con.executeQuery(query);
		
		
			try {
				while(rs.next()) {
					id = rs.getString("FishID");
					name = rs.getString("FishName");
					size = rs.getDouble("FishSize");
					speed = rs.getInt("FishSpeed");
					algae = rs.getInt("FishAlgaeTolerance");
					listFish.add(new Freshwater(id, name, size, speed, algae));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	public void loadTrans() {
		String fishId,email;
		int transactionId, quantity;
		String query = "SELECT * FROM transaction";
		ResultSet rs = con.executeQuery(query);
		try {
			while(rs.next()) {
				transactionId = rs.getInt("TransactionID");
				fishId = rs.getString("FishID");
				email = rs.getString("UserEmail");
				quantity = rs.getInt("Quantity");
			listTrans.add(new Transaction(transactionId, fishId, email, quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isValidEmail(String email) {
		if(email.contains("@") && email.endsWith(".com")) {
			return true;
		}
		return false;
	}
	public boolean isValidType(String type) {
		if(type.equals("Freshwater") || type.equals("Saltwater")) {
			return true;
			
		}
		return false;
	}
	
	public void viewSaltwater() {
		
		System.out.println("| NO | FishID | Fish Name | Fish Size | FishSpeed | DepthTolerance");
		int i = 1;
		for(Fish fish : listFish) {
			if(fish instanceof Saltwater) {
				System.out.printf("| %d | %s | %s | %f | %d | %s\n",i,fish.getId(),fish.getName(),fish.getSize(),fish.getSpeed()
						,((Saltwater)fish).getDepth());
				i++;
			}
		}
	}
	public void viewFreshwater() {
		System.out.println("| NO | FishID | Fish Name | Fish Size | FishSpeed | Algae Tolerance");
		int i = 1;
		for(Fish fish : listFish) {
			if(fish instanceof Freshwater) {
				System.out.printf("| %d | %s | %s | %f | %d | %d\n",i,fish.getId(),fish.getName(),fish.getSize(),fish.getSpeed()
						,((Freshwater)fish).getAlgae());
				i++;
			}
		}
	}
	
	public void purchaseFish() {
		String email = "";
		int quantity = 0;
		String type = "";
		
		do {
			System.out.print("Input User Email [Ends with '.com' | contains '@']: ");
			email = scanner.nextLine();
		} while (!isValidEmail(email));
		do {
			System.out.print("Input Quantity [Must be Greater than 0]: ");
			quantity = scanner.nextInt();
		} while (quantity < 0);
		scanner.nextLine();
		
		do {
			System.out.print("Input Fish Type [Freshwater | Saltwater](Case Sensitive): ");
			type = scanner.nextLine();
		} while (!isValidType(type));
		
		if(type.equals("Saltwater")) {
			loadSalt();
			viewSaltwater();
			int input= 0;
			
			System.out.print("Input fish index to purchase[1-6]: ");
			input = scanner.nextInt();
			
			input-=1;
			
				
					
						System.out.println("Fish ID: " + listFish.get(input).getId());
						System.out.println("Fish Name: " + listFish.get(input).getName());
						System.out.println("Fish Size: " + listFish.get(input).getSize());
						System.out.println("Fish Speed: " + listFish.get(input).getSpeed());
						System.out.println("Fish Price: "+ listFish.get(input).price(quantity));
					
				
				
				String fishId = listFish.get(input).getId();
			
			Transaction transaction = new Transaction(0,email,fishId,quantity);
			transaction.insert();
		} else if(type.equals("Freshwater")) {
			loadFresh();
			viewFreshwater();
			int input= 0;
			
			System.out.print("Input fish index to purchase[1-6]: ");
			input = scanner.nextInt();
			
			input-=1;
			
			
		
					System.out.println("Fish ID: " + listFish.get(input).getId());
					System.out.println("Fish Name: " + listFish.get(input).getName());
					System.out.println("Fish Size: " + listFish.get(input).getSize());
					System.out.println("Fish Speed: " + listFish.get(input).getSpeed());
					System.out.println("Fish Price: "+ listFish.get(input).price(quantity));
			
			
			
				
				String fishId = listFish.get(input).getId();
			
			Transaction transaction = new Transaction(0,email,fishId,quantity);
			transaction.insert();
			listTrans.add(transaction);
		}
		
	}
	
	public void viewTrans() {
		loadTrans();
		if(!listTrans.isEmpty()) {
			System.out.println("| Transaction ID | User Email | Quantity | FishID | Fish Name");
			for(int i = 0 ; i < listTrans.size(); i ++) {
				for(Fish fish : listFish) {
					if(listTrans.get(i).getFishId().equals(fish.getId())) {
						System.out.printf("| %d | %s | %d | %s | %s",listTrans.get(i).getTransactionId(),
								listTrans.get(i).getEmail(),listTrans.get(i).getQuantity(),listTrans.get(i).getFishId(),
								fish.getName()
								);
					}
					
				}
			}
		}
		else {
			System.out.println("There is no data");
		}
		
	}
	public void deleteTrans() {
		viewTrans();
		int transId= 0;
		System.out.print("Input transaction ID to be Cancelled: ");
		transId = scanner.nextInt();
		scanner.nextLine();
		
		String query = String.format("DELETE FROM transaction WHERE TransactionID = '%d'", transId);
		con.executeUpdate(query);
		
	}
	
	public Main() {
		// TODO Auto-generated constructor stub
		int menu = 0;
		boolean loop = true;
		
		do {
			
			
			mainMenu();
			try {
				System.out.print("Input [1-4]: ");
				menu = scanner.nextInt();
			} catch (Exception e) {
				// TODO: handle exception
				menu = -1;
			}
			scanner.nextLine();
			do {
				switch (menu) {
				case 1:
					purchaseFish();
					break;
				case 2:
					viewTrans();
					break;
				case 3:
					deleteTrans();
					break;
				case 4:
					loop = !loop;
					break;
				}
			} while (menu < 1 || menu > 4);
		} while (loop);
	}
	public static void main(String[] args) {
		new Main();
	}

}
