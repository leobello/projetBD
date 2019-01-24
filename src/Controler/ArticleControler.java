package Controler;

import BDD.Article;
import BDD.CRUDInterface;

public class ArticleControler implements CRUDInterface<Article>{
	private Article article;

	@Override
	public boolean create(Article object) {
		// TODO Auto-generated method stub
		return false;
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
