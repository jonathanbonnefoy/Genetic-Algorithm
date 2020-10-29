import java.util.LinkedList;

public class Villes {
	
	public static LinkedList<Integer> villesOrigine;
	public static int nbVilles;
	
	public Villes(int nombreVilles) {
		villesOrigine = new LinkedList<Integer>();
		for (int i = 0 ; i < nbVilles ; i ++) {
			villesOrigine.add(i);
		}
		nbVilles = nombreVilles;
	}
}
