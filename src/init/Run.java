package init;
import serviceBD.*;
import affichage.*;

public class Run {
	public static void main(String args[]) {
		InteractionSystem.run();
		System.out.println("heho");
		BD bd = new BD();
		//bd.init();
		
	}
}
