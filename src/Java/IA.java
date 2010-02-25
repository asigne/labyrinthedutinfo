package Java;

import java.util.ArrayList;

public class IA extends Joueur {
		
	int profondeurLimite = 2; 	
	final static  int  MIN = -1;
	final static int MAX = 1 ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Coup CoupCourant;
	int newPosLigne, newPosColonne;
	boolean deplacement;

	public IA(String nom) {
		super(nom);
	}

	public static ArrayList<ObjetIA> methode1(Partie maPartie,
			int indiceInterdit, String flecheInterdite) {
		ArrayList<ObjetIA> ListeObjetIA = new ArrayList<ObjetIA>(); // cree une
		// liste de
		// ObjetIA
		Coup monCoup;
		String fleche = "haut";
		Plateau plateauSauv = maPartie.getMonPlateau().sauvPlateau(); // sauvegarde
		// du
		// plateau
		// Initial
		Case caseSauv = maPartie.getCaseCourante().sauvCase(); // sauvegarde de
		// la
		// caseCourante
		// Initiale

		for (int tour = 0; tour < 4; tour++) // tour est la variable pour
			// effectuer le tour du plateau
			// (haut, droite, bas et gauche)
		{
			switch (tour) {
			case 0:
				fleche = "haut";
				break;
			case 1:
				fleche = "droite";
				break;
			case 2:
				fleche = "bas";
				break;
			case 3:
				fleche = "gauche";
				break;
			}
			for (int i = 1; i <= 5; i = i + 2) // i est la variable pour passer
				// de la colonne 1 � la 3 puis �
				// la 5
			{
				if (fleche != flecheInterdite || i != indiceInterdit) // on test
					// si la
					// modif
					// du
					// plateau
					// est
					// celle
					// interdite
				{
					int rotation = 0;
					for (int j = 0; j < 4; j++) // on effectue les 4 modif
						// possibles (la case ins�r�e
						// peut etre ins�r�e de 4
						// mani�res)
					{
						maPartie.getCaseCourante().rotate(rotation); // on
						// effectue
						// la
						// rotation
						// de la
						// case
						monCoup = new Coup(maPartie.getCaseCourante(), i,
								fleche); // cr�ation du nouveau coup
						maPartie.modifierPlateau(monCoup); // application du
						// coup (modif
						// plateau)
						ObjetIA monObjetIA = new ObjetIA();
						monObjetIA.setPlateau(maPartie.getMonPlateau()
								.sauvPlateau()); // ajout du plateau �
						// monObjetIA
						monObjetIA.setCoup(monCoup.sauvCoup());// ajout du coup
						// � monObjetIA
						ListeObjetIA.add(monObjetIA); // ajout du new ObjetIA �
						// la liste
						maPartie.setMonPlateau(plateauSauv.sauvPlateau()); // restauration
						// du
						// plateau
						// Initial
						maPartie.setCaseCourante(caseSauv); // restauration de
						// la caseCourante
						// Initiale
						rotation += 90; // change le degr� de rotation
					}
				}
			}
		}
		return ListeObjetIA; // retourne le liste des ObjetIA
	}

	public static ArrayList<ObjetIA> methode2(ObjetIA monObjetIA,
			Joueur joueurActif)
			// public static ArrayList<ObjetIA> methode2(Plateau monPlateau, Joueur
			// joueurActif)
			{
		ArrayList<ObjetIA> ListeObjetIA = new ArrayList<ObjetIA>(); // cree une
		// liste de
		// ObjetIA

		Plateau monPlateau = monObjetIA.getPlateau(); // recupere le plateau de
		// l'ObjetIA

		joueurActif.testCasesAccessibles(monPlateau); // incremente l'attribut
		// "flag" des cases
		// accessibles par le
		// joueur
		// Partie maPartie = joueurActif.getPartieActuelle(); //r�cup�reration
		// de la partie en cours
		for (int ligne = 0; ligne < 7; ligne++) // parcours des lignes du
			// plateau
		{
			for (int colonne = 0; colonne < 7; colonne++) // parcours des
				// colonnes du
				// plateau
			{
				if (monPlateau.getCase(ligne, colonne).getFlag() != 0) // test
					// si le
					// joueur
					// � la
					// droit
					// d'aller
					// sur
					// la
					// case
				{
					// System.out.println("ligne"+ligne+" colonne"+colonne
					// +" ok");
					if (ligne != 0 || colonne != 0) {
						joueurActif.seDeplacer(ligne, colonne, monPlateau); // d�placement
						// du
						// joueur
					}
					// System.out.println("ligne"+ligne+ "colonne"+colonne);
					// System.out.println(monPlateau.getCase(ligne, colonne));
					ObjetIA newObjetIA = new ObjetIA();
					newObjetIA.setPlateau(monPlateau.sauvPlateau()); // ajout du
					// plateau
					// �
					// newObjetIA
					newObjetIA.setCoup(monObjetIA.getCoup());// ajout du coup �
					// newObjetIA
					newObjetIA.setDeplacementX(ligne);
					newObjetIA.setDeplacementY(colonne);
					ListeObjetIA.add(newObjetIA); // ajout du new ObjetIA � la
					// liste
				}
			}
		}
		return ListeObjetIA;
			}

