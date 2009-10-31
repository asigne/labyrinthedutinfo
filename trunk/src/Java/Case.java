package Java;

import java.util.ArrayList;

public abstract class Case
{
	ArrayList<Pion> ListPion;
	int rotation;
	int noImage;
	boolean tabDroit[];

	int entree;
	int sortie;
	int flag;
	
	public Case(int noImage)
		{
			this.noImage=noImage;
			tabDroit=new boolean[5];
			rotation=0;
			entree=-1;
			sortie=0;
			flag=0;
		}
	
	public String toString()
		{
			//return this.rotation+" "+this.noImage;
			return tabDroit[1]+" "+tabDroit[2]+" "+tabDroit[3]+" "+tabDroit[4]+" "+this.rotation+" "+this.flag;
		}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
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
	
	public void rotate(int indice)
	{
				for(int i=0;i<indice;i=i+90)
				{
						tabDroit[0]=tabDroit[4];
						if(rotation<270)
							{
								rotation=rotation+90;
							}
						else
							{
								rotation=0;
								
							}	
						for(int j=4;j>0;j--)
						{
							this.tabDroit[j]=this.tabDroit[j-1];
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
