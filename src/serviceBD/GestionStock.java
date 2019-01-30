package serviceBD;

import BDD.Stock;
import Controler.StockControler;
import Controler._GlobalControler;

public class GestionStock {
	private StockControler stkc;

	public GestionStock() {
		stkc = _GlobalControler.getStockControler();
	}

	public boolean decrStock(int i, String typeImp, String qualite, String format) {
		Stock currentStock = stkc.readStock(typeImp, qualite, format);
		if (currentStock.getQuantiteStock() < i) {
			return false;
		} else {
			currentStock.setQuantiteStock(currentStock.getQuantiteStock() - i);
			stkc.update(currentStock);
			return true;
		}

	}

	public void incrStock(int i, String typeImp, String qualite, String format) {
		Stock currentStock = stkc.readStock(typeImp, qualite, format);
		currentStock.setQuantiteStock(currentStock.getQuantiteStock() + i);
		stkc.update(currentStock);
	}
}