	// methode retournant la liste de tous les successeurs
	public static ArrayList<ObjetIA> successeurs(Partie maPartie,
			int indiceInterdit, String flecheInterdite, Joueur joueurActif) {
		ArrayList<ObjetIA> ListeObjetIAMethode1 = new ArrayList<ObjetIA>();
		ArrayList<ObjetIA> ListeObjetIAMethode2 = new ArrayList<ObjetIA>();
		ArrayList<ObjetIA> ListeObjetIAMethode3 = new ArrayList<ObjetIA>();

		ListeObjetIAMethode1 = methode1(maPartie, indiceInterdit,
				flecheInterdite);

		for (int i = 0; i < ListeObjetIAMethode1.size(); i++) {
			ObjetIA ObjetIAEnCours = ListeObjetIAMethode1.get(i);
			ListeObjetIAMethode2 = methode2(ObjetIAEnCours, joueurActif);
			for (int j = 0; j < ListeObjetIAMethode2.size(); j++) {
				ObjetIA newObjetIAEnCours = ListeObjetIAMethode2.get(j);
				ListeObjetIAMethode3.add(newObjetIAEnCours);
			}
		}
		return ListeObjetIAMethode3;
	}

	// methode permettant de calculer le nombre de chemins pour atteindre une
	// case, ce nombre est l'entier
	// flag de la case concern�e lorsque cette methode a �t� effectu�e
	public static void methode4(int ligne, int colonne, Joueur joueurActif,
			ObjetIA monObjetIA) {
		Plateau monPlateau = monObjetIA.getPlateau();
		Case maCase = monPlateau.getCase(ligne, colonne);// recupere la case �
		// traiter
		int L1 = 0, C1 = 0, S = 0;
		boolean Ok;

		monPlateau.getCase(ligne, colonne).setFlag(
				monPlateau.getCase(ligne, colonne).getFlag() + 1); // increment
		// le flag
		// de la
		// case de 1
		monPlateau.getCase(ligne, colonne).setFlagEnEntrant(1); // passe le
		// flagEnEntrant
		// � 1
		for (int i = maCase.getSortie() + 1; i < 5; i++)// test de toutes les
			// sorties (haut 1,
			// droite 2, bas 3,
			// gauche 4)
		{
			Ok = false;
			switch (i) {
			case 1: // haut
				if (ligne > 0
						&& maCase.getTabDroit(1) == true
						&& monPlateau.getCase(ligne - 1, colonne)
						.getTabDroit(3) == true
						&& monPlateau.getCase(ligne - 1, colonne)
						.getFlagEnEntrant() == 0) {
					L1 = -1;
					C1 = 0;
					S = 1;
					Ok = true;
				}
				break;
			case 2: // droite
				if (colonne < 6
						&& maCase.getTabDroit(2) == true
						&& monPlateau.getCase(ligne, colonne + 1)
						.getTabDroit(4) == true
						&& monPlateau.getCase(ligne, colonne + 1)
						.getFlagEnEntrant() == 0) {
					L1 = 0;
					C1 = 1;
					S = 2;
					Ok = true;
				}
				break;
			case 3: // bas
				if (ligne < 6
						&& maCase.getTabDroit(3) == true
						&& monPlateau.getCase(ligne + 1, colonne)
						.getTabDroit(1) == true
						&& monPlateau.getCase(ligne + 1, colonne)
						.getFlagEnEntrant() == 0) {
					L1 = 1;
					C1 = 0;
					S = 3;
					Ok = true;
				}
				break;
			case 4: // gauche
				if (colonne > 0
						&& maCase.getTabDroit(4) == true
						&& monPlateau.getCase(ligne, colonne - 1)
						.getTabDroit(2) == true
						&& monPlateau.getCase(ligne, colonne - 1)
						.getFlagEnEntrant() == 0) {
					L1 = 0;
					C1 = -1;
					S = 4;
					Ok = true;
				}
				break;
			}
			if (Ok == true) // s'il est possible de sortir de la case en cours
				// de traitement
			{
				monPlateau.getCase(ligne, colonne).setSortie(S); // on indique
				// par o� on
				// sort
				// on change de case
				ligne = ligne + L1;
				colonne = colonne + C1;

				methode4(ligne, colonne, joueurActif, monObjetIA); // on
				// applique
				// la
				// fonction
				// a la
				// nouvelle
				// case
				// on revient a la case
				ligne = ligne - L1;
				colonne = colonne - C1;
			}
		}
		monPlateau.getCase(ligne, colonne).setFlagEnEntrant(0); // on repasse le
		// flagEnEntrant
		// � 0
		monPlateau.getCase(ligne, colonne).setSortie(0); // //lorsque la
		// fonction se
		// termine sur une
		// case on met la
		// sortie � 0
	}

