package Java;

import Java.Case;

@SuppressWarnings("serial")
public class T extends Case
{	
	public T(int identifiant, int rotation, int noImage)
		{
			super(identifiant, noImage);
			this.tabDroit[1]=false;
			this.tabDroit[2]=true;
			this.tabDroit[3]=true;
			this.tabDroit[4]=true;
			this.tabDroit[0]=false;
			rotate(rotation);
		}
	
	public String toString()
		{
			return "T "+super.toString();
		}
}
