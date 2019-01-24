package Controler;

import BDD.CRUDInterface;
import BDD.Stock;

public class StockControler implements CRUDInterface<Stock>{
	private Stock stock;

	@Override
	public boolean create(Stock object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stock read(int identifiant) {
		// TODO Auto-generated method stub
		return stock;
	}

	@Override
	public boolean update(Stock object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int idenTtifiant) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
