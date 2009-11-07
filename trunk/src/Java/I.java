package Java;

import Java.Case;

public class I extends Case
{
	public I(int rotation)
		{
			super(0, 0);
			this.tabDroit[1]=true;
			this.tabDroit[2]=false;
			this.tabDroit[3]=true;
			this.tabDroit[4]=false;
			this.tabDroit[0]=false;
			rotate(rotation);
			
		}
	public String toString()
		{
			return "I "+super.toString();
		}
}
