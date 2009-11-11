package Java;

public class Serveur {
	String nom;
	Partie ListPartie[];
	int nbPartie;
	int nbPartieMax;
	
	public Serveur(String nom)
	{
		this(nom, 5);		
	}
	
	public Serveur(String nom, int nbPartieMax)
	{
		this.nom=nom;
		this.nbPartie=0;
		this.nbPartieMax=nbPartieMax;
		ListPartie = new Partie[nbPartieMax];
	}
		
	
	public void creerPartie(String nomPartie)
	{
		if(nbPartie<nbPartieMax)
			{
				nbPartie++;
				ListPartie[nbPartie] = new Partie(nomPartie);
			}
		else
			{
				//erreur trop de partie sur le serveur
			}
	}
}
