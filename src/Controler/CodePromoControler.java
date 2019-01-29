package Controler;

import java.sql.Statement;

import BDD.CRUDInterface;
import BDD.CodePromo;

public class CodePromoControler implements CRUDInterface<CodePromo>{
	private CodePromo codePromo;
	private static Statement stmt;

	public CodePromoControler(Statement stmt) {
		this.stmt = stmt;
	}

	@Override
	public boolean create(CodePromo object) {
		// TODO Auto-generated method stub
		return false;
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
