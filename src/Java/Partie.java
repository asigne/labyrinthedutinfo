package Java;

import java.util.ArrayList;

public class Partie {
	int nbCarte=24; //nb de cartes a distribuer aux joueurs
	String nom;		//nom de la partie
	Plateau monPlateau;	//plateau de la partie
	Case caseCourante;	//caseCourante de la partie
	ArrayList<Carte> ListCarte;	//liste des cartes à distribuer
	ArrayList<Joueur> ListJoueur;	// liste des joueurs de la partie
	boolean partieFinie;	//si true partie finie sinon false
	
	public Partie(String nom)
	{
		this.nom=nom;
		partieFinie=false;
		ListCarte = new ArrayList<Carte>();
		ListJoueur = new ArrayList<Joueur>();
		
		//creation du plateau de maniere aleatoire
		monPlateau=new Plateau();
		
		//détermination de la caseCourante
		caseCourante=monPlateau.ListCase.get(0); // derniere case non placée sur le plateau
		
		//creation des cartes à distribuer
		for(int i=0; i<nbCarte;i++)
		{
			ListCarte.add(new Carte(i));
		}
	}
	
	public boolean getPartieFinie() {
		return partieFinie;
	}

	public void setPartieFinie(boolean partieFinie) {
		this.partieFinie = partieFinie;
	}

	//ajout d'un joueur
	public void ajouterJoueur(Joueur newJoueur)
	{
		int numJoueur=ListJoueur.size();
		ListJoueur.add(newJoueur);		
		Joueur joueurCourant=ListJoueur.get(numJoueur);
		
		joueurCourant.setIdentifiant(numJoueur);
		switch (numJoueur)
		{
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
	
	//retourne la liste des joueurs de la partie
	public ArrayList<Joueur> getListJoueur() {
		return ListJoueur;
	}
	
	public Joueur joueurSuivant(Joueur joueurActif)
	{
		int indiceJoueurCourant;
		indiceJoueurCourant=ListJoueur.indexOf(joueurActif);
		if(indiceJoueurCourant==ListJoueur.size()-1)
		{
			return ListJoueur.get(0);
		}
		else
		{
			return ListJoueur.get(indiceJoueurCourant+1);
		}
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
	public void distribuerCarte() {
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
		
		
		for(int i=0; i<ListJoueur.size();i++)
		{
			ListJoueur.get(i).ajouterCarte(new Carte(24+ListJoueur.get(i).getIdentifiant()));
			//ListJoueur.get(i).setCarteObjectif(ListJoueur.get(i).getListCarte().get(0));
		}
	}
	
	
	
}

	
