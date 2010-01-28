package Java;

import java.util.ArrayList;

public class IA extends Joueur {

	Coup CoupCourant;
	int newPosLigne, newPosColonne;
	boolean deplacement;
	
	public IA(String nom) {
		super(nom);
	}
	
	
	public static ArrayList<ObjetIA> methode1(Partie maPartie, int indiceInterdit, String flecheInterdite)
	{
		ArrayList<ObjetIA> ListeObjetIA = new ArrayList<ObjetIA>(); //cree une liste de ObjetIA
		Coup monCoup;	
		String fleche="haut";
		Plateau plateauSauv=maPartie.getMonPlateau().sauvPlateau();	//sauvegarde du plateau Initial
		Case caseSauv=maPartie.getCaseCourante().sauvCase();			//sauvegarde de la caseCourante Initiale
		
		for(int tour=0; tour<4; tour++)	//tour est la variable pour effectuer le tour du plateau (haut, droite, bas et gauche)
		{
			switch(tour)
			{
			case 0:
				fleche="haut";
				break;
			case 1:
				fleche="droite";
				break;
			case 2:
				fleche="bas";
				break;
			case 3:
				fleche="gauche";
				break;
			}
				for(int i=1; i<=5; i=i+2)   //i est la variable pour passer de la colonne 1 à la 3 puis à la 5
				{
					if(fleche!=flecheInterdite || i!=indiceInterdit) // on test si la modif du plateau est celle interdite 
					{
						int rotation=0;
						for(int j=0; j<4; j++)   //on effectue les 4 modif possibles (la case insérée peut etre insérée de 4 manières)
						{
							maPartie.getCaseCourante().rotate(rotation); //on effectue la rotation de la case
							monCoup = new Coup(maPartie.getCaseCourante(),i,fleche); //création du nouveau coup
							maPartie.modifierPlateau(monCoup);			//application du coup (modif plateau)
							ObjetIA monObjetIA=new ObjetIA();
							monObjetIA.setPlateau(maPartie.getMonPlateau().sauvPlateau());	// ajout du plateau à monObjetIA
							monObjetIA.setCoup(monCoup.sauvCoup());// ajout du coup à monObjetIA
							ListeObjetIA.add(monObjetIA);	// ajout du new ObjetIA à la liste
							maPartie.setMonPlateau(plateauSauv.sauvPlateau());	//restauration du plateau Initial
							maPartie.setCaseCourante(caseSauv);		//restauration de la caseCourante Initiale
							rotation+=90;		//change le degré de rotation
						}
					}
				}
		}
	return ListeObjetIA; //retourne le liste des ObjetIA
	}
	
	
	public static ArrayList<ObjetIA> methode2(ObjetIA monObjetIA, Joueur joueurActif)
	//public static ArrayList<ObjetIA> methode2(Plateau monPlateau, Joueur joueurActif)
	{
		ArrayList<ObjetIA> ListeObjetIA = new ArrayList<ObjetIA>(); //cree une liste de ObjetIA
		
		Plateau monPlateau=monObjetIA.getPlateau(); //recupere le plateau de l'ObjetIA
		
		joueurActif.testCasesAccessibles(monPlateau); //incremente l'attribut "flag" des cases accessibles par le joueur
		//Partie maPartie = joueurActif.getPartieActuelle();		//récupéreration de la partie en cours
		for(int ligne=0;ligne<7;ligne++)		//parcours des lignes du plateau
		{
			for(int colonne=0;colonne<7;colonne++)		//parcours des colonnes du plateau
				{
					if(monPlateau.getCase(ligne, colonne).getFlag()!=0)		//test si le joueur à la droit d'aller sur la case
					{
						//System.out.println("ligne"+ligne+" colonne"+colonne +" ok");
						if(ligne!=0 || colonne!=0)
							{
								joueurActif.seDeplacer(ligne, colonne, monPlateau);		//déplacement du joueur		
							}
							//System.out.println("ligne"+ligne+ "colonne"+colonne);
							//System.out.println(monPlateau.getCase(ligne, colonne));
							ObjetIA newObjetIA=new ObjetIA();
							newObjetIA.setPlateau(monPlateau.sauvPlateau());	// ajout du plateau à newObjetIA
							newObjetIA.setCoup(monObjetIA.getCoup());// ajout du coup à newObjetIA
							newObjetIA.setDeplacementX(ligne);
							newObjetIA.setDeplacementY(colonne);
							ListeObjetIA.add(newObjetIA);	// ajout du new ObjetIA à la liste
					}
				}
		}
	return ListeObjetIA;
	}
	
	//methode retournant la liste de tous les successeurs
	public static ArrayList<ObjetIA> methode3(Partie maPartie, int indiceInterdit, String flecheInterdite, Joueur joueurActif)
	{
		ArrayList<ObjetIA> ListeObjetIAMethode1 = new ArrayList<ObjetIA>();
		ArrayList<ObjetIA> ListeObjetIAMethode2 = new ArrayList<ObjetIA>();
		ArrayList<ObjetIA> ListeObjetIAMethode3 = new ArrayList<ObjetIA>();
		
		ListeObjetIAMethode1=methode1(maPartie, indiceInterdit, flecheInterdite);
		
		for(int i=0; i<ListeObjetIAMethode1.size(); i++)
			{
				ObjetIA ObjetIAEnCours=ListeObjetIAMethode1.get(i);
				ListeObjetIAMethode2=methode2(ObjetIAEnCours, joueurActif);
				for(int j=0; j<ListeObjetIAMethode2.size(); j++)
				{
					ObjetIA newObjetIAEnCours=ListeObjetIAMethode2.get(j);
					ListeObjetIAMethode3.add(newObjetIAEnCours);
				}
			}
		return ListeObjetIAMethode3;
	}
	
