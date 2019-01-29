package init;
import serviceBD.*;
import affichage.*;

import java.sql.SQLException;

public class Run {
	public static void main(String args[]) {
		InteractionSystem.run();
		BD bd = new BD();
		/*try {
			bd.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
	}
}
