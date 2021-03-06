package Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BDD.Article;
import BDD.CRUDInterface;
import serviceBD.BD;
import serviceBD.BuildReq;

public class ArticleControler implements CRUDInterface<Article> {
	private Article article;
	private Statement stmt;
	// serializable
	public ArticleControler(BD bd) {
		try {
			this.stmt = bd.getSerializableSTMT();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean create(Article object) {
		BuildReq br = new BuildReq();
		String req = "INSERT INTO ARTICLE(ID_ARTICLE,PRIX,QUANTITE,NUMIMPRESSION) VALUES ("+object.getIdArticle()+",  "+object.getPrix()+", "+object.getQuantite()+", "+object.getImpression().getNumImpression()+")";
		//String req = br.insert("ARTICLE(ID_ARTICLE,PRIX,QUANTITE,NUMIMPRESSION)", "ARTICLES_SEQ.NEXTVAL", String.valueOf(object.getPrix()), String.valueOf(object.getQuantite()), 
				//String.valueOf(object.getImpression().getNumImpression()));
		try {
			int rs = stmt.executeUpdate(req);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Article read(int identifiant) {

		// TODO Auto-generated method stub
		try {
			String requete = "SELECT * FROM ARTICLE WHERE ID_ARTICLE = " + identifiant;
			ResultSet rs = stmt.executeQuery(requete);
			while (rs.next()) {
				article = new Article(identifiant, rs.getFloat("PRIX"), rs.getInt("QUANTITE"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return article;
	}

	@Override
	public boolean update(Article object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}