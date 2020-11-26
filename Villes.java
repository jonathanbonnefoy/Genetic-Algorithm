import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Villes {
	
	public static LinkedList<Ville> villesOrigine;
	public static int nbVilles;

	// constructeur pour generer des villes aléatoirements
	public Villes(int nombreVilles) {
		villesOrigine = new LinkedList<Ville>();
		Random r = new Random();
		for (int i = 0 ; i < nombreVilles ; i++) {
			villesOrigine.add(new Ville(i,r.nextDouble(),r.nextDouble()));
		}
		nbVilles = nombreVilles;
	}
	
	// constructeur pour le defi des 250 villes
	/*public Villes(int nombreVilles) throws FileNotFoundException, IOException {
		villesOrigine = new LinkedList<Ville>();
		File file = new File("C:\\Users\\jonat\\Desktop\\defi250.xlsx");  
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheetAt(0);    
		XSSFRow row = sheet.getRow(1);
		for (int i = 1 ; i <= 250 ; i++) {
			row = sheet.getRow(i);
			String posX = row.getCell(0).getStringCellValue();
			Double posXD = Double.parseDouble(posX);
			String posY = row.getCell(1).getStringCellValue();
			Double posYD = Double.parseDouble(posY);
			villesOrigine.add(new Ville(i,posXD,posYD));
		}	
			
		//villesOrigine.add(new Ville(i,r.nextDouble(),r.nextDouble()));
		nbVilles = nombreVilles;
		wb.close();
	}
	*/

}
