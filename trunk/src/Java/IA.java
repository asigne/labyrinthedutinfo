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
	
	public Coup rechercheMeilleurCoup(Partie maPartie)
	{
			// methode developpée par le prof
			// genere le meilleur coup
		return null;
	} 	
	
	public boolean rechercheMeilleurDeplacement(Partie maPartie)
	{
		// methode developpée par le prof
		//pour le deplacement, modifier les valeurs de newPositionLigne, newPositionColonne;
		//le booleen 'deplacement est vrai si le joueur veut se deplacer;
		return false;
	} 
	
	
		



	public void jouer(Partie maPartie) {
		Coup coupIa = rechercheMeilleurCoup(maPartie);
		//modifier le plateau
		
		modifierPlateau(coupIa);
		//se deplacer
		//seDeplacer(maPartie);
	}
}
