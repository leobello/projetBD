package Controler;

import java.sql.ResultSet;
import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.CodePromo;
import serviceBD.BD;

public class CodePromoControler implements CRUDInterface<CodePromo> {
	private CodePromo codePromo;
	private BD bd;

	public CodePromoControler(BD bd) {
		this.bd = bd;
	}

	@Override
	public boolean create(CodePromo object) {
		boolean createOK = false;
		try {
			String requete = "INSERT INTO CODEPROMO VALUES (" + codePromo.getCode() + "," + codePromo.getPourcentage()
					+ "," + codePromo.getTypeCodePromo() + ")";

			ResultSet rs = this.bd.getReadCommittedSTMT().executeQuery(requete);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return createOK;
	}

	@Override
	public CodePromo read(int identifiant) {
		// TODO Auto-generated method stub
		return codePromo;
	}

	@Override
	public boolean update(CodePromo object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}

}
