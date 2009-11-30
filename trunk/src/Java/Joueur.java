package Java;

import java.util.ArrayList;

public abstract class Joueur{
	int posLigneInit, posColonneInit;	
	int identifiant;
	String nom;
	Partie partieActuelle;
	ArrayList<Carte> ListCarte;
	Carte carteObjectif;
	int posLigne;
	int posColonne;
	int numObjectifTrouve;
	
	public Joueur(String nom){
		this.identifiant=0;
		this.nom=nom;
		ListCarte = new ArrayList<Carte>();
		partieActuelle=null;
	}	
	
	public void RejoindrePartie(Partie maPartie){
		partieActuelle=maPartie;
		maPartie.ajouterJoueur(this);
	}
		
	public boolean modifierPlateau(Coup monCoup)
	{
		partieActuelle.modifierPlateau(monCoup);
		return true;
	}
	
	//cartes
	public void ajouterCarte(Carte newCarte)
	{
		ListCarte.add(newCarte);
	}
	public void supprCarte(Carte oldCarte)
	{
		ListCarte.remove(oldCarte);
	}	
	public ArrayList<Carte> getListCarte() {
		return ListCarte;
	}
	public Carte getCarteObjectif()
		{
			return ListCarte.get(0);
		}
	public void modifCarteObjectif()
		{
			if(partieActuelle.getRegle().equals("Normal"))
			{
				this.supprCarte(this.getListCarte().get(0));
			}
			else
			{
				if(this.getListCarte().size()>0)
				{
					boolean objectif = false;
					int i=0;
					
					while(objectif==false && i<this.getListCarte().size())
					{
						if(this.getListCarte().get(i).getIdentifiant()==numObjectifTrouve)
						{
							this.supprCarte(this.getListCarte().get(i));
							objectif=true;
						}	
						i++;
					}
				}
			}
					//this.supprCarte(this.getListCarte().get(0));
					//this.setCarteObjectif(this.getListCarte().get(0));
				
	//			else
	//			{
	//				partieActuelle.setPartieFinie(true);
	//				//this.setCarteObjectif(new Carte(this.getIdentifiant()+24));
	//			}
		}
	
	
	//position
	public int getPosLigne() {
		return posLigne;
	}
	public int getPosColonne() {
		return posColonne;
	}	
	public void setPosition(int posLigne, int posColonne) {
		this.posLigneInit = posLigne;
		this.posColonneInit = posColonne;
		this.posLigne = posLigne;
		this.posColonne = posColonne;
		partieActuelle.getMonPlateau().getCase(posLigne, posColonne).ajouterJoueur(this);
	}
	public boolean seDeplacer(int ligne, int colonne)
	{
		if(testCaseAccessible(ligne, colonne))
		{
			partieActuelle.getMonPlateau().getCase(posLigne, posColonne).supprJoueur(this);	
			posLigne=ligne;
			posColonne=colonne;	
			partieActuelle.getMonPlateau().getCase(ligne, colonne).ajouterJoueur(this);	
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	public void modifPosition(int ligne, int colonne)
	{
		posLigne=ligne;
		posColonne=colonne;	
	}
	
	
	//information du joueur
	public String getNom() {
		return nom;
	}
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	
	
	
	
	public boolean testCarteTrouvee()
	{
		if(partieActuelle.getRegle().equals("Normal"))
		{
			if(!this.getListCarte().isEmpty())
				{
					Case maCase = partieActuelle.getMonPlateau().getCase(posLigne, posColonne);
					if(maCase.getIdentifiant()==this.getCarteObjectif().getIdentifiant())
						{
							numObjectifTrouve=maCase.getIdentifiant();
							//carte trouvée !!!
							return true;
						}
				}
		}
		else
		{
			if(!this.getListCarte().isEmpty())
			{
				Case maCase = partieActuelle.getMonPlateau().getCase(posLigne, posColonne);
				
				boolean objectif = false;
				int i=0;
				
				while(objectif==false && i<this.getListCarte().size())
				{
					if(this.getListCarte().get(i).getIdentifiant()==maCase.getIdentifiant())
					{
						numObjectifTrouve=maCase.getIdentifiant();
						objectif=true;
					}	
					i++;
				}
				
				if(objectif)  
				{
					//carte trouvée !!!
					return true;
				}
			}			
		}
			return false;
	}
	
	public void testJoueurGagnant()
	{
		if(posColonne==posColonneInit && posLigne == posLigneInit && ListCarte.isEmpty())
			{
				partieActuelle.setPartieFinie(true);	//partie terminée
			}
	}

	//methode effectuant le test pour connaitre la cases accessibles par un joueur
	
	public boolean testCaseAccessible(int ligne, int colonne)
		{
			Plateau monPlateau=partieActuelle.getMonPlateau();
			if(monPlateau.getCase(ligne, colonne).getFlag()==1)
			   {
				   return true;
			   }
			else
			   {
				   return false;
			   }
	   }
	
	public void testCasesAccessibles()
	{
		Plateau monPlateau=partieActuelle.getMonPlateau();
		//int posLigne=monJoueur.getPosLigne();
		//int posColonne=monJoueur.getPosColonne();
		
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				monPlateau.getCase(i,j).setFlag(0);
				monPlateau.getCase(i,j).setEntree(-1);
				monPlateau.getCase(i,j).setSortie(0);
			}	
		}
	   
	   fonction(posLigne, posColonne); 
	}
	
	
	//fonction permettant de savoir si une case est accessible depuis la postion du joueur
	// correspondant aux parametres ligne et colonne.
	public void fonction(int ligne, int colonne)
	   {
			Plateau monPlateau=partieActuelle.getMonPlateau();
			Case maCase=monPlateau.getCase(ligne, colonne);
			int sortie=maCase.getSortie();
			int entree=maCase.getEntree();
			int L1=0, C1=0, S=0, E=0;
			boolean Ok = false;
			
			monPlateau.getCase(ligne, colonne).setFlag(1);
	
		   
		   for(int i=maCase.getSortie()+1;i<5;i++)
		   {
			   if(sortie!=entree)
				   {
				   		switch(i)
					   		{
						   		case 1: //haut
						   			if(ligne>0 && maCase.getTabDroit(1)==true &&
						   					monPlateau.getCase(ligne-1, colonne).getTabDroit(3)==true &&
						   					monPlateau.getCase(ligne-1, colonne).getFlag()==0)
						   				{
						   					L1=-1;
						   					C1=0;
						   					S=1;
						   					E=3;
						   					Ok=true;
						   				}	
						   			break;
						   			
						   		case 2: //droite
						   			if(colonne<6 && maCase.getTabDroit(2)==true &&
						   					monPlateau.getCase(ligne, colonne+1).getTabDroit(4)==true &&
						   					monPlateau.getCase(ligne, colonne+1).getFlag()==0)
						   				{
						   					L1=0;
						   					C1=1;
						   					S=2;
						   					E=4;
						   					Ok=true;
						   				}		   			
						   			break;
						   			
						   		case 3: //bas
						   			if(ligne<6 && maCase.getTabDroit(3)==true &&
						   					monPlateau.getCase(ligne+1, colonne).getTabDroit(1)==true &&
						   					monPlateau.getCase(ligne+1, colonne).getFlag()==0)
						   				{
						   					L1=1;
						   					C1=0;
						   					S=3;
						   					E=1;
						   					Ok=true;
						   				}
						   			break;
						   			
						   		case 4: //gauche
						   			if(colonne>0 && maCase.getTabDroit(4)==true &&
						   					monPlateau.getCase(ligne, colonne-1).getTabDroit(2)==true &&
						   					monPlateau.getCase(ligne, colonne-1).getFlag()==0)
						   				{
						   					L1=0;
						   					C1=-1;
						   					S=4;
						   					E=2;
						   					Ok=true;
						   				}	
						   			break;
					   		}
					   if(Ok==true)
					   		{
						   		monPlateau.getCase(ligne, colonne).setSortie(S);
						   		ligne=ligne+L1;
						   		colonne=colonne+C1;
						   		monPlateau.getCase(ligne, colonne).setEntree(E);
						   		monPlateau.getCase(ligne, colonne).setFlag(1);
						   		
						   		fonction(ligne,colonne);
						   		
						   		ligne=ligne-L1;
						   		colonne=colonne-C1;
					   		}
				   }
			   }
	   }
}
