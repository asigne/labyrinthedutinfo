package Java;

import java.util.ArrayList;

public abstract class Joueur {
	String nom;
	Pion pion;
	Partie partieActuelle;
	ArrayList<Carte> ListCarte;
	
	public Joueur(String nom){
		this.nom=nom;
		//pour le pion : a revoir
		pion = new Pion("couleur");
		ListCarte = new ArrayList<Carte>();
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
	
	public void supprPion(Carte oldCarte)
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
}
