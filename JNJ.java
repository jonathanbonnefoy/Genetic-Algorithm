import static org.junit.Assert.*;
import org.junit.Test;

public class JNJ {
		
		@Test
		public void test() {
			Villes v = new Villes(5);
			Population p = new Population();
			System.out.println("av mutation" + p.getPopulation().get(1));
			System.out.println("score av " + p.getPopulation().get(1).getScore());
			p.getPopulation().get(1).mutation();
			System.out.println("ap mutation" + p.getPopulation().get(1));
			System.out.println("score ap " + p.getPopulation().get(1).getScore());
			Individu mut = p.getPopulation().get(1);
			mut.mutation();
			assertEquals(mut,p.getPopulation().get(1));
		}

}

