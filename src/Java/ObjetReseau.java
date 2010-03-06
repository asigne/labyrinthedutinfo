package Java;

import java.io.Serializable;

public class ObjetReseau implements Serializable {
	Coup Coup;
	int deplacementX, deplacementY;

	public ObjetReseau() {
		Coup = null;
		deplacementX = 99;
		deplacementY = 55;
	}

	public void affiche() {
		System.out.println(Coup);
		System.out.println("deplacementX" + deplacementX);
		System.out.println("deplacementY" + deplacementY);
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
