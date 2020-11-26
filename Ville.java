
public class Ville implements Cloneable {

	double posX, posY;
	int numVille;
	
	// instancie une ville composee de ses coordonnees x et y ainsi que son numero de ville
	public Ville(int numVille, double posX, double posY) {
		this.numVille = numVille;
		this.posX = posX;
		this.posY = posY;
	}
	
	// calcule la distance entre deux villes v1 et v2
	public static double distanceEntrePoints(Ville v1, Ville v2) {
		return Math.sqrt(Math.pow((v2.getPosX()-v1.getPosX()), 2) + Math.pow((v2.getPosY()-v1.getPosY()), 2));
	}
	
	public int getNumVille() {
		return this.numVille;
	}
	
	public double getPosX() {
		return this.posX;
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return this.posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	@Override
	public String toString() {
		String retour = "";
		if (this.posX == -1.0 && this.posY == -1.0) {
			retour += "" +-1;
		} else {
			retour += this.numVille;
		}
		return retour;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (numVille != other.numVille)
			return false;
		return true;
	}
	

}
