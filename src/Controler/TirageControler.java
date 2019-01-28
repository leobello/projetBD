package Controler;

import java.sql.SQLException;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Tirage;

public class TirageControler implements CRUDInterface<Tirage> {
	private Tirage tirage;
	private Statement stmt;
	
	public  TirageControler(Statement stmt) {
		this.stmt = stmt;
	}

	@Override
	public boolean create(Tirage tirage) {
		// TODO Auto-generated method stub
		try {
			this.stmt.executeQuery(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Tirage read(int identifiant) {
		// TODO Auto-generated method stub
		return tirage;
	}

	@Override
	public boolean update(Tirage object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}



}
