package Java;

import java.util.ArrayList;

public class Partie {
	String nom;
	Plateau monPlateau;
	public Case caseCourante;
	ArrayList<Joueur> ListJoueur;
	
	
	public Partie(String nom)
	{
		this.nom=nom;
		//creation du plateau de maniere aleatoire
		monPlateau=new Plateau();
		
		//détermination de la caseCourante
		caseCourante=monPlateau.ListCase.get(0);
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
		Case caseCourante = monCoup.maCase;
		int modif = monCoup.modif;
		String sens = monCoup.sens;
		
		if(sens=="haut" || sens=="bas")
		{
			monPlateau.modifierColonne(modif, this, sens);
		}
		else
		{
			monPlateau.modifierLigne(modif, this, sens);
		}
		
	}
	
	
	/*public void lancerPartie(Partie maPartie)
	{
		
	}*/
	
}

	
