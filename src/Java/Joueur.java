package Java;

public abstract class Joueur {
	String nom;
	Pion pion;
	Partie partieActuelle;
	
	public Joueur(String nom){
		this.nom=nom;
		//pour le pion : a revoir
		pion = new Pion("couleur");
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
}
