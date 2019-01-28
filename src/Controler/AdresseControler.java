package Controler;

import java.sql.SQLException;
import java.sql.Statement;

import BDD.Adresse;
import BDD.CRUDInterface;

public class AdresseControler implements CRUDInterface <Adresse>{
	private Adresse adresse;
	private static Statement stmt;
	

	public AdresseControler(Statement stmt) {
		this.stmt = stmt;
	}

	@Override
	public boolean create(Adresse object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Adresse read(int identifiant) {
		// TODO Auto-generated method stub
		try {
			stmt.executeQuery("select rue from Adresse");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adresse;
	}

	@Override
	public boolean update(Adresse object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		System.out.println("C'est passé");
		return false;
	}

}
