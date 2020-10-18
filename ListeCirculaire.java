import java.util.ArrayList;

public class ListeCirculaire<T>  {

	private int compteur;
	private ArrayList<T> listeCirculaire;
	
	/**
	 * Instancie une liste circulaire
	 */
	public ListeCirculaire() {
		this.listeCirculaire = new ArrayList<T>();
		this.compteur = 0;
	}

	/**
	 * Ajoute un element a la liste circulaire
	 * @param element de type generique a rajouter
	 */
	public void ajouter(T t) {
		this.listeCirculaire.add(t);
		this.compteur++;
	}
	
	/**
	 * @return le premier element
	 * @throws ListeVide
	 */
	public T premier() throws ListeVide {
		if (this.listeCirculaire.isEmpty()) {
			throw new ListeVide("Erreur : la liste est vide");
		}
		return this.listeCirculaire.get(0);
	}
	
	/**
	 * @return l'element suivant de la liste circulaire
	 * @throws ListeVide lorsque la liste circulaire est vide
	 */
	public T suivant() throws ListeVide {
		if (this.listeCirculaire.isEmpty()) {
			throw new ListeVide("Erreur : la liste est vide");
		}
		//this.compteur = (this.compteur++)%this.listeCirculaire.size();
		return this.listeCirculaire.get((this.getCompteur()+1)%this.listeCirculaire.size());
	}

	public ArrayList<T> getListeCirculaire() {
		return this.listeCirculaire;
	}
	
	public int getCompteur() {
		return this.compteur;
	}
	
	public void setCompteur(int compteur) {
		this.compteur = compteur%this.listeCirculaire.size();
	}

	public void avancerCompteur() {
		this.compteur = (this.compteur+1)%this.listeCirculaire.size();
	}

	public T getElement(int i) {
		return this.listeCirculaire.get(i);
	}
	
	public int size() {
		return this.listeCirculaire.size();
	}
	
	public void changerElement(T valeur, int index) {
		this.compteur = index;
		this.listeCirculaire.set(index,valeur);
	}

	public boolean rechercherOccurences(T element) {
		boolean trouver = false;
		for (int i = 0 ; i < this.listeCirculaire.size() ; i++) {
			if (this.listeCirculaire.get(i) == element) {
				trouver = true;
			}
		}
		return trouver;
	}
	
	@Override
	public String toString() {
		return "" + this.listeCirculaire;
	}
	
}