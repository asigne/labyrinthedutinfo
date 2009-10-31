package Java;

public class IA extends Joueur {

	public IA(String nom) {
		super(nom);
	}

	//rejoindre un serveur ou une partie ???
	public void RejoindreServeur(Serveur monServeur){
	
	}
	
	public void RejoindrePartie(Partie maPartie){
		maPartie.ajouterJoueur(this);
		partieActuelle=maPartie;
	}
	//rejoindre un serveur ou une partie ???
	
	public Coup rechercheMeilleurCoup(Case caseCourante, Plateau monPlateau)
	{
		// methode developper par le prof
		Coup monCoup = null;
		return monCoup;
	}
 
	
}
