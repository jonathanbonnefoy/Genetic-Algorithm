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
		while (pointDecoupe1 >= pointDecoupe2) {
			pointDecoupe2 = r.nextInt(Villes.nbVilles-2) + 1;
		}
		
		// creation des enfants F1 et F2
		Individu F1 = new Individu();
		F1.getVilles().clear();
		for (Ville v: P1.getVilles()) {
			try {
				F1.getVilles().add((Ville) v.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		Individu F2 = new Individu();
		F2.getVilles().clear();
		for (Ville v: P2.getVilles()) {
			try {
				F2.getVilles().add((Ville) v.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	
		//System.out.println("P1 : " + P1);
		//System.out.println("P2 : " + P2);
		//System.out.println("pDécoupe1 : " + pointDecoupe1 + " pDécoupe2 : " + pointDecoupe2);
		for (int i = pointDecoupe1 ; i <= pointDecoupe2 ; i++) {
			Ville villeCouranteF1 = F1.getVilles().get(i); 
			Ville villeCouranteF2 = F2.getVilles().get(i); 
			//System.out.println("villeCF2 : " + villeCouranteF2);
			if (P1.getVilles().indexOf(villeCouranteF2) < pointDecoupe1 || (P1.getVilles().indexOf(villeCouranteF2)) > pointDecoupe2) {
				//System.out.println("-1 pour F1 à i");
				F1.getVilles().get(P1.getVilles().indexOf(villeCouranteF2)).setPosX(-1.0);
				F1.getVilles().get(P1.getVilles().indexOf(villeCouranteF2)).setPosY(-1.0);
				//System.out.println(F1);
			}
			//System.out.println("villeCF1 : " + villeCouranteF1);
			if (P2.getVilles().indexOf(villeCouranteF1) < pointDecoupe1 || (P2.getVilles().indexOf(villeCouranteF1) > pointDecoupe2)) {
				//System.out.println("-1 pour F2 à i");
				F2.getVilles().get(P2.getVilles().indexOf(villeCouranteF1)).setPosX(-1.0);
				F2.getVilles().get(P2.getVilles().indexOf(villeCouranteF1)).setPosY(-1.0);
			}
			
			//System.out.println("F1 av switch:" + F1);
			//System.out.println("F2 av switch:" + F2);
			F1.getVilles().set(i, villeCouranteF2);
			F2.getVilles().set(i, villeCouranteF1);
			//System.out.println("F1 ap switch:" + F1);
			//System.out.println("F2 ap switch:" + F2);
		}
		// recherche des villes manquantes 
		LinkedList<Ville> villesNonPlaceesF1 = new LinkedList<>();
		LinkedList<Ville> villesNonPlaceesF2 = new LinkedList<>();
		for (int i = 0 ; i < Villes.nbVilles ; i++) {
			//System.out.println("av contains " + P1);
			if (!F1.getVilles().contains(P1.getVilles().get(i)))
				villesNonPlaceesF1.add(P1.getVilles().get(i));
			//System.out.println("av contains " + P2);
			if (!F2.getVilles().contains(P1.getVilles().get(i)))
				villesNonPlaceesF2.add(P1.getVilles().get(i));
		}
		//System.out.println("villenonplaceeF1" + villesNonPlaceesF1);
		//System.out.println("villenonplaceeF2" + villesNonPlaceesF2);
		this.gererVillesNonPlacees(F1, villesNonPlaceesF1, pointDecoupe1, pointDecoupe2);
	    //System.out.println("deuxieme gerervillernonplaceee");
		this.gererVillesNonPlacees(F2, villesNonPlaceesF2, pointDecoupe1, pointDecoupe2);
		//System.out.println("gererVillesNonPlaccesF1" + F1);
		//System.out.println("gererVillesNonPlaccesF2" + F2);
		progeniture.add(F1);
		progeniture.add(F2);
		
		return progeniture;
	}
	
	public void gererVillesNonPlacees(Individu ind, LinkedList<Ville> l, int pointDecoupe1, int pointDecoupe2) {
		Random r = new Random();
		for (int i = 0 ; i < ind.getVilles().size() ; i ++) {
			if (i == pointDecoupe1) {
				i = pointDecoupe2;
			}
			if (ind.getVilles().get(i).getPosX() == -1.0 && ind.getVilles().get(i).getPosY() == -1.0) {
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
	
	public Individu getMeilleurIndividu() {
		Individu min = this.getPopulation().get(0);
		for (int i = 1 ; i < Villes.nbVilles ; i++) {
			if (min.getScore() > this.getPopulation().get(i).getScore()) {
				min = this.getPopulation().get(i);
			}	
		}
		return min;
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
