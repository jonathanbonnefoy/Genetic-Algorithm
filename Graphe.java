
import java.util.Random;

public class Graphe {	
	
	private static Graphe graphe;
	
	private int ligne, colonne;
	private int[][] matrice;
	
	private Graphe() {
		this.ligne = Villes.nbVilles;
		this.colonne = Villes.nbVilles;
		matrice = new int[ligne][colonne];
		this.genererGraphe();
	}
	
	public void genererGraphe() {
		Random distance = new Random();
		for (int i = 0 ; i < this.ligne ; i++) {
			for (int j = 0 ; j <= i ; j++) {
				if (i == j) {
					matrice[i][j] = 0;
				} else {
					int coefficient = distance.nextInt(10)+1;
					matrice[i][j] = coefficient;
					matrice[j][i] = coefficient;
				}
			}
		}
	}
	
	public int[][] getMatrice() {
		return this.matrice;
	}

	public int getDistance(int i, int j) {
		return this.matrice[i][j];
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0 ; i < this.ligne ; i++) {
			for (int j = 0 ; j < this.colonne ; j++) {
				s += matrice[i][j] + "  ";
			}
			s += "\n";
		}
		return s;
	}
	
	public static synchronized Graphe getInstance() {
		if (graphe == null)
			graphe = new Graphe();
		return graphe;
	}

}