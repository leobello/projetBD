package Controler;

import java.sql.ResultSet;
import java.sql.SQLException;

import BDD.CRUDInterface;
import BDD.Stock;
import serviceBD.BD;

public class StockControler implements CRUDInterface<Stock> {
	private Stock stock;
	private BD bd;
	// serializable

	public StockControler(BD bd) {
		this.bd = bd;
	}

	public Stock readStock(String typeImp, String qualite, String format) {
		try {
			String requete = "SELECT * FROM STOCK " + "where TYPE_IMPRESSION = '" + typeImp + "' " + "AND QUALITE = '"
					+ qualite + "' " + "AND FORMAT = '" + format + "'";
			ResultSet result = this.bd.getSerializableSTMT().executeQuery(requete);
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
	public void updateStock(int idArticle,int quantite) {
		String req = "SELECT TYPE_IMPRESSION, QUALITE, FORMAT FROM STOCKARTICLE WHERE ID_ARTICLE = " + idArticle;
		String type = "";
		String qualite = "";
		String format = "";
		try {
			ResultSet rs = bd.getSerializableSTMT().executeQuery(req);
			while(rs.next()) {
				type = rs.getString("TYPE_IMPRESSION");
				qualite = rs.getString("QUALITE");
				format = rs.getString("FORMAT");
			}
			this.update(new Stock( type, quantite,  qualite , format));
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
