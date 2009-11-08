package Java;

import java.util.ArrayList;

public abstract class Joueur {
	String nom;
	Pion pion;
	Partie partieActuelle;
	ArrayList<Carte> ListCarte;
	int posLigne;
	int posColonne;
	
	public Joueur(String nom){
		this.nom=nom;
		this.pion=null; 		//pour le pion : a revoir
		ListCarte = new ArrayList<Carte>();
		partieActuelle=null;
	}
	
	public Pion getPion() {
		return pion;
	}

	public void setPion(Pion pion) {
		this.pion = pion;
	}	

	public void seDeplacer(int ligne, int colonne)
	{
		partieActuelle.getMonPlateau().getCase(posLigne, posColonne).supprPion(this.pion);		
		posLigne=ligne;
		posColonne=colonne;
		partieActuelle.getMonPlateau().getCase(ligne, colonne).ajouterPion(this.pion);		
	}
	
	public void creerServeur(String nomServeur)
	{
		//à etudier 
		Serveur monServeur= new Serveur(nomServeur);
	}
	
	public void scannerServeur()
	{
		// à etudier
	}
		
	//rejoindre un serveur ou une partie ???
	public void RejoindreServeur(Serveur monServeur){
	
	}
	
	public void RejoindrePartie(Partie maPartie){
		maPartie.ajouterJoueur(this);
		partieActuelle=maPartie;
	}
	
	//rejoindre un serveur ou une partie ???
		
	public boolean jouer(Coup monCoup)
	{
		partieActuelle.modifierPlateau(monCoup);
		return true;
	}
	
	public void ajouterCarte(Carte newCarte)
	{
		ListCarte.add(newCarte);
	}
	
	public void supprCarte(Carte oldCarte)
	{
		ListCarte.remove(oldCarte);
	}
		
	public ArrayList<Carte> getListCarte() {
		return ListCarte;
	}
	
	public Carte getCarteCourante()
		{
			return ListCarte.get(0);
		}
	
	public int getPosLigne() {
		return posLigne;
	}

	public void setPosLigne(int posLigne) {
		this.posLigne = posLigne;
	}

	public int getPosColonne() {
		return posColonne;
	}

	public void setPosColonne(int posColonne) {
		this.posColonne = posColonne;
	}
}
