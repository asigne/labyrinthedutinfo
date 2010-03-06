package Java;

/*import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.ObjectOutputStream;*/
import java.io.Serializable;
import java.util.ArrayList;

public class Partie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int nbCarte = 24; // nb de cartes a distribuer aux joueurs
	private String nom; // nom de la partie
	Plateau monPlateau; // plateau de la partie
	Case caseCourante; // caseCourante de la partie
	ArrayList<Carte> ListCarte; // liste des cartes � distribuer
	ArrayList<Joueur> ListJoueur; // liste des joueurs de la partie
	boolean partieFinie; // si true partie finie sinon false
	String regle = "";
	String difficulte = "";
	Joueur joueurActif;

	public Joueur getJoueurActif() {
		return joueurActif;
	}

	public void setJoueurActif(Joueur joueurActif) {
		this.joueurActif = joueurActif;
	}

	public Partie(String nom, String regle, String difficulte) {
		this.setNom(nom);
		partieFinie = false;
		ListCarte = new ArrayList<Carte>();
		ListJoueur = new ArrayList<Joueur>();
		this.regle = regle;
		this.difficulte = difficulte;

		// creation du plateau de maniere aleatoire
		monPlateau = new Plateau();

		// d�termination de la caseCourante
		caseCourante = monPlateau.ListCase.get(0); // derniere case non plac�e
		// sur le plateau

		// creation des cartes � distribuer
		for (int i = 0; i < nbCarte; i++) {
			ListCarte.add(new Carte(i));
		}
	}

	public boolean getPartieFinie() {
		return partieFinie;
	}

	public void setPartieFinie(boolean partieFinie) {
		this.partieFinie = partieFinie;
	}

	// ajout d'un joueur
	public void ajouterJoueur(Joueur newJoueur) {
		int numJoueur = ListJoueur.size();
		ListJoueur.add(newJoueur);
		Joueur joueurCourant = ListJoueur.get(numJoueur);

		joueurCourant.setIdentifiant(numJoueur);
		switch (numJoueur) {
		case 0:
			joueurCourant.setPosition(0, 0);
			break;
		case 1:
			joueurCourant.setPosition(6, 6);
			break;
		case 2:
			joueurCourant.setPosition(0, 6);
			break;
		case 3:
			joueurCourant.setPosition(6, 0);
			break;
		}
	}

	// retourne la liste des joueurs de la partie
	public ArrayList<Joueur> getListJoueur() {
		return ListJoueur;
	}

	public Joueur joueurSuivant(Joueur joueurActif) {
		int indiceJoueurCourant;
		indiceJoueurCourant = ListJoueur.indexOf(joueurActif);
		if (indiceJoueurCourant == ListJoueur.size() - 1) {
			return ListJoueur.get(0);
		} else {
			return ListJoueur.get(indiceJoueurCourant + 1);
		}
	}

	// suppression d'un joueur
	public void supprJoueur(Joueur oldJoueur) {
		ListJoueur.remove(oldJoueur);
	}

	// renvoie le plateau de la partie
	public Plateau getMonPlateau() {
		return monPlateau;
	}

	public void setMonPlateau(Plateau monPlateau) {
		this.monPlateau = monPlateau;
	}

	// renvoie la case courante de la partie
	public Case getCaseCourante() {
		return caseCourante;
	}

	// modifie le plateau
	public void modifierPlateau(Coup monCoup) {
		String sens = monCoup.sens;
		if (sens.equals("haut") || sens.equals("bas")) {
			caseCourante = monPlateau.modifierColonne(monCoup);
		} else {
			caseCourante = monPlateau.modifierLigne(monCoup);
		}

	}

	public void setCaseCourante(Case caseCourante) {
		this.caseCourante = caseCourante;
	}

	// lancer la partie
	public void lancerPartie() {
		if (ListJoueur.size() < 2) {
			// pas assez de joueurs;
		} else {
			distribuerCarte();
		}
	}

	// distribution des cartes aux joueurs
	public void distribuerCarte() {
		int numJoueur = 0;
		int carteAleatoire;
		Carte carteActuelle;
		while (!ListCarte.isEmpty()) // tant qu'il reste des cartes a distribuer
		{
			carteAleatoire = (int) (Math.random() * ListCarte.size());
			carteActuelle = ListCarte.get(carteAleatoire); // choix d'une carte
			// au hasard
			ListJoueur.get(numJoueur).ajouterCarte(carteActuelle); // distribution
			// au joueur
			// actuel
			// si dernier joueur atteind, la prochain joueur sera le premier de
			// la liste
			if (numJoueur == ListJoueur.size() - 1) {
				numJoueur = 0;
			} else // sinon on passe au joueur suivant
			{
				numJoueur++;
			}
			ListCarte.remove(carteAleatoire); // suppression de la carte
			// courante de la liste
		}

		/*
		 * for(int i=0; i<ListJoueur.size();i++) {
		 * ListJoueur.get(i).ajouterCarte(new
		 * Carte(12+ListJoueur.get(i).getIdentifiant()));
		 * //ListJoueur.get(i).ajouterCarte(new
		 * Carte(24+ListJoueur.get(i).getIdentifiant()));
		 * //ListJoueur.get(i).setCarteObjectif
		 * (ListJoueur.get(i).getListCarte().get(0)); }
		 */
	}

	public String getDifficulte() {
		return difficulte;
	}

	public String getRegle() {
		return regle;
	}

	public boolean partieEnfant() {
		if (this.regle.equals("Enfant")) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * public int sauv() { try {
	 * 
	 * // ouverture d'un flux de sortie vers le fichier "save.ser"
	 * FileOutputStream fos = openFileOutput("save.dat",2);
	 * 
	 * // création d'un "flux objet" avec le flux fichier ObjectOutputStream
	 * oos= new ObjectOutputStream(fos); // sérialisation : écriture de
	 * l'objet dans le flux de sortie oos.writeObject(this); // on vide le
	 * tampon oos.flush();
	 * 
	 * } catch (FileNotFoundException e) { return 0; } catch (IOException e) {
	 * return 1; } return 2; }
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	
	
	// deplacement des joueurs sur les case mobiles en mouvement
	public void traitementJoueurSurCaseMobile(int indice, String fleche) {
		ArrayList<Joueur> listEnCoursDeTest = null;
		Joueur JCT; // joueur en Cours de Traitement

		if (fleche.equals("haut")) {
			for (int ligne = 6; ligne >= 0; ligne--) {
				listEnCoursDeTest = monPlateau.getCase(ligne, indice).getListJoueur();
				for (int i = 0; i < listEnCoursDeTest.size(); i++) {
					JCT = listEnCoursDeTest.get(i);
					JCT.modifPosition(ligne, indice);
				}
			}
		} else if (fleche.equals("bas")) {
			for (int ligne = 0; ligne <= 6; ligne++) {
				listEnCoursDeTest = monPlateau.getCase(ligne, indice).getListJoueur();
				for (int i = 0; i < listEnCoursDeTest.size(); i++) {
					JCT = listEnCoursDeTest.get(i);
					JCT.modifPosition(ligne, indice);
				}
			}
		} else if (fleche.equals("gauche")) {
			for (int colonne = 6; colonne >= 0; colonne--) {
				listEnCoursDeTest = monPlateau.getCase(indice, colonne).getListJoueur();
				for (int i = 0; i < listEnCoursDeTest.size(); i++) {
					JCT = listEnCoursDeTest.get(i);
					JCT.modifPosition(indice, colonne);
				}
			}
		} else if (fleche.equals("droite")) {
			for (int colonne = 0; colonne <= 6; colonne++) {
				listEnCoursDeTest = monPlateau.getCase(indice, colonne)
				.getListJoueur();
				for (int i = 0; i < listEnCoursDeTest.size(); i++) {
					JCT = listEnCoursDeTest.get(i);
					JCT.modifPosition(indice, colonne);
				}
			}
		}
	}

}
