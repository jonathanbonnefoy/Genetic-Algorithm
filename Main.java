
public class Main {

	public static void main(String[] args) {
		
		Villes v = new Villes(5);
	
		Population p = new Population();
		
		System.out.println("\n" + p);
	
	    System.out.println("Parent 1 : " + p.getPopulation().get(1));
	    System.out.println("Parent 2 : " + p.getPopulation().get(2));
	    p.croisement(p.getPopulation().get(1), p.getPopulation().get(2));
	    System.out.println("Enfant 1 : " + p.getPopulation().get(8));
	    System.out.println("Enfant 2 : " + p.getPopulation().get(9));
	}

}

