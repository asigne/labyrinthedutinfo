package Java;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public abstract class Case implements Serializable, Cloneable
{
	int identifiant; //identifiant pour les cases possédante une image sinon 0
	int rotation; //indice de rotation par rapport à la position originale
	int noImage; //numero de l'image ou 0 sinon
	boolean tabDroit[]; //tableau contenant les possibilité de sortie de la case

	//attributs utilisés lors de la recherche de chemin possible
	int flag; //indique si la case est accessible ou non
	int flagEnEntrant; //indique les cases du chemin actuel
	
	//pour les attributs entree et sortie les valeurs possibles sont : 1 pour haut, 2 pour droite, 3 pour bas et 4 pour gauche
	//int entree; //indique l'entree de la case
	int sortie; // indique la sortie de la case
	
	
	ArrayList<Joueur> ListJoueur;

	public Case(int identifiant, int noImage)
		{
			this.noImage=noImage;
			this.identifiant=identifiant;
			tabDroit=new boolean[5];
			rotation=0;
			//entree=-1;
			sortie=0;
			flag=0;
			ListJoueur = new ArrayList<Joueur>();
		}
	
	public String toString()
		{
			//return tabDroit[1]+" "+tabDroit[2]+" "+tabDroit[3]+" "+tabDroit[4]+" "+this.rotation+" "+this.flag;
			//return ""+noImage+" "+rotation;//ListJoueur;
			return ""+flag+" "+rotation+" "+ListJoueur;
			//return ListJoueur+" ";
		}
	
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
		
	public void setFlagEnEntrant(int flagEnEntrant) {
		this.flagEnEntrant = flagEnEntrant;
	}
	
	public int getFlagEnEntrant() {
		return flagEnEntrant;
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

	public int getSortie()
	{
		return this.sortie;
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
	
	public Case sauvCase()
	{
		Case sauvCase; 
		Case caseASauv=this;
		if(caseASauv instanceof L)
		{
			sauvCase=new L(0,0,0); //creation d'une nouvelle case L si la case a sauver est de type L
		}
		else if(caseASauv instanceof T)
		{
			sauvCase=new T(0,0,0); //creation d'une nouvelle case T si la case a sauver est de type T
		}
		else
		{
			sauvCase=new I(0); //creation d'une nouvelle case I si la case a sauver est de type I
		}
		
		//copie de tous les attributs
		sauvCase.noImage=caseASauv.noImage;
		sauvCase.identifiant=caseASauv.identifiant;
		sauvCase.tabDroit=caseASauv.tabDroit;
		sauvCase.rotation=caseASauv.rotation;
		sauvCase.sortie=caseASauv.sortie;
		sauvCase.flag=caseASauv.flag;
		//copie de la liste des joueurs
		for(int i=0; i<caseASauv.ListJoueur.size(); i++)
			{
				sauvCase.ListJoueur.add(caseASauv.ListJoueur.get(i));
			}
		return sauvCase;
	}
}
