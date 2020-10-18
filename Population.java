import java.util.ArrayList;

public class Population {
	
	public static final int NB_INDIVIDU = 8;
	private ArrayList<Individu> population = new ArrayList<Individu>();
	
	public Population() {
		for (int i = 0 ; i < NB_INDIVIDU ; i ++) {
			this.population.add(new Individu());
		}
	}
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
	
	public void croisement() {
		ArrayList<Individu> nouvellePopulation = new ArrayList<Individu>();
	
	}
    
	public String toString() {
		String s = "Population : \n";
		for (Individu i : this.population) {
			s += i.toString() + "\n";
		}
		return s;
	}
	
}
