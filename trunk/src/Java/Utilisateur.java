package Java;

public class Utilisateur extends Joueur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Utilisateur(String nom) {
		super(nom);
	}

	public void creerServeur(String nomServeur) {
		// � etudier
		// Serveur monServeur= new Serveur(nomServeur);
	}

	public void scannerServeur() {
		// � etudier
	}

	// rejoindre un serveur ou une partie ???
	public void RejoindreServeur(Serveur monServeur) {

	}

	@Override
	public void RejoindrePartie(Partie maPartie) {
		partieActuelle = maPartie;
		maPartie.ajouterJoueur(this);
	}

	// rejoindre un serveur ou une partie ???

	public Coup genererCoup(Case caseCourante, int modif, String sens) {
		Coup monCoup = new Coup(caseCourante, modif, sens);
		return monCoup;
	}
}
