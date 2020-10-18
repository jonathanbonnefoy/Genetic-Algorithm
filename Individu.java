import java.util.Random;

public class Individu {
	
	private ListeCirculaire<Integer> villes;
	private double score;
	private Graphe graphe;
	
	/**
	 * Instancie un Individu en melangeant la liste des villes 
	 * ordonnees puis evalue son score de fitness
	 */
	public Individu() {
		this.villes = melangerVilles();
		this.graphe = Graphe.getInstance();
		this.score = this.fonctionEvaluation();
	}

	/**
	 * Melange les villes presentes dans la liste villes
	 */
	public ListeCirculaire<Integer> melangerVilles() {
		Random r = new Random();
		ListeCirculaire<Integer> chemin = new ListeCirculaire<Integer>();
		
        while (chemin.size() < Villes.nbVilles) {
        	int place = r.nextInt(Villes.nbVilles);
        	while(chemin.rechercherOccurences(place)) {
        		place = r.nextInt(Villes.nbVilles);	
        	}
        	chemin.ajouter(place);
        }
        chemin.setCompteur(0);
        return chemin;
	}
	
	/**
	 * evalue le score de fitness du chemin 
	 * @return son score de fitness
	 */
	
	public double fonctionEvaluation() {
		double somme = 0;
		for (int i = 0 ; i < Villes.nbVilles ; i ++) {		
			try {
				if (this.villes.suivant() != this.villes.premier()) {
					somme += graphe.getDistance(this.villes.getElement(i),this.villes.suivant());
				} else {
					somme += graphe.getDistance(this.villes.getElement(i),this.villes.getElement(0));
				}
			} catch (ListeVide e) {
				e.printStackTrace();
			}
			this.villes.avancerCompteur();
		}
		return somme;
	}
	
    /**
     * échange deux villes aléatoires de l'individu
     */
    public void mutation() {
        Random r = new Random();
        int x = r.nextInt(Villes.nbVilles);
        int y = r.nextInt(Villes.nbVilles);
        while (y == x) {
        	y = r.nextInt(Villes.nbVilles);
        }
        
        int tmp = this.villes.getElement(x);
        this.villes.changerElement(this.villes.getElement(y), x);
        this.villes.changerElement(tmp, y);
    }
	
	/**
	 * renvoie le chemin de l'Individu 
	 */
	@Override
	public String toString() {
		return "" + this.villes.toString();
	}

	public Graphe getGraphe() {
		return this.graphe;
	}

	public double getScore() {
		return this.score;
	}
	
}
