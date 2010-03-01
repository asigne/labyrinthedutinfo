package Java;



@SuppressWarnings("serial")
public class I extends Case {
	String nom;
	
	public I(int rotation) {
		super(1000, 0);
		nom="aaa";
		this.tabDroit[1] = true;
		this.tabDroit[2] = false;
		this.tabDroit[3] = true;
		this.tabDroit[4] = false;
		this.tabDroit[0] = false;
		rotate(rotation);
	}

	@Override
	public String toString() {
		return "I " + super.toString();
	}
}
