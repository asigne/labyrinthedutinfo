package Java;

import java.util.ArrayList;

public class Jeu {
	ArrayList<Carte> ListCarte; // liste de cartes contenues dans le jeu
	Carte carteObjectif;

	public Jeu() {
		// effecuter la distribution des cartes entre les différents joueurs
	}

	public void ajouterCarte(Carte newCarte) {
		ListCarte.add(newCarte);
	}

	public void supprCarte(Carte oldCarte) {
		ListCarte.remove(oldCarte);
	}
}
