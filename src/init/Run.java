package init;
import serviceBD.*;

import java.sql.SQLException;

public class Run {
	public static void main(String args[]) {

<<<<<<< HEAD
		/*try {
		    BD.getSTMT();
		} catch (SQLException e) {
			e.printStackTrace();
		}
*/
=======
		BuildReq br = new BuildReq();
		String req = br.insert("COMMANDE","2O19-01-28", "ADRESSE", "EN COURS", "10", "NULL", "LEOBELLO.WD@GMAIL.COM", "10.50");
		System.out.println(req);
>>>>>>> 41a0ba1410462090bcb9855e402f3b0266089336
	}
}
