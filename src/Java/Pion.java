package Java;

public class Pion {
	int identifiant;
	String couleur;
	int ligne;
	int colonne;
	Case estSur;
	


	public Pion(int identifiant, String couleur)
		{
			this.identifiant=identifiant;
			this.couleur=couleur;
			estSur=null;
		}
	
	public String getCouleur()
		{
			return this.couleur;
		}	
	
	public int getIdentifiant() {
		return identifiant;
	}
	
	
	
	
}
