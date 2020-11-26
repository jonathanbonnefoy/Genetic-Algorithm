import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Main {

	public static void main(String[] args) {
		try   
		{  
		//declare file name to be create   
		String filename = "C:\\Users\\jonat\\Desktop\\AlgoGenetique.xls";  
		HSSFWorkbook workbook = new HSSFWorkbook();  
			HSSFSheet sheet = workbook.createSheet("defi250");    
			HSSFRow row = sheet.createRow((short)0);  
			row.createCell(0).setCellValue("NumVille");  
			row.createCell(1).setCellValue("posX");  
			row.createCell(2).setCellValue("posY");  
			row.createCell(4).setCellValue("Score best ind");  
			row.createCell(6).setCellValue("Score moy pop");
			row.createCell(8).setCellValue("chemin best ind");  
			row.createCell(10).setCellValue("temps exec");
	 
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
			
			long debut = System.currentTimeMillis();
			
			for (int param=1 ; param <= nbIterations+20 ; param++) {
				row = sheet.createRow(param);
			}
			Villes v = new Villes(nbVilles);
			Population p = new Population();
			double moy = p.moyenneFitness();
			System.out.println("----------------------------------------------------------");
			System.out.println("Population numero 0 :");
			System.out.println(p);
			
			// listing de toutes les informations de chaque villes 
			for (int i = 1 ; i <= nbVilles ; i ++) {
				row = sheet.createRow(i);  
				//insertion des données dans chaque colonne
				row.createCell(0).setCellValue(Villes.villesOrigine.get(i-1).numVille);  
				row.createCell(1).setCellValue(Villes.villesOrigine.get(i-1).posX);  
				row.createCell(2).setCellValue(Villes.villesOrigine.get(i-1).posY);	
			}
	
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
					// voir si le dans while on modifie pas le score moyen ?
					Individu P1 = p.selectionParRoulette(1).get(0);
					Individu P2 = p.selectionParRoulette(1).get(0);
					LinkedList<Individu> progeniturePotentielle = p.croisement(P1, P2);
					Individu F1 = progeniturePotentielle.getFirst();
					Individu F2 = progeniturePotentielle.getLast();
				//	System.out.println("Moyenne generation actuelle :" + p.moyenneFitness());
				//	System.out.println("generation numero : " + i);
				//  System.out.println("F1 score avant mutation " + F1.getScore());
				//	System.out.println("F2 score avant mutation " + F2.getScore());
					System.out.println(".");
					Random r = new Random();
					if (r.nextInt(101) < pourcentageMutation) {
						F1.mutation(); // mutation du premier fils
						//System.out.println("MUTATION F1 " + F1.getScore());
						
					}
					if (r.nextInt(101) < pourcentageMutation) {
						F2.mutation(); // mutation du second fils
						//System.out.println("mutation F2 " + F2.getScore());
					}
					// si l'enfant 1 est bien classe
					//System.out.println("Moyenne" + moyenneGen);
					if (F1.getScore() <= moyenneGen) {
						progenitureAcceptee.add(F1);
					//	System.out.println("F1 ajoute" + progenitureAcceptee);
					}
					// si l'enfant 2 est bien classe
					if (F2.getScore() <= moyenneGen) {
						progenitureAcceptee.add(F2);
					//	System.out.println("F2 ajoute" + progenitureAcceptee);
					}
					//System.out.println("moyenne initiale :" + moy);
				}
				if (progenitureAcceptee.size() > nbVilles/2) {
					progenitureAcceptee.removeLast();
				}
				// insertion des enfants bien classes dans la population
				for (Individu individu : progenitureAcceptee) {
					p.getPopulation().add(individu);
				}
				progenitureAcceptee.clear();

				row = sheet.getRow(i);  
				row.createCell(4).setCellValue(p.getMeilleurIndividu().getScore());
				row.createCell(6).setCellValue(p.moyenneFitness());
				
				//System.out.println("----------------------------------------------------------");
				System.out.println("Population numero " + (i) + " :");
				//System.out.println(p);
				//System.out.println("MOYENNE FIN " + moyenneGen);
				
			}
			System.out.println("MOYENNE DEBUT " + moy);
			
			double tempsMili = (System.currentTimeMillis()-debut);
			double tempsSecondes = tempsMili/1000;
			row = sheet.getRow(1);  
			row.createCell(10).setCellValue(tempsSecondes);
	
			for (int d = 0 ; d < nbVilles ; d++) {
				row = sheet.getRow(d+1); 
				row.createCell(8).setCellValue(p.getMeilleurIndividu().getVilles().get(d).getNumVille());
			}
			
			System.out.println("l'execution a dure : " + tempsSecondes + " secondes");
			//Affiche la durée d'exécution en millisecondes
			System.out.println("Recapitulatif :\nNombre de villes (et d'individus) : " + nbVilles + "\nCeci est la " + nbIterations 
					+ "eme generation\n" + pourcentageMutation + "% de chance qu'un individu mute apres un croisement");
			System.out.println("Le meilleur chemin de la premiere generation a pour score : " + minDebut.getScore());
			System.out.println("Le meilleur chemin de la derniere generation a pour score : " + p.getMeilleurIndividu().getScore() + "\nVoici le trajet a suivre : " + p.getMeilleurIndividu());
			
			
			FileOutputStream fileOut = new FileOutputStream(filename);  
			workbook.write(fileOut); 
			//closing the Stream  
			fileOut.close();  
			//closing the workbook  
			workbook.close();  
			//prints the message on the console  
			System.out.println("Excel file has been generated successfully.");  
			} 
		catch (Exception e) {  
			e.printStackTrace();  
		} 
	}
}