	//methode permettant de calculer le nombre de chemins pour atteindre une case, ce nombre est l'entier 
	//flag de la case concernée lorsque cette methode a été effectuée	
	public static void methode4(int ligne, int colonne, Joueur joueurActif, ObjetIA monObjetIA)
		{
			Plateau monPlateau=monObjetIA.getPlateau();
			Case maCase=monPlateau.getCase(ligne, colonne);//recupere la case à traiter
			int L1=0, C1=0, S=0;
			boolean Ok;
	
			monPlateau.getCase(ligne, colonne).setFlag(monPlateau.getCase(ligne, colonne).getFlag()+1); //increment le flag de la case de 1
			monPlateau.getCase(ligne, colonne).setFlagEnEntrant(1); //passe le flagEnEntrant à 1
			for(int i=maCase.getSortie()+1;i<5;i++)//test de toutes les sorties (haut 1, droite 2, bas 3, gauche 4)
			{	
				Ok = false;
		   		switch(i)
			   		{
			   		case 1: //haut
			   			if(ligne>0 && maCase.getTabDroit(1)==true &&
			   					monPlateau.getCase(ligne-1, colonne).getTabDroit(3)==true &&
			   					monPlateau.getCase(ligne-1, colonne).getFlagEnEntrant()==0)
			   				{
								L1=-1;
								C1=0;
								S=1;
								Ok=true;
			   				}	
			   			break;
					case 2: //droite
						if(colonne<6 && maCase.getTabDroit(2)==true &&
								monPlateau.getCase(ligne, colonne+1).getTabDroit(4)==true &&
								monPlateau.getCase(ligne, colonne+1).getFlagEnEntrant()==0)
							{
								L1=0;
								C1=1;
								S=2;
								Ok=true;
							}		   			
						break;	
					case 3: //bas
						if(ligne<6 && maCase.getTabDroit(3)==true &&
								monPlateau.getCase(ligne+1, colonne).getTabDroit(1)==true &&
								monPlateau.getCase(ligne+1, colonne).getFlagEnEntrant()==0)
							{
								L1=1;
								C1=0;
								S=3;
								Ok=true;
							}
						break;
					case 4: //gauche
							if(colonne>0 && maCase.getTabDroit(4)==true &&
									monPlateau.getCase(ligne, colonne-1).getTabDroit(2)==true &&
									monPlateau.getCase(ligne, colonne-1).getFlagEnEntrant()==0)
								{
									L1=0;
									C1=-1;
									S=4;
									Ok=true;
								}	
							break;
			   		}
		   		if(Ok==true)  //s'il est possible de sortir de la case en cours de traitement
			   		{
				   		monPlateau.getCase(ligne, colonne).setSortie(S);  //on indique par où on sort
				   		//on change de case
				   		ligne=ligne+L1;
				   		colonne=colonne+C1;
				   		
				   		methode4(ligne,colonne, joueurActif, monObjetIA); //on applique la fonction a la nouvelle case
				   		//on revient a la case
				   		ligne=ligne-L1;
				   		colonne=colonne-C1;
			   		}
			}
			monPlateau.getCase(ligne, colonne).setFlagEnEntrant(0); //on repasse le flagEnEntrant à 0
			monPlateau.getCase(ligne, colonne).setSortie(0); ////lorsque la fonction se termine sur une case on met la sortie à 0
		}
		
	//methode retournant la valeur d'un plateau
	public static int methode5(int ligne, int colonne, Joueur joueurActif, ObjetIA monObjetIA)
	{
		methode4(joueurActif.getPosLigne(),joueurActif.getPosColonne(),joueurActif, monObjetIA);
		Plateau monPlateau=monObjetIA.getPlateau();
		int nombreChemin=0;
		int distance = 0;
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
				{		
					if(joueurActif.getCarteObjectif().getIdentifiant()==monPlateau.getCase(i, j).getIdentifiant())
					{
						nombreChemin=monPlateau.getCase(i, j).getFlag();
						distance=Math.abs((joueurActif.getPosLigne()+1)-(i+1))+Math.abs((joueurActif.getPosColonne()+1)-(j+1));
					}
				}
		}
		return 50*nombreChemin+(14-distance);
	}
	
	

	public void jouer(int indiceInterdit, String flecheInterdite) {
		Coup monCoup;
		Case caseCourante=partieActuelle.getCaseCourante();
		
		
		//valeur par default de tour de jeu de l'IA
		int ligneDeplacement=getPosLigne()-1, colonneDeplacement=getPosColonne();
		if(indiceInterdit==1 && flecheInterdite=="bas")
			{
				monCoup=new Coup(caseCourante, 1, "bas");
			}
		else
			{
				monCoup=new Coup(caseCourante, 2, "bas");
			}
		
		//a cet endroit, modifier l'objet monCoup, l'entier ligneDeplacement et colonneDeplacement
		// en fonction de la decision de l'IA
		
		partieActuelle.modifierPlateau(monCoup);
		this.seDeplacer(ligneDeplacement, colonneDeplacement);
	}
}
