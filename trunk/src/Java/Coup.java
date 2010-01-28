package Java;

public class Coup {
	Case maCase;
	int modif; // numero de ligne ou de colonne
	String sens; // sens de l'insertion de la case courante dans le plateau

	public Coup() {

	}

	public Coup(Case maCase, int modif, String sens) {
		this.maCase = maCase;
		this.modif = modif;
		this.sens = sens;
	}

	@Override
	public String toString() {
		return "case:" + maCase + " modif:" + modif + " sens:" + sens;
	}

	public Coup sauvCoup() {
		Coup coupASauv = this;
		Coup coupSauv = new Coup();

		coupSauv.maCase = coupASauv.maCase.sauvCase();
		coupSauv.modif = coupASauv.modif;
		coupSauv.sens = coupASauv.sens;

		return coupSauv;
	}
}
