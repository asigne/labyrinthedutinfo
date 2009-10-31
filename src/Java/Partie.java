package Java;

import java.util.ArrayList;

public class Partie {
	int nbCarte=24;
	String nom;
	Plateau monPlateau;
	Case caseCourante;
	ArrayList<Carte> ListCarte;
	ArrayList<Joueur> ListJoueur;
	
	public Partie(String nom)
	{
		this.nom=nom;
		ListCarte = new ArrayList<Carte>();
		ListJoueur = new ArrayList<Joueur>();
		
		//creation du plateau de maniere aleatoire
		monPlateau=new Plateau();
		
		//détermination de la caseCourante
		caseCourante=monPlateau.ListCase.get(0);
		
		//creation des cartes
		/*for(int i=0; i<nbCarte;i++)
		{
			ListCarte.add(new Carte(i));
		}*/
	}
	
	

	public void ajouterJoueur(Joueur newJoueur)
	{
		ListJoueur.add(newJoueur);
	}
	
	public void supprJoueur(Joueur oldJoueur)
	{
		ListJoueur.remove(oldJoueur);
	}
	
	public Plateau getMonPlateau()
	{
		return monPlateau;
	}

	public Case getCaseCourante()
	{
		return caseCourante;
	}
	
	public void setCaseCourante(Case maCase)
	{
		caseCourante=maCase;
	}

	
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
	
	
	public void lancerPartie()
	{
		//distribution des cartes aux joueurs
		int numJoueur=0;
		Carte carteActuelle;
		while(!ListCarte.isEmpty())
			{
				carteActuelle=ListCarte.get(0);
				ListJoueur.get(numJoueur).ajouterCarte(carteActuelle);
				if(numJoueur==ListJoueur.size()-1)
					{
						numJoueur=0;
					}
				else
					{
						numJoueur++;
					}
				ListCarte.remove(0);
			}
	}
	
}

	
