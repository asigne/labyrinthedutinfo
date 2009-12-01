package laby.iut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PartieLocale extends Activity {

	Spinner sRegle, sNbJoueurs;
	@SuppressWarnings("unchecked")
	ArrayAdapter aRegle, aNbJoueurs;
	Button continuer;
	String regle, nbJoueurs;
	int joueurs;
	String typePartie;
    
    public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setFullscreen();
		
		Bundle objetParametre  = this.getIntent().getExtras(); 
 		typePartie = objetParametre.getString("typePartie");
		
		setContentView(R.layout.partielocale);
	      
        sRegle = (Spinner) findViewById(R.id.regle);
        aRegle = ArrayAdapter.createFromResource(
                this, R.array.regle, android.R.layout.simple_spinner_item);
        aRegle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sRegle.setAdapter(aRegle);
        
        sNbJoueurs = (Spinner) findViewById(R.id.nbJoueurs);
        aNbJoueurs = ArrayAdapter.createFromResource(
                this, R.array.nbJoueurs, android.R.layout.simple_spinner_item);
        aNbJoueurs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNbJoueurs.setAdapter(aNbJoueurs);
        
        continuer = (Button) findViewById(R.id.continuer);
        
        continuer.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        		{   
        			continuer();
        		}		
        });
    }
    
    public void continuer() {
    	Intent defineIntent = null;
    	regle = (String) sRegle.getSelectedItem();
		nbJoueurs = (String) sNbJoueurs.getSelectedItem();
		if(nbJoueurs.equals("2 joueurs"))
		{
			joueurs=2;
			defineIntent = new Intent(this, Pseudos2.class);
		}
		else if(nbJoueurs.equals("3 joueurs"))
		{
			joueurs=3;
			defineIntent = new Intent(this, Pseudos3.class);
		}
		else
		{
			joueurs=4;
			defineIntent = new Intent(this, Pseudos4.class);
		}
    	
		Bundle objetbundle = new Bundle();
		objetbundle.putString("regle", regle);
		objetbundle.putInt("nbJoueurs", joueurs);
		objetbundle.putString("typePartie", typePartie);
		defineIntent.putExtras(objetbundle);
		
		//lancement de la nouvelle activity
		startActivity(defineIntent);
	}
    
    
    public void setFullscreen() { 
	    requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	            WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	} 
}

