package Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import BDD.CRUDInterface;
import BDD.Client;
import serviceBD.BD;

public class ClientControler implements CRUDInterface<Client> {
	private Client client;
	private BD bd;
	
	
	public ClientControler(BD bd) {
		this.bd = bd;
	}


	@Override
	public boolean create(Client client) {
		boolean createOK = false;
		try {
			String requete = "INSERT INTO ADRESSE VALUES ("+ 
					client.getMailClient()+","+
					client.getNom()+","+
					client.getPrenom()+";"+
					client.getMotDePasse()+")";                                  
			
			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);
			 
					} catch (Exception e) {
			e.printStackTrace();
		}
		
				return createOK;
	}
	//
	public Client readClient (String mailClient)
	{
		try {
			String requete = "SELECT * FROM CLIENT WHERE MAILCLIENT = "+"'"+mailClient+"'";
			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);
			while (rs.next()) {
				client = new Client(rs.getString("MAILCLIENT"),
						rs.getString("NOM"),rs.getString("PRENOM"),
						rs.getString("MOTDEPASSE")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	//
	@Override
	public boolean update(Client object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client read(int identifiant) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return client;
	}
	
	
}
