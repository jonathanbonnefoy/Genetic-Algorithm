import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Individu {
	
	private LinkedList<Integer> villes;
	private double score;
	public static Graphe graphe = Graphe.getInstance();
	
	/**
	 * Instancie un Individu en melangeant la liste des villes 
	 * ordonnees puis evalue son score de fitness
	 */
	public Individu() {
		this.villes = new LinkedList<>(Villes.villesOrigine);
		Collections.shuffle(this.villes);
		this.score = this.fonctionEvaluation();
	}

	
	/**
	 * evalue le score de fitness du chemin 
	 * @return son score de fitness
	 */
	
	public double fonctionEvaluation() {
		double somme = 0;
		for (int i = 0 ; i < Villes.nbVilles ; i ++) {	
			if (this.villes.get(i) == this.villes.getLast()) {
				somme += graphe.getDistance(this.villes.get(i),this.villes.getFirst());
			} else {
				somme += graphe.getDistance(this.villes.get(i),this.villes.get(i+1));
			}
		}
		return somme;
	}
	
    /* 
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
        this.score = this.fonctionEvaluation();
        
    }
	
	/**
	 * renvoie le chemin de l'Individu 
	 */
	@Override
	public String toString() {
		return "" + this.villes.toString();
	}

	public double getScore() {
		return this.score;
	}

	public LinkedList<Integer> getVilles() {
		return this.villes;
	}
	
}
