package affichage;

import java.sql.Date;
import java.sql.Timestamp;

public class General {
	public static void erreurDeChoix() {
		System.out.println( "  /\\  |-------------------------------------|  /\\\n"
						+ 	" /!!\\ |Veuillez entrer un des choix proposés| /!!\\\n"
						+ 	"/!!!!\\|-------------------------------------|/!!!!\\\n");
	}
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	public static Date getDateNow() {
		return new Date(new Timestamp(System.currentTimeMillis()).getTime());
	}
}
