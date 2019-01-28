package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Commande;

public class CommandeControler implements CRUDInterface<Commande>{
	private Commande commande;
	private static Statement stmt;

	public CommandeControler(Statement stmt) {
		this.stmt = stmt;
	}

	@Override
	public boolean create(Commande object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Commande read(int identifiant) {
		// TODO Auto-generated method stub
		return commande;
	}

	@Override
	public boolean update(Commande object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	
	

}
