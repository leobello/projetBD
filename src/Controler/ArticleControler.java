package Controler;

import java.sql.Statement;

import BDD.Article;
import BDD.CRUDInterface;
import serviceBD.BD;

public class ArticleControler implements CRUDInterface<Article>{
	private Article article;
	private static BD bd;

	public ArticleControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Article article) {
		boolean checkCreate = false;
		int insertOk = 0;
		try {
			String requete = "INSERT INTO ARTICLE VALUES ( ARTICLES_SEQ.NEXTVALUE"+
					article.getIdArticle()+ ","+
					article.getCommande()+","+
					article.getPrix()+","+
					article.getQuantite()+","+
					article.getImpression();
			insertOk = this.bd.getReadCommittedSTMT().executeUpdate(requete);
			if (insertOk >0) {
				checkCreate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkCreate;
	}

	@Override
	public Article read(int identifiant) {
		// TODO Auto-generated method stub
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
