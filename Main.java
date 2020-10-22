
public class Main {

	public static void main(String[] args) {
		
		Villes v = new Villes(5);
		Population p = new Population();
		Individu i = new Individu();
		System.out.println(i.getGraphe());
	//	System.out.println(p.selection());
		System.out.println(p);
		p.triABulles();
		System.out.println(p);
		
	}

}
