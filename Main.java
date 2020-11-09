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
		System.out.println("Entrez le pourcentage a depasser afin de que la mutation se produise :");
		float pourcetageMutation = sc.nextFloat();
		
		Population p = new Population();
		System.out.println(p);
		
		for (int i = 0 ; i < nbIterations ; i++) {
			p.triABulles();
		}
		
		/**Pour nombre d’iterations
			Parent A = S´election d’un Individu (Groupe)
			Parent B = S´election d’un Individu (Groupe)
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
