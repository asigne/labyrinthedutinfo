package Java;

public class IA extends Joueur {

	Coup CoupCourant;
	int newPosLigne, newPosColonne;
	boolean deplacement;
	
	public IA(String nom) {
		super(nom);
	}
	
	
	
	//rejoindre un serveur ou une partie ???
	public void RejoindreServeur(Serveur monServeur){
	
	}
	
	public void RejoindrePartie(Partie maPartie){
		partieActuelle=maPartie;
		maPartie.ajouterJoueur(this);
	}
	//rejoindre un serveur ou une partie ???
	
	public Coup rechercheMeilleurCoup(Partie maPartie, String flecheInterdite, int sensInterdit)
	{
			// methode developpée par le prof
			// genere le meilleur coup
		return null;
	} 	
	
	public void rechercheMeilleurDeplacement(Partie maPartie)
	{
		// methode developpée par le prof
		
		//pour le deplacement, modifier les valeurs de ligne et de  colonne;
		int ligne=0, colonne=0;
		seDeplacer(ligne, colonne);
	} 
	




	public void jouer(Partie maPartie, String flecheInterdite, int sensInterdit) {
		//rechercher le meilleur Coup
		Coup coupIa = rechercheMeilleurCoup(maPartie, flecheInterdite, sensInterdit);
		
		//modifier le plateau
		modifierPlateau(coupIa);
		
		//se deplacer
		rechercheMeilleurDeplacement(maPartie);
	}
}
