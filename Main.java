
public class Main {

	public static void main(String[] args) {
		
		Villes v = new Villes(5);
		Population p = new Population();
		Individu i = new Individu();
		//System.out.println(i.getGraphe());
		//System.out.println(p);
		//p.triABulles();
		//System.out.println(p);
		
		System.out.println("Parent 1 : " + p.getPopulation().get(1));
		System.out.println("Parent 2 : " + p.getPopulation().get(2));
		p.croisement(p.getPopulation().get(1), p.getPopulation().get(2));
		System.out.println("Enfant 1 : " + p.getPopulation().get(8));
		System.out.println("Enfant 2 : " + p.getPopulation().get(9));
		//System.out.println(p);
	}

}
