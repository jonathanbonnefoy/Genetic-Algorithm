import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Parametres
		System.out.println("ALGORITHME GENETIQUE APPLIQUE AU PROBLEME DU VOYAGEUR DE COMMERCE");
		System.out.println("----------------------------------------------------------------- \n");
		System.out.println("Entrez le nombre de ville que le voyageur de commerce doit parcourir :");
		int nbVilles = sc.nextInt();
		System.out.println("Entrez le nombre de generations souhaitees :");
		int nbIterations = sc.nextInt();
		System.out.println("Entrez le pourcentage a depasser afin de que la mutation se produise (entre 0 et 100 inclus):");
		int pourcentageMutation = sc.nextInt();
		
		Villes v = new Villes(nbVilles);
		Population p = new Population();
		double moy = p.moyenneFitness();
		System.out.println("----------------------------------------------------------");
		System.out.println("Population numero 0 :");
		System.out.println(p);
		
		Individu minDebut = p.getPopulation().get(0);
		for (int i = 1 ; i < nbVilles ; i++) {
			if (minDebut.getScore() > p.getPopulation().get(i).getScore()) {
				minDebut = p.getPopulation().get(i);
			}	
		}
		
		for (int i = 1 ; i <= nbIterations ; i++) {
			// moyenne population generation i 
			double moyenneGen = p.moyenneFitness();
			// Selection des individus qui vont pouvoir se reproduire et supression des moins performants
			p.setPopulation(p.selectionParRoulette(p.getPopulation().size()/2)); 
		
			// Selection des parents pour la reproduction
			
			LinkedList<Individu> progenitureAcceptee = new LinkedList<>();
			while (progenitureAcceptee.size() < p.getPopulation().size()) {
				Individu P1 = p.selectionParRoulette(1).get(0);
				Individu P2 = p.selectionParRoulette(1).get(0);
				
				LinkedList<Individu> progeniturePotentielle = p.croisement(P1, P2);
				Individu F1 = progeniturePotentielle.getFirst();
				Individu F2 = progeniturePotentielle.getLast();
				System.out.println("Moyenne generation actuelle :" + p.moyenneFitness());
				System.out.println("F1 score avant mutation " + F1.getScore());
				System.out.println("F2 score avant mutation " + F2.getScore());
				Random r = new Random();
				if (r.nextInt(101) < pourcentageMutation) {
					F1.mutation(); // mutation du premier fils
					System.out.println("MUTATION F1 " + F1.getScore());
					
				}
				if (r.nextInt(101) < pourcentageMutation) {
					F2.mutation(); // mutation du second fils
					System.out.println("mutation F2 " + F2.getScore());
				}
				// si l'enfant 1 est bien classe
				//System.out.println("Moyenne" + moyenneGen);
				if (F1.getScore() <= moyenneGen) {
					progenitureAcceptee.add(F1);
					System.out.println("F1 ajoute" + progenitureAcceptee);
				}
				// si l'enfant 2 est bien classe
				if (F2.getScore() <= moyenneGen) {
					progenitureAcceptee.add(F2);
					System.out.println("F2 ajoute" + progenitureAcceptee);
				}
				System.out.println("moyenne initiale :" + moy);
			}
			if (progenitureAcceptee.size() > nbVilles/2) {
				progenitureAcceptee.removeLast();
			}
			// insertion des enfants bien classes dans la population
			for (Individu individu : progenitureAcceptee) {
				p.getPopulation().add(individu);
			}
			progenitureAcceptee.clear();
			System.out.println("----------------------------------------------------------");
			System.out.println("Population numero " + (i) + " :");
			System.out.println(p);
			System.out.println("MOYENNE FIN " + moyenneGen);
		}
		System.out.println("MOYENNE DEBUT " + moy);

		Individu min = p.getPopulation().get(0);
		for (int i = 1 ; i < nbVilles ; i++) {
			if (min.getScore() > p.getPopulation().get(i).getScore()) {
				min = p.getPopulation().get(i);
			}	
		}
		System.out.println("Recapitulatif :\nNombre de villes (et d'individus) : " + nbVilles + "\nCeci est la " + nbIterations 
				+ "eme generation\n" + pourcentageMutation + "% de chance qu'un individu mute apres un croisement");
		System.out.println("Le meilleur chemin de la premiere generation a pour score : " + minDebut.getScore());
		System.out.println("Le meilleur chemin de la derniere generation a pour score : " + min.getScore() + "\nVoici le trajet a suivre : " + min);
		
	}
}
