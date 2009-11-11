package Java;

import java.util.ArrayList;

public abstract class Case
{
	int identifiant; //identifiant pour les cases possédante une image sinon 0
	int rotation; //indice de rotation par rapport à la position originale
	int noImage; //numero de l'image ou 0 sinon
	boolean tabDroit[]; //tableau contenant les possibilité de sortie de la case

	//attributs utilisés lors de la recherche de chemin possible
	int flag; //indique si la case est accessible ou non
	//pour les attributs entree et sortie les valeurs possibles sont : 1 pour haut, 2 pour droite, 3 pour bas et 4 pour gauche
	int entree; //indique l'entree de la case
	int sortie; // indique la sortie de la case
	
	
	ArrayList<Joueur> ListJoueur;

	public Case(int identifiant, int noImage)
		{
			this.noImage=noImage;
			this.identifiant=identifiant;
			tabDroit=new boolean[5];
			rotation=0;
			entree=-1;
			sortie=0;
			flag=0;
			ListJoueur = new ArrayList<Joueur>();
		}
	
	public Case sauvCase()
		{
			Case sauvCase; 
			if(this instanceof L)
			{
				sauvCase=new L(0,0,0);
			}
			else if(this instanceof T)
			{
				sauvCase=new T(0,0,0);
			}
			else
			{
				sauvCase=new I(0);
			}
			
			sauvCase.noImage=this.noImage;
			sauvCase.identifiant=this.identifiant;
			sauvCase.tabDroit=this.tabDroit;
			sauvCase.rotation=this.rotation;
			sauvCase.entree=this.entree;
			sauvCase.sortie=this.sortie;
			sauvCase.flag=this.flag;
			sauvCase.ListJoueur=this.ListJoueur;	
			return sauvCase;
		}
	
	
	public String toString()
		{
			//return tabDroit[1]+" "+tabDroit[2]+" "+tabDroit[3]+" "+tabDroit[4]+" "+this.rotation+" "+this.flag;
			return ""+ListJoueur;
		}
	
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
		
	//methode retournant le droit associé au rang fourni
	public boolean getTabDroit(int rang) {
		return tabDroit[rang];
	}

	public int getNoImage()
		{
			return this.noImage;
		}
	
	//methode permettant de faire tourner la case de 'indice' degrés
	//l'indice rotation  et les droits de la case sont modifiés
	public void rotate(int indice)
	{
		for(int i=0;i<indice;i=i+90)
		{
				tabDroit[0]=tabDroit[4];	//droit temporaire pour l'échange
				if(rotation<270)
					{
						rotation=rotation+90;	//MaJ de rotation
					}
				else
					{
						rotation=0;				//360° correspond a 0°
						
					}	
				for(int j=4;j>0;j--)	
				{
					this.tabDroit[j]=this.tabDroit[j-1];	//MaJ des droits
				}
		}
	}

	public int getEntree()
	{
		return this.entree;
	}
	
	public int getSortie()
	{
		return this.sortie;
	}
	
	public void setEntree(int entree) {
		this.entree = entree;
	}

	public void setSortie(int sortie) {
		this.sortie = sortie;
	}
		
	public int getRotation() {
		return this.rotation;
	}

	public void ajouterJoueur(Joueur newJoueur)
	{
		ListJoueur.add(newJoueur);
	}
	
	public void supprJoueur(Joueur oldJoueur)
	{
		ListJoueur.remove(oldJoueur);
	}

	public ArrayList<Joueur> getListJoueur() {
		return ListJoueur;
	}

	public void setListJoueur(ArrayList<Joueur> listJoueur) {
		ListJoueur = listJoueur;
	}

	public int getIdentifiant() {
		return identifiant;
	}	
	

}
