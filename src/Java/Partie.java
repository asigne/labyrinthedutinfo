package Java;

import java.util.ArrayList;

public class Partie {
	int nbCarte=24;
	String nom;
	Plateau monPlateau;
	Case caseCourante;
	ArrayList<Carte> ListCarte;
	ArrayList<Joueur> ListJoueur;
	//ArrayList<Pion> ListPion;
	boolean partieFinie;
	
	public Partie(String nom)
	{
		this.nom=nom;
		partieFinie=false;
		ListCarte = new ArrayList<Carte>();
		ListJoueur = new ArrayList<Joueur>();
		//ListPion = new ArrayList<Pion>();
		
		/*
		//creation des pions
		ListPion.add(new Pion(0, "bleu"));
		ListPion.add(new Pion(1, "rouge"));
		ListPion.add(new Pion(2, "vert"));
		ListPion.add(new Pion(3, "jaune"));
		*/
		
		
		//creation du plateau de maniere aleatoire
		monPlateau=new Plateau();
		
		//détermination de la caseCourante
		caseCourante=monPlateau.ListCase.get(0);
		
		//creation des cartes
		for(int i=0; i<nbCarte;i++)
		{
			ListCarte.add(new Carte(i));
		}
	}
	
	//ajout d'un joueur
	public void ajouterJoueur(Joueur newJoueur)
	{
		ListJoueur.add(newJoueur);
		int numJoueur=ListJoueur.size()-1;
		Pion pion = null;
		int posX=0, posY=0;
		
		Joueur joueurCourant=ListJoueur.get(numJoueur);
		
		switch (numJoueur)
		{
			case 0:
				pion= new Pion(0, "bleu");
				posX=0;
				posY=0;
				break;
			case 1:
				pion= new Pion(1, "rouge");
				posX=6;
				posY=6;
				break;
			case 2:
				pion= new Pion(3, "vert");
				posX=6;
				posY=0;
				break;
			case 3:
				pion= new Pion(2, "jaune");
				posX=0;
				posY=6;
				break;
		}
		
		joueurCourant.setPion(pion);
		joueurCourant.setPosLigne(posX);
		joueurCourant.setPosColonne(posY);
		monPlateau.getCase(posX, posY).ajouterPion(pion);
	}
	
	public ArrayList<Joueur> getListJoueur() {
		return ListJoueur;
	}

	//suppression d'un joueur
	public void supprJoueur(Joueur oldJoueur)
	{
		ListJoueur.remove(oldJoueur);
	}
	
	//renvoie le plateau de la partie
	public Plateau getMonPlateau()
	{
		return monPlateau;
	}

	//renvoie la case courante de la partie
	public Case getCaseCourante()
	{
		return caseCourante;
	}

	//modifie le plateau
	public void modifierPlateau(Coup monCoup)
	{
		String sens = monCoup.sens;
		if(sens=="haut" || sens=="bas")
		{
			caseCourante=monPlateau.modifierColonne(monCoup);
		}
		else
		{
			caseCourante=monPlateau.modifierLigne(monCoup);
		}
		
	}
	
	/*//renvoie la liste de carte
	public ArrayList<Carte> getListCarte() {
		return ListCarte;
	}*/

	
	//lancer la partie
	public void lancerPartie()
	{
		if(ListJoueur.size()<2)
			{
				//pas assez de joueurs;
			}
		else
			{
				distribuerCarte();		
			}
	}
	
	//distribution des cartes aux joueurs
	private void distribuerCarte() {
		int numJoueur=0;
		int carteAleatoire;
		Carte carteActuelle;
		while(!ListCarte.isEmpty()) //tant qu'il reste des cartes a distribuer
			{
				carteAleatoire=(int)(Math.random()*ListCarte.size()); 
				carteActuelle=ListCarte.get(carteAleatoire);	//choix d'une carte au hasard
				ListJoueur.get(numJoueur).ajouterCarte(carteActuelle); //distribution au joueur actuel
				//si dernier joueur atteind, la prochain joueur sera le premier de la liste
				if(numJoueur==ListJoueur.size()-1)	
					{
						numJoueur=0;
					}
				else	//sinon on passe au joueur suivant
					{
						numJoueur++;
					}
				ListCarte.remove(carteAleatoire); //suppression de la carte courante de la liste
			}
	}
}

	
