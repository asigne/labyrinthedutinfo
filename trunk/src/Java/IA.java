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
	
	public Coup rechercheMeilleurCoup(Case caseCourante, Plateau monPlateau)
	{
		deplacement=false;
		Coup monCoup = null;
		// methode developper par le prof
		//pour le deplacement, modifier les valeurs de newPositionLigne, newPositionColonne;
		//le booleen 'deplacement est vrai si le joueur veut se deplacer;
		return monCoup;
	} 	
	
	public void seDeplacer()
	{
		if(deplacement)
			{
				super.seDeplacer(newPosLigne, newPosColonne);
			}
	}
}
