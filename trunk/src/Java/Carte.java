package Java;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Carte implements Serializable{
	
	int identifiant;
	
	public Carte(int identifiant)
	{
		this.identifiant=identifiant;
	}
	
	public String toString()
	{
		return (""+this.identifiant);
	}

	public int getIdentifiant() {
		return identifiant;
	}
	
	
}
