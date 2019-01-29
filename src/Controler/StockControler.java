package Controler;

import java.sql.ResultSet;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Stock;
import serviceBD.BD;

public class StockControler implements CRUDInterface<Stock>{
	private Stock stock;
	private BD bd;

	public StockControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Stock object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stock read(int identifiant) {
		
		return stock;
	}
	
	public Stock readStock(String typeImp, String qualite, String format) {
		try {
			String requete = "SELECT * FROM STOCK "
					+ "where TYPE_IMPRESSION = '"+typeImp+"' "
							+ "AND QUALITE = '"+ qualite + "' "
							+ "AND FORMAT = '"+ format+"'";
			ResultSet result = this.bd.getReadCommittedSTMT().executeQuery(requete);
			while (result.next()) {
				stock = new Stock (result.getString("TYPE_IMPRESSION"),
						result.getInt("QUANTITESTOCK"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stock;
	}

	@Override
	public boolean update(Stock stock) {
		// serialisable
		boolean checkUpdate = false;
		try {
			String typeImp = stock.getTypeImression();
			int qteStock = stock.getQuantiteStock();
			
			//String requete = "UPDATE STOCK set QUANTITESTOCK = "+ qteStock + 
					//"WHERE TYPE_IMPRESSION = '"+ stock.getTypeImression()+"' AND QUALITE = '"+stock.get;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
