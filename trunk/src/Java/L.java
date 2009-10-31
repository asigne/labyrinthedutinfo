package Java;

import Java.Case;

public class L extends Case
{
	public L(int rotation, int noImage)
		{
			super(noImage);
			this.tabDroit[1]=true;
			this.tabDroit[2]=true;
			this.tabDroit[3]=false;
			this.tabDroit[4]=false;
			this.tabDroit[0]=false;
			rotate(rotation);

		}
	public String toString()
		{
			return "L "+super.toString();
		}
}
