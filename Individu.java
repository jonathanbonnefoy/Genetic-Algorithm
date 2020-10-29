import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Individu {
	
	private LinkedList<Integer> villes;
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
	public LinkedList<Integer> melangerVilles() {
		LinkedList<Integer> chemin = Villes.villesOrigine;
        Collections.shuffle(chemin);
        return chemin;    
	}
	
	/**
	 * evalue le score de fitness du chemin 
	 * @return son score de fitness
	 */
	
	public double fonctionEvaluation() {
		double somme = 0;
		for (int i = 0 ; i < Villes.nbVilles ; i ++) {		
			if (this.villes.get(i) == this.villes.getLast()) {
				somme += graphe.getDistance(this.villes.get(i),this.villes.get(i+1));
			} else {
				somme += graphe.getDistance(this.villes.get(i),this.villes.getFirst());
			}
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
        Collections.swap(this.villes, x, y);
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

	public LinkedList<Integer> getVilles() {
		return this.villes;
	}
	
}
