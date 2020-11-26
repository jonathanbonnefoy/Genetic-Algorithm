
public class Graphe {	
	
	private static Graphe graphe;
	
	private int ligne, colonne;
	private double[][] matrice;
	
	// instancie la matrice codant la distance entre chacune des villes
	private Graphe() {
		this.ligne = Villes.nbVilles;
		this.colonne = Villes.nbVilles;
		matrice = new double[ligne][colonne];
		this.genererGraphe();
	}
	
	public void genererGraphe() {
		for (int i = 0 ; i < this.ligne ; i++) {
			for (int j = 0 ; j <= i ; j++) {
				if (i == j) {
					matrice[i][j] = 0;
				} else {
					double coefficient = Ville.distanceEntrePoints(Villes.villesOrigine.get(i), Villes.villesOrigine.get(j));
					matrice[i][j] = coefficient;
					matrice[j][i] = coefficient;
				}
			}
		}
	}
	
	public double[][] getMatrice() {
		return this.matrice;
	}

	public double getDistance(int i, int j) {
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