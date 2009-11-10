package Java;

import java.util.ArrayList;

public abstract class Joueur {
	int identifiant;
	String nom;
	//Pion pion;
	Partie partieActuelle;
	ArrayList<Carte> ListCarte;
	int posLigne;
	int posColonne;
	
	public Joueur(String nom){
		this.identifiant=0;
		this.nom=nom;
		//this.pion=null; 		//pour le pion : a revoir
		ListCarte = new ArrayList<Carte>();
		partieActuelle=null;
	}	
	
	public void seDeplacer(int ligne, int colonne)
	{
		partieActuelle.getMonPlateau().getCase(posLigne, posColonne).supprJoueur(this);		
		partieActuelle.getMonPlateau().getCase(ligne, colonne).ajouterJoueur(this);	
		posLigne=ligne;
		posColonne=colonne;			
	}
	
	public void seDeplacer1(int ligne, int colonne)
	{
		//partieActuelle.getMonPlateau().getCase(posLigne, posColonne).supprJoueur(this);		
		//partieActuelle.getMonPlateau().getCase(ligne, colonne).ajouterJoueur(this);	
		posLigne=ligne;
		posColonne=colonne;			
	}
	
	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
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
		partieActuelle=maPartie;
		maPartie.ajouterJoueur(this);
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

	public String getNom() {
		return nom;
	}

	public int getPosColonne() {
		return posColonne;
	}

	public void setPosition(int posLigne, int posColonne) {
		
		this.posLigne = posLigne;
		this.posColonne = posColonne;
		partieActuelle.getMonPlateau().getCase(posLigne, posColonne).ajouterJoueur(this);
	}
}
