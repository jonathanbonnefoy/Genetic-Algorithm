import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	public static final int NB_INDIVIDU = 8;
	private ArrayList<Individu> population = new ArrayList<Individu>();
	
	public Population() {
		for (int i = 0 ; i < NB_INDIVIDU ; i ++) {
			this.population.add(new Individu());
		}
	}
	// test 
	public ArrayList<Individu> selectionParRang() {
		ArrayList<Individu> parentsRestants = new ArrayList<Individu>();
		this.triABulles();
		for (int i = 0 ; i < NB_INDIVIDU/2 ; i++) {
			parentsRestants.add(this.population.get(i));
		}
		return parentsRestants;
	}
	
	public ArrayList<Individu> selectionParRoulette() {
		ArrayList<Individu> parentsRestants = new ArrayList<Individu>();
		
		return parentsRestants;
	}
	
	public ArrayList<Individu> triABulles() {
		Individu tmp = new Individu();
		for (int i = 0 ; i < NB_INDIVIDU ; i++) {
			for (int j = 0 ; j < NB_INDIVIDU ; j++) {
				if (this.population.get(j).getScore() > this.population.get(i).getScore()) {
					tmp = this.population.get(i);
					this.population.set(i, this.population.get(j));
					this.population.set(j, tmp);
				}
			}
		}
		return this.population;
	}
	
	public double moyenneFitness() {
		double somme = 0;
		for (Individu i : this.population) {
			System.out.println(i.getScore());
			somme += i.fonctionEvaluation();
		}
		return somme/NB_INDIVIDU;
	}
	
	public void croisement(Individu P1, Individu P2) {
		ArrayList<Individu> nouvellePopulation = new ArrayList<Individu>();
		Random r = new Random();
		int pointDecoupe1 = r.nextInt();
		int pointDecoupe2 = r.nextInt();
		while (pointDecoupe1 == pointDecoupe2) {
			pointDecoupe2 = r.nextInt();
		}
	}
    
	public String toString() {
		String s = "Population : \n";
		for (Individu i : this.population) {
			s += i.toString() + "\n";
		}
		return s;
	}
	
}
