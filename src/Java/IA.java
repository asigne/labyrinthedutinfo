package Java;

public class IA extends Joueur {

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
	
	public Coup rechercheMeilleurCoup(Case caseCourante, Plateau monPlateau)
	{
		// methode developper par le prof
		Coup monCoup = null;
		return monCoup;
	}
 
	
}
