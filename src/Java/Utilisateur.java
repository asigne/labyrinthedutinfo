package Java;

public class Utilisateur extends Joueur {

	public Utilisateur(String nom) {
		super(nom);
		
	}
		
	public void scannerServeur()
	{
		
	}
	
	//rejoindre un serveur ou une partie ???
	public void RejoindreServeur(Serveur monServeur){
	
	}
	
	public void RejoindrePartie(Partie maPartie){
		partieActuelle=maPartie;
		maPartie.ajouterJoueur(this);
	}
	//rejoindre un serveur ou une partie ???
	
	
	public Coup genererCoup(Case caseCourante, int modif, String sens)
	{
		Coup monCoup = new Coup(caseCourante, modif, sens);
		return monCoup;
	}
}