	// methode retournant la valeur d'un plateau
	public static int methode5(Joueur joueurActif, ObjetIA monObjetIA) {
		methode4(joueurActif.getPosLigne(), joueurActif.getPosColonne(),
				joueurActif, monObjetIA);
		Plateau monPlateau = monObjetIA.getPlateau();
		int nombreChemin = 0;
		int distance = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if (joueurActif.getCarteObjectif().getIdentifiant() == monPlateau
						.getCase(i, j).getIdentifiant()) {
					nombreChemin = monPlateau.getCase(i, j).getFlag();
					distance = Math.abs((joueurActif.getPosLigne() + 1)
							- (i + 1))
							+ Math.abs((joueurActif.getPosColonne() + 1)
									- (j + 1));
				}
			}
		}
		return 50 * nombreChemin + (14 - distance);
	}
/*	
	
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
			// methode developp�e par le prof
	    	// genere le meilleur coup
		//maPartie.getCaseCourante.rotate();
	    Coup monCoup=new Coup(maPartie.getCaseCourante(), 3, "haut");
	    return monCoup;
	    //TODO retourner aussi le d�placement
	} 	
	
	public void rechercheMeilleurDeplacement(Partie maPartie)
	{
		// methode developp�e par le prof
		
		//pour le deplacement, modifier les valeurs de ligne et de  colonne;
		int ligne=0, colonne=0;
		seDeplacer(ligne, colonne);
	} 
	
*/
	
	public void jouer(int indiceInterdit, String flecheInterdite) {
		Coup monCoup;
		Case caseCourante = partieActuelle.getCaseCourante();

		// valeur par default de tour de jeu de l'IA
		int ligneDeplacement = getPosLigne() - 1, colonneDeplacement = getPosColonne();
		if (indiceInterdit == 1 && flecheInterdite == "bas") {
			monCoup = new Coup(caseCourante, 1, "bas");
		} else {
			monCoup = new Coup(caseCourante, 2, "bas");
		}

		// a cet endroit, modifier l'objet monCoup, l'entier ligneDeplacement et
		// colonneDeplacement
		// en fonction de la decision de l'IA

		partieActuelle.modifierPlateau(monCoup);
		this.seDeplacer(ligneDeplacement, colonneDeplacement);
	}
	
	private class AMove{
		private int val;
		private ObjetIA o;
		
		public AMove(int val, ObjetIA o){
			this.val = val;
			this.o = o;
				
		}
		
		public int getVal() {
			return val;
		}
		public void setVal(int val) {
			this.val = val;
		}
		public ObjetIA getO() {
			return o;
		}
		public void setO(ObjetIA o) {
			this.o = o;
		}
		
		
	}
	
	
	private AMove minimaxAB(String flecheInterdite, int sensInterdit, Joueur joueurActif, ObjetIA oia,
			int minmax , int depth, int alpha, int beta){
		if (depth == this.profondeurLimite || joueurActif.testCarteTrouvee()) {// TODO compléter ceci
			return (new AMove(methode5(joueurActif,oia),oia));	
		}
		if (minmax == MIN) {
			ArrayList<ObjetIA> succs = 
				successeurs(joueurActif.getPartieActuelle(), sensInterdit, flecheInterdite, joueurActif);
			AMove ret= new AMove(beta,oia);
			int taille = succs.size();
			for (int i = 0 ; (i < taille) || (alpha > beta) ; i++){
				ObjetIA o = succs.get(i);
				
				joueurActif.getPartieActuelle().modifierPlateau(o.getCoup());
				joueurActif.seDeplacer(o.getDeplacementX(), o.getDeplacementY());
				
				Joueur j = joueurActif.getPartieActuelle().joueurSuivant(joueurActif);
				
				
				
				AMove currentMove = minimaxAB(flecheInterdite, sensInterdit, j,o, MAX, depth+1, alpha, beta); // TODO metre a jour ceci 
				if (currentMove.getVal() < beta){				
					beta  = currentMove.getVal();
					ret = currentMove;
				}
			}		
			return ret;	
		}
		else { //if (minmax == MAX) {
			ArrayList<ObjetIA> succs = 
				successeurs(joueurActif.getPartieActuelle(), sensInterdit, flecheInterdite, joueurActif);
			AMove ret= new AMove(beta,oia);
			int taille = succs.size();
			for (int i = 0 ; (i < taille) || (alpha > beta) ; i++){
				ObjetIA o = succs.get(i);
				
				joueurActif.getPartieActuelle().modifierPlateau(o.getCoup());
				joueurActif.seDeplacer(o.getDeplacementX(), o.getDeplacementY());
				
				Joueur j = joueurActif.getPartieActuelle().joueurSuivant(joueurActif);
				
				
				
				AMove currentMove = minimaxAB(flecheInterdite, sensInterdit, j,o, MIN, depth+1, alpha, beta); // TODO metre a jour ceci 
				if (currentMove.getVal() > alpha){				
					alpha  = currentMove.getVal();
					ret = currentMove;
				}
			}		
			return ret;	
		}
	}
}
	
