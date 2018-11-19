package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO{
	
	private static final String DB_URL = "jdbc:derby://localhost:64413/EECS;user=student;password=secret";
//	public static final String DB_URL = "jdbc:derby://red.eecs.yorku.ca:64413/EECS;user=student;password=secret";

	
//	private List<ItemBEAN> beans;
	
	public ItemDAO(){ }
	
	/**
	 * 
	 * @return a list of all the food items in the database
	 * @throws Exception 
	 */
//	public List<ItemBEAN> retrieve() throws Exception {
//		
//		try {
//			List<ItemBEAN> beans = new ArrayList<ItemBEAN>();
//			
//			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
//			Connection con = DriverManager.getConnection(DB_URL);
//			Statement s = con.createStatement();
//			s.executeUpdate("set schema roumani");
//			
//			String query = "SELECT * FROM ITEM ORDER BY NAME ASC";
//			
//			
//			ResultSet r = s.executeQuery(query);
//			
//			ItemBEAN bean;
//			
//			while(r.next()) {
//				
//				String number = r.getString("NUMBER"),
//						name = r.getString("NAME"),
//						price = r.getString("PRICE"),
//						qty = 1+"",//r.getString("QTY"),
////						onorder = r.getString("ONORDER"),
////						reorder = r.getString("REORDER"),
//						catid = r.getString("CATID");
////						supid = r.getString("SUPID"),
////						costprice = r.getString("COSTPRICE");
//				
//				bean = new ItemBEAN(number, name, price, qty, catid);
//				beans.add(bean);
//				
//			}
//			r.close(); s.close(); con.close();
//			
//			return beans;
//							
//		}
//		catch(ClassNotFoundException cnfe) {
//			cnfe.printStackTrace();
//			throw new Exception("Could not initialize derby. Please try again later");
//		}
//		catch(SQLException sqle) {
//			sqle.printStackTrace();
//			throw new Exception("Database encounterd an error. Please Try again later.");
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			throw new Exception("We're having difficulties right now. Please try again later");
//		}
//	}
	
	/**
	 * 
	 * @param catID
	 * @return returns a list of all the food items in the database
	 * based on the category given
	 * @throws Exception 
	 */
	public List<ItemBEAN> retrieve(String foodName, String sortBy, String catID) throws Exception{
		
		try {
			List<ItemBEAN> beans = new ArrayList<ItemBEAN>();
			
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			Connection con = DriverManager.getConnection(DB_URL);
			Statement s = con.createStatement();
			s.executeUpdate("set schema roumani");
			
//			String query = "SELECT * FROM ITEM WHERE NAME LIKE '%"+ foodName
//					+ "%' AND CATID LIKE " + catID
//					+ " ORDER BY "+ sortBy +" ASC";
			
			
			String query;
			foodName = foodName.toLowerCase();
			
			if(!foodName.trim().isEmpty()) {
				if (catID.equalsIgnoreCase("none")) {
					query = "SELECT * FROM ITEM WHERE lower(NAME) LIKE '%"+foodName+"%' ORDER BY "+ sortBy;
				}
				else {
					query = "SELECT * FROM ITEM WHERE lower(NAME) LIKE '%"+foodName+"%' and CATID = " + catID + " ORDER BY "+ sortBy;
				}
			}
			else {
				if (catID.equalsIgnoreCase("none")) {
					query = "SELECT * FROM ITEM ORDER BY "+ sortBy;
				}
				else {
					query = "SELECT * FROM ITEM WHERE CATID = " + catID + " ORDER BY "+ sortBy;
				}
			}
			
			
			
			
			ResultSet r = s.executeQuery(query);
			
			ItemBEAN bean;
			
			while(r.next()) {
				
				String number = r.getString("NUMBER"),
						name = r.getString("NAME"),
						price = String.format("$%.2f", Double.parseDouble(r.getString("PRICE"))),
						qty = 1+"",//r.getString("QTY"),
//						onorder = r.getString("ONORDER"),
//						reorder = r.getString("REORDER"),
						catid = r.getString("CATID");
//						supid = r.getString("SUPID"),
//						costprice = r.getString("COSTPRICE");
				
				bean = new ItemBEAN(number, name, price, qty, catid);
				beans.add(bean);
				
			}
			r.close(); s.close(); con.close();
			
			return beans;
							
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			throw new Exception("Could not initialize derby. Please try again later");
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			throw new Exception("Database encounterd an error. Please Try again later.");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("We're having difficulties right now. Please try again later");
		}
		
	}
	
	
	public void sortBy(String att){
		
		
	}
	
	
//	private static void log(String s) {
//		System.out.println(s);
//	}
//	
//	
//	public static void main(String[] args) throws Exception {
//		
////		ItemDAO i = new ItemDAO();
//		
//		String a = "Deli Meat";
//		String b = " ";
//		
//		log ((b.trim().isEmpty())+"");
//		
//	}

}
