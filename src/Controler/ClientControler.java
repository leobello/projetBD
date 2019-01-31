package Controler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.Client;
import BDD.Impression;
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
			String requete = "INSERT INTO CLIENT VALUES (" + client.getMailClient() + "," + client.getNom() + ","
					+ client.getPrenom() + "," + client.getMotDePasse() + ")";

			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return createOK;
	}

	//
	public Client readClient(String mailClient) {
		try {
			String requete = "SELECT * FROM CLIENT WHERE MAILCLIENT = " + "'" + mailClient + "'";
			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);
			while (rs.next()) {
				client = new Client(rs.getString("MAILCLIENT"), rs.getString("NOM"), rs.getString("PRENOM"),
						rs.getString("MOTDEPASSE"));
			}
			loadImpressions(client);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	private void loadImpressions(Client client) throws SQLException {
		String idClient = client.getMailClient();
		Statement stmt = BD.getInstance().getReadCommittedSTMT();
		String req = "SELECT * FROM IMPRESSION WHERE MAILCLIENT = '" + idClient+"'";
		ResultSet rs = stmt.executeQuery(req);
		boolean impressionOK;
		while(rs.next()) {
			if(Integer.parseInt(rs.getString("IMPRESSION_OK")) == 1){
				impressionOK = true;
			} else {
				impressionOK = false;
			}
			client.getImpressions().add(new Impression(Integer.parseInt(rs.getString("NUMIMPRESSION")),
					rs.getString("PATH_IMPRESSION"),
					impressionOK,
					rs.getString("QUALITE"),
					rs.getString("FORMAT")));
		}
	}

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
