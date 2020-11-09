import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Villes v = new Villes(5);
			
		Scanner sc = new Scanner(System.in);
		
		// Parametres
		System.out.println("ALGORITHME GENETIQUE APPLIQUE AU PROBLEME DU VOYAGEUR DE COMMERCE");
		System.out.println("----------------------------------------------------------------- \n");
		System.out.println("Entrez le nombre de ville que le voyageur de commerce doit parcourir :");
		int nbVilles = sc.nextInt();
		System.out.println("Entrez le nombre de generations souhaitees :");
		int nbIterations = sc.nextInt();
		System.out.println("Entrez le pourcentage a depasser afin de que la mutation se produise (entre 0 et 100 inclus):");
		int pourcentageMutation = sc.nextInt(101);
		Population p = new Population();
		System.out.println(p);
		
		
		for (int i = 0 ; i < nbIterations ; i++) {
			// archive du nombre d'individus 
			int nbIndividu = p.getPopulation().size();

			// Selection des individus qui vont pouvoir se reproduire et supression des moins performants
			p.setPopulation(p.selectionParRoulette(p.getPopulation().size()/2)); 
		
			// Selection des parents pour la reproduction
			// PROBLEME : enfants peuvent se reproduire pendant la boucle while --> on veut que les parents qui se reproduisent dans cette phase
			while (p.getPopulation().size() < nbIndividu) {
				Individu P1 = p.selectionParRoulette(1).get(0);
				Individu P2 = p.selectionParRoulette(1).get(0);

				LinkedList<Individu> progeniture = p.croisement(P1, P2);
				Individu F1 = progeniture.getFirst();
				Individu F2 = progeniture.getLast();
				
				Random r = new Random();
				if (r.nextInt(101) > pourcentageMutation) {
					F1.mutation(); // mutation du premier fils
				}
				if (r.nextInt(101) > pourcentageMutation) {
					F2.mutation(); // mutation du second fils
				}
				
				if (F1.getScore() < p.moyenneFitness()) {
					p.getPopulation().add(F1); // ajout du premier fils en fonction de son evaluation
				}
				if (F2.getScore() < p.moyenneFitness()) {
					p.getPopulation().add(F2); // ajout du second fils en fonction de son evaluation
				}
			}
			
		}
		//Initialisation population
		/**Pour nombre d’iterations
			Parent A = Selection d’un Individu (Groupe)
			Parent B = Selection d’un Individu (Groupe)
			Fils = Recombinaison (Parent A, Parent B)
			Si hasard > pourcentage Alors
				Appliquer une mutation `a Fils
			FinSi
			Optimiser Fils // Optionnel
			Evaluer Fils
			Si Fils est accept´e dans Groupe Alors
				R´eins´erer Fils dans Groupe
			FinSi
		FinPour**/

	}
}
