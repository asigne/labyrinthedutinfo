package Java;

import java.util.ArrayList;

public abstract class Case
{
	int identifiant; //identifiant pour les cases possédante une image sinon 0
	ArrayList<Pion> ListPion; //liste des pions situés sur cette case
	int rotation; //indice de rotation par rapport à la position originale
	int noImage; //numero de l'image ou 0 sinon
	boolean tabDroit[]; //tableau contenant les possibilité de sortie de la case

	//attributs utilisés lors de la recherche de chemin possible
	int flag; //indique si la case est accessible ou non
	//pour les attributs entree et sortie les valeurs possibles sont : 1 pour haut, 2 pour droite, 3 pour bas et 4 pour gauche
	int entree; //indique l'entree de la case
	int sortie; // indique la sortie de la case
	
	
	public Case(int identifiant, int noImage)
		{
			this.noImage=noImage;
			this.identifiant=identifiant;
			tabDroit=new boolean[5];
			rotation=0;
			entree=-1;
			sortie=0;
			flag=0;
			ListPion = new ArrayList<Pion>();
		}
	
	public String toString()
		{
			return tabDroit[1]+" "+tabDroit[2]+" "+tabDroit[3]+" "+tabDroit[4]+" "+this.rotation+" "+this.flag;
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

	public ArrayList<Pion> getListPion()
		{
			return this.ListPion;
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

	public void ajouterPion(Pion newPion)
	{
		ListPion.add(newPion);
	}
	
	public void supprPion(Pion oldPion)
	{
		ListPion.remove(oldPion);
	}
}
