package Java;

public class Coup {
	Case maCase;
	int modif; // numero de ligne ou de colonne
	String sens; // sens de l'insertion de la case courante dans le plateau
	
	public Coup(Case maCase, int modif, String sens)
	{
		this.maCase=maCase;
		this.modif=modif;
		this.sens=sens;		
	}
}
