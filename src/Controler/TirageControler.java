package Controler;

import java.sql.SQLException;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Tirage;
import serviceBD.BD;

public class TirageControler implements CRUDInterface<Tirage> {
	private Tirage tirage;
	private BD bd;
	
	public  TirageControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(Tirage tirage) {
		boolean checkCreate = false;
		try {
			//String requete = "INSERT INTO TIRAGE VALUES ("+                
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
