package Java;

public class ObjetIA {
	
	Plateau Plateau;
	Coup Coup;
	int deplacementX, deplacementY;

	
	public ObjetIA()
	{
		Plateau=null;
		Coup=null;
		deplacementX=99;
		deplacementY=55;
	}

	public void affiche()
	{
		Plateau.affiche();
		System.out.println(Coup);
		System.out.println("deplacementX"+deplacementX);
		System.out.println("deplacementY"+deplacementY);
	}

	public Plateau getPlateau() {
		return Plateau;
	}


	public void setPlateau(Plateau plateau) {
		Plateau = plateau;
	}


	public Coup getCoup() {
		return Coup;
	}


	public void setCoup(Coup coup) {
		Coup = coup;
	}


	public int getDeplacementX() {
		return deplacementX;
	}


	public void setDeplacementX(int deplacementX) {
		this.deplacementX = deplacementX;
	}


	public int getDeplacementY() {
		return deplacementY;
	}


	public void setDeplacementY(int deplacementY) {
		this.deplacementY = deplacementY;
	}
	
	
}
