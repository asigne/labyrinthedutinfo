package Java;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Plateau implements Serializable, Cloneable{
	boolean PlateauBool[][]; //plateau de booleen
	Case PlateauCase[][];			//plateau des Cases
	ArrayList<Case> ListCase;	//liste de toutes les cases mobiles
	int comptI;		//nb de I sans image
	int comptLimage;	//nb de L avec image
	int comptL;		//nb de L sans image
	int comptTimage;	//nb de T avec image
	int ligne,colonne;	//indice pour le parcours du plateau
	int rotationAleatoire; //variable pour déterminer la rotation aléatoire des cases
	int caseAleatoire;	//variable pour choisir une case aleatoire dans la liste "ListCase"
	
	public Plateau(){
		PlateauBool= new boolean[7][7]; //plateau de booleen
		PlateauCase= new Case[7][7];			//plateau des Cases
		ListCase = new ArrayList<Case>();
		comptI=13;		//nb de I sans image
		comptLimage=6;	//nb de L avec image
		comptL=9;		//nb de L sans image
		comptTimage=6;	//nb de T avec image
		initialisation();
	}
	
	public boolean[][] getPlateauBool() {
		return PlateauBool;
	}
		
	public void setPlateauCase(Case[][] plateauCase) {
		PlateauCase = plateauCase;
	}

	public Case[][] getPlateauCase() {
		return PlateauCase;
	}

	public void setCase(Case maCase, int ligne, int colonne) {
		PlateauCase[ligne][colonne]=maCase;
	}
	

	
	public void initialisation(){
		{		
			//initialisation du PlateauBool à false
			for(ligne=0;ligne<7;ligne++)
				{
					for(colonne=0;colonne<7;colonne++)
						{
							PlateauBool[ligne][colonne]=false;
						}
				}	
			
			//creation de la liste de toutes les cases mobiles
			creationListeCase();
			
			//creation des cases fixes
			PlateauBool[0][0]=true;		PlateauCase[0][0]=new L(24,90,7);	//bleu
			PlateauBool[0][6]=true;		PlateauCase[0][6]=new L(26,180,10);	//vert
			PlateauBool[6][0]=true;		PlateauCase[6][0]=new L(27,0,8);	//jaune
			PlateauBool[6][6]=true;		PlateauCase[6][6]=new L(25,270,9);	//rouge
			
			PlateauBool[0][2]=true;		PlateauCase[0][2]=new T(6,0,1);		//casque
			PlateauBool[0][4]=true;		PlateauCase[0][4]=new T(7,0,2);		//chandelier
			PlateauBool[6][2]=true;		PlateauCase[6][2]=new T(16,180,11);	//sac	
			PlateauBool[6][4]=true;		PlateauCase[6][4]=new T(17,180,12);	//livre	

			PlateauBool[2][0]=true;		PlateauCase[2][0]=new T(8,270,3);	//épée	
			PlateauBool[4][0]=true;		PlateauCase[4][0]=new T(12,270,7);	//tetemort	
			PlateauBool[2][6]=true;		PlateauCase[2][6]=new T(11,90,6);	//bague	
			PlateauBool[4][6]=true;		PlateauCase[4][6]=new T(15,90,10);	//carte

			PlateauBool[2][2]=true;		PlateauCase[2][2]=new T(9,270,4);	//diamant	
			PlateauBool[2][4]=true;		PlateauCase[2][4]=new T(10,0,5);	//tresor	
			PlateauBool[4][2]=true;		PlateauCase[4][2]=new T(13,90,8);	//clés
			PlateauBool[4][4]=true;		PlateauCase[4][4]=new T(14,180,9);	//couronne
									
			//remplissage du PlateauCase avec les case mobiles de la liste
			for(ligne=0;ligne<7;ligne++)
			{
				for(colonne=0;colonne<7;colonne++)
					{
						if(!(PlateauBool[ligne][colonne]))
						{	
							caseAleatoire=(int)(Math.random()*ListCase.size());
							PlateauCase[ligne][colonne]=ListCase.get(caseAleatoire);
							PlateauBool[ligne][colonne]=true;
							ListCase.remove(caseAleatoire);							
						}
					}		
			}	
		}
}

public void creationListeCase()
{
	//remplissage de liste de case ListCase avec les case mobiles du plateau
	//creation des 13 'I' sans images
	for(int i=0;i<comptI;i++)
	{
		rotationAleatoire=rotationAleatoire();
		ListCase.add(new I(rotationAleatoire));
	}
	
	//creation des 9 'L' sans images
	for(int i=0;i<comptL;i++)
	{
		rotationAleatoire=rotationAleatoire();
		ListCase.add(new L(100,rotationAleatoire,0));	
	}
	
	//creation des 6 'L' avec images
	for(int i=0;i<comptLimage;i++)
	{
		rotationAleatoire=rotationAleatoire();
		ListCase.add(new L(i,rotationAleatoire,i+1));	
	}
	//creation des 6 'T' avec images
	for(int i=0;i<comptTimage;i++)
	{
		rotationAleatoire=rotationAleatoire();
		ListCase.add(new T(i+18,rotationAleatoire,i+13));	// i+13 car il y a les 13 cases fixes avant
	}
}
	
public int rotationAleatoire()
{
	rotationAleatoire=(int)(Math.random()*4);
	int indiceRotation = 0;
	switch(rotationAleatoire)
	{
	case 0:
		indiceRotation=0;
		break;
	case 1:
		indiceRotation=90;
		break;
	case 2:
		indiceRotation=180;
		break;
	case 3:
		indiceRotation=270;
		break;
	}
	return indiceRotation;
}
	
	
public Case getCase(int ligne, int colonne){
		return PlateauCase[ligne][colonne];
	}

public Case modifierColonne(Coup monCoup)
	{
		Case caseCoup = monCoup.maCase;
		colonne = monCoup.modif;
		String sens = monCoup.sens;
		
		Case caseTemp;
		if(sens=="haut")
		{
			caseTemp=PlateauCase[6][colonne];
			for(ligne=6;ligne>0;ligne--)
			{
				PlateauCase[ligne][colonne]=PlateauCase[ligne-1][colonne];
			}
			//attention : a modifier : modifier les methode modifierLigne et modifierColonne pour tenir
			// compte du nouvel etat de la caseCourante (notemment lors du jeu contre l'IA)
			PlateauCase[0][colonne]=caseCoup;
			PlateauCase[0][colonne].setListJoueur(caseTemp.getListJoueur());
		}
		else
		{
			caseTemp=PlateauCase[0][colonne];
			for(ligne=0;ligne<6;ligne++)
			{
				PlateauCase[ligne][colonne]=PlateauCase[ligne+1][colonne];
			}
			//attention : a modifier : modifier les methode modifierLigne et modifierColonne pour tenir
			// compte du nouvel etat de la caseCourante (notemment lors du jeu contre l'IA)
			PlateauCase[6][colonne]=caseCoup;
			PlateauCase[6][colonne].setListJoueur(caseTemp.getListJoueur());
		}
		return caseTemp;
	}
	
public Case modifierLigne(Coup monCoup)
	{
		Case caseCoup = monCoup.maCase;
		ligne = monCoup.modif;
		String sens = monCoup.sens;
		
		Case caseTemp;
		if(sens=="gauche")
		{
			caseTemp=PlateauCase[ligne][6];
			for(colonne=6;colonne>0;colonne--)
			{
				PlateauCase[ligne][colonne]=PlateauCase[ligne][colonne-1];
			}
			//attention : a modifier : modifier les methode modifierLigne et modifierColonne pour tenir
			// compte du nouvel etat de la caseCourante (notemment lors du jeu contre l'IA)
			PlateauCase[ligne][0]=caseCoup;
			PlateauCase[ligne][0].setListJoueur(caseTemp.getListJoueur());
		}
		else
		{	
			caseTemp=PlateauCase[ligne][0];
			for(colonne=0;colonne<6;colonne++)
			{
				PlateauCase[ligne][colonne]=PlateauCase[ligne][colonne+1];
			}
			//attention : a modifier : modifier les methode modifierLigne et modifierColonne pour tenir
			// compte du nouvel etat de la caseCourante (notemment lors du jeu contre l'IA)
			PlateauCase[ligne][6]=caseCoup;
			PlateauCase[ligne][6].setListJoueur(caseTemp.getListJoueur());
		}
		return caseTemp;
	}

	
public void affiche()
	{
		//affichage plateau
		for(ligne=0;ligne<7;ligne++)
		{
			for(colonne=0;colonne<7;colonne++)
				{
						//System.out.print(PlateauCase[ligne][colonne].toString()+"   \t");
				System.out.print(PlateauCase[ligne][colonne]+"  \t");
				}
			System.out.print("\n");
		}
	}


public Plateau sauvPlateau()
{
	Plateau plateauASauv = this;
	Plateau plateauSauv=new Plateau();	
	for(int ligne=0;ligne<7;ligne++) //parcours des lignes du plateau
	{
		for(int colonne=0;colonne<7;colonne++) //parcours des colonnes du plateau
			{
				// on sauvegarde le contenu du tableau PlateauBool
				plateauSauv.getPlateauBool()[ligne][colonne]=plateauASauv.getPlateauBool()[ligne][colonne];
				//on sauvegarde le contenu du tableau PlateauCase
				plateauSauv.getPlateauCase()[ligne][colonne]=plateauASauv.getCase(ligne, colonne).sauvCase();
			}
	}	
	return plateauSauv;
}



	
}
