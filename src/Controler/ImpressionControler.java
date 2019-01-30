package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Impression;

public class ImpressionControler implements CRUDInterface<Impression> {
	private Impression impression;
	private static Statement stmt;

	public ImpressionControler(Statement stmt) {
		ImpressionControler.stmt = stmt;
	}

	@Override
	public boolean create(Impression object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Impression read(int identifiant) {
		// TODO Auto-generated method stub
		return impression;
	}

	@Override
	public boolean update(Impression object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	

}
