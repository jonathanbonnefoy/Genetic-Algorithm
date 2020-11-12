import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Population {
	
	private ArrayList<Individu> population = new ArrayList<Individu>();
	
	public Population() {
		for (int i = 0 ; i < Villes.nbVilles ; i ++) {
			this.population.add(new Individu());
		}
	}
	
	public ArrayList<Individu> selectionParRang() {
		ArrayList<Individu> parentsRestants = new ArrayList<Individu>();
		this.triABulles();
		for (int i = 0 ; i < Villes.nbVilles/2 ; i++) {
			parentsRestants.add(this.population.get(i));
		}
		return parentsRestants;
	}
	
	public ArrayList<Individu> selectionParRoulette(int nbrRestants) {
        ArrayList<Individu> parentsRestants = new ArrayList<Individu>();
        Random r = new Random();

        double[] proba = new double[this.population.size()];
        proba[0] = this.chanceSelection(this.population.get(0));
        for (int i = 1 ; i < this.population.size() ; i++) {
            proba[i] = this.chanceSelection(this.population.get(i)) + proba[i-1];
        }

        for (int j = 0 ; j < nbrRestants ; j++) {
            double individuSelect = r.nextDouble();
            //System.out.println(individuSelect);
            int curseur = 0;
            while (individuSelect > proba[curseur]) {
                curseur++;
            }
            boolean dejaSelect = true;
            while (dejaSelect) {
                //System.out.println(dejaSelect);
                //System.out.println(individuSelect);
                dejaSelect = false;
                //System.out.println(dejaSelect);
                curseur = 0;
                while (individuSelect > proba[curseur]) {
                    curseur++;
                }
                for (Individu i : parentsRestants) {
                    if (this.population.get(curseur) == i) {
                        //System.out.println("individu deja select");
                        individuSelect = r.nextDouble();
                        //System.out.println(individuSelect);
                        dejaSelect = true;
                    }
                }
                //System.out.println(dejaSelect + " deuxieme vÃ©rif");
            }
            parentsRestants.add(this.population.get(curseur));
        }
        return parentsRestants;
    }

	
	public ArrayList<Individu> triABulles() {
		Individu tmp = new Individu();
		for (int i = 0 ; i < Villes.nbVilles ; i++) {
			for (int j = 0 ; j < Villes.nbVilles ; j++) {
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
			somme += i.fonctionEvaluation();
		}
		return somme/Villes.nbVilles;
	}
	
	public LinkedList<Individu> croisement(Individu P1, Individu P2) {
		LinkedList<Individu> progeniture = new LinkedList<>();
		Random r = new Random();
		int pointDecoupe1 = r.nextInt(Villes.nbVilles-3) + 1; // entre 1 et nbVilles-1
		int pointDecoupe2 = r.nextInt(Villes.nbVilles-2) + 1;
		while (pointDecoupe1 >= pointDecoupe2 ) {
			pointDecoupe2 = r.nextInt(Villes.nbVilles-2) + 1;
		}
		
		// creation des enfants F1 et F2
		Individu F1 = P1;
		Individu F2 = P2;
		
		//System.out.println("pDécoupe1 : " + pointDecoupe1 + " pDécoupe2 : " + pointDecoupe2);
		for (int i = pointDecoupe1 ; i <= pointDecoupe2 ; i++) {
			int villeCouranteF1 = F1.getVilles().get(i); 
			int villeCouranteF2 = F2.getVilles().get(i); 
			if (P1.getVilles().contains(villeCouranteF2) 
					&& ((P1.getVilles().indexOf(villeCouranteF2) < pointDecoupe1)
					|| (P1.getVilles().indexOf(villeCouranteF2)) > pointDecoupe2)) {
				F1.getVilles().set(P1.getVilles().indexOf(villeCouranteF2), -1);
			}
			if (P2.getVilles().contains(villeCouranteF1)
					&& ((P2.getVilles().indexOf(villeCouranteF1) < pointDecoupe1)
					|| (P2.getVilles().indexOf(villeCouranteF1)) > pointDecoupe2)) {
				F2.getVilles().set(P2.getVilles().indexOf(villeCouranteF1), -1);
			}

			F1.getVilles().set(i, villeCouranteF2);
			F2.getVilles().set(i, villeCouranteF1);
		}
		// recherche des villes manquantes 
		LinkedList<Integer> villesNonPlaceesF1 = new LinkedList<>();
		LinkedList<Integer> villesNonPlaceesF2 = new LinkedList<>();
		for (int i = 0 ; i < Villes.nbVilles ; i++) {
			if (!F1.getVilles().contains(i))
				villesNonPlaceesF1.add(i);
			if (!F2.getVilles().contains(i))
				villesNonPlaceesF2.add(i);
		}
		this.gererVillesNonPlacees(F1, villesNonPlaceesF1, pointDecoupe1, pointDecoupe2);
		this.gererVillesNonPlacees(F2, villesNonPlaceesF2, pointDecoupe1, pointDecoupe2);
		
		progeniture.add(F1);
		progeniture.add(F2);
		
		return progeniture;
	}
	
	public void gererVillesNonPlacees(Individu ind, LinkedList<Integer> l, int pointDecoupe1, int pointDecoupe2) {
		Random r = new Random();
		for (int i = 0 ; i < ind.getVilles().size() ; i ++) {
			if (i == pointDecoupe1) {
				i = pointDecoupe2;
			}
			if (ind.getVilles().get(i) == -1) {
				int indexAleatoire = r.nextInt(l.size());
				ind.getVilles().set(i, l.get(indexAleatoire));
				l.remove(indexAleatoire);
			}
		}
	}
	
	public double chanceSelection(Individu individu) {
		double total = 0;
		for (Individu i : this.population) {
			total += i.getScore();
		}
		double frequenceIndividu = 0;
		double totalFrequence = 0;
		for (Individu j : this.population) {
			frequenceIndividu = total / j.getScore();
			totalFrequence += frequenceIndividu;
		}
		return (total / individu.getScore())/totalFrequence;
	}
	
	public ArrayList<Individu> getPopulation() {
		return this.population;
	}
	
	public void setPopulation(ArrayList<Individu> population) {
		this.population = population;
	}
	
	public String toString() {
		String s = "";
		for (Individu i : this.population) {
			s += i.toString() + " " + i.getScore() + " | " + this.chanceSelection(i) + "\n";
		}
		return s;
	} 	
}
