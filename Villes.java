import java.util.ArrayList;

public class Villes {
	
	public static ArrayList<Integer> villesOrigine;
	public static int nbVilles;
	
	public Villes(int nombreVilles) {
		villesOrigine = new ArrayList<Integer>();
		for (int i = 0 ; i < nbVilles ; i ++) {
			villesOrigine.add(i);
		}
		nbVilles = nombreVilles;
	}
}
