package Controler;

import java.sql.ResultSet;
import BDD.CRUDInterface;
import BDD.Stock;
import serviceBD.BD;

public class StockControler implements CRUDInterface<Stock> {
	private Stock stock;
	private BD bd;

	public StockControler(BD bd) {
		this.bd = bd;
	}

	public Stock readStock(String typeImp, String qualite, String format) {
		try {
			String requete = "SELECT * FROM STOCK " + "where TYPE_IMPRESSION = '" + typeImp + "' " + "AND QUALITE = '"
					+ qualite + "' " + "AND FORMAT = '" + format + "'";
			ResultSet result = this.bd.getReadCommittedSTMT().executeQuery(requete);
			while (result.next()) {
				stock = new Stock(result.getString("TYPE_IMPRESSION"), result.getInt("QUANTITESTOCK"),
						result.getString("QUALITE"), result.getString("FORMAT"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stock;
	}

	//
	@Override
	public boolean update(Stock stock) {
		boolean checkUpdate = false;
		try {
			String typeImp = stock.getTypeImression();
			String qteStock = stock.getQualite();
			String format = stock.getFormat();

			String requete = "UPDATE STOCK set QUANTITESTOCK = " + stock.getQuantiteStock()
					+ " WHERE TYPE_IMPRESSION = '" + typeImp + "' AND QUALITE = '" + qteStock + "'" + " AND FORMAT = '"
					+ format + "'";
			int insert = this.bd.getSerializableSTMT().executeUpdate(requete);
			if (insert > 0) {
				checkUpdate = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkUpdate;
	}

	//
	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stock read(int identifiant) {

		return stock;
	}

	@Override
	public boolean create(Stock object) {
		// TODO Auto-generated method stub
		return false;
	}

}
