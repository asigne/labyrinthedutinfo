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

public class Parametre extends Activity {

	Spinner sVibreur, sCaseRouge, sNotification;	
	ArrayAdapter aVibreur, aCaseRouge, aNotification;
	Button valider;
	
	    
    public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setFullscreen();
		
		//Bundle objetParametre  = this.getIntent().getExtras(); 
 		//typePartie = objetParametre.getString("typePartie");
		
		setContentView(R.layout.parametre);
	     
		sVibreur = (Spinner) findViewById(R.id.vibreur);
        aVibreur = ArrayAdapter.createFromResource(
                this, R.array.vibreur, android.R.layout.simple_spinner_item);
        aVibreur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sVibreur.setAdapter(aVibreur);
        
        sNotification = (Spinner) findViewById(R.id.notification);
        aNotification = ArrayAdapter.createFromResource(
                this, R.array.notification, android.R.layout.simple_spinner_item);
        aNotification.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sNotification.setAdapter(aNotification);
        
        
        sCaseRouge = (Spinner) findViewById(R.id.caseRouge);
        aCaseRouge = ArrayAdapter.createFromResource(
                this, R.array.caseRouge, android.R.layout.simple_spinner_item);
        aCaseRouge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCaseRouge.setAdapter(aCaseRouge);
        
        
        valider = (Button) findViewById(R.id.valider);
        
        valider.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        		{   
        			//valider();
        		}		
        });
    }
    /*
    public void valider() {
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
    */
    
    public void setFullscreen() { 
	    requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	            WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	}
}

