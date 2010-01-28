package laby.iut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Pseudos3 extends Activity {

	Button jouer;
	EditText pseudo1, pseudo2, pseudo3;
	String regle, pseudoJ1, pseudoJ2, pseudoJ3, typePartie;
	int nbJoueurs;
    
    public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setFullscreen();
		
		setContentView(R.layout.pseudos3j);
		// récuperation du pseudo et des parametres de la partie
 		Bundle objetParametre  = this.getIntent().getExtras();     
		nbJoueurs=objetParametre.getInt("nbJoueurs");
		regle = objetParametre.getString("regle");
		typePartie=objetParametre.getString("typePartie");
        jouer = (Button) findViewById(R.id.jouer);
        jouer.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
	        	{   
	        		pseudo1 = (EditText) findViewById(R.id.pseudoJoueur1);
	    			pseudoJ1 = pseudo1.getEditableText().toString();
	    			pseudo2 = (EditText) findViewById(R.id.pseudoJoueur2);
	    			pseudoJ2 = pseudo2.getEditableText().toString();	
	    			pseudo3 = (EditText) findViewById(R.id.pseudoJoueur3);
	    			pseudoJ3 = pseudo3.getEditableText().toString();	
	        		if (pseudoJ1.length()==0 || pseudoJ1.length()>10 || pseudoJ2.length()==0 || pseudoJ2.length()>10 
	        				|| pseudoJ3.length()==0 || pseudoJ3.length()>10){
	    				CharSequence text = "pseudo entre 1 et 10 caracteres";
	    				int duration = Toast.LENGTH_SHORT;
	    				Context context = getApplicationContext();
	    				Toast toast = Toast.makeText(context, text, duration);
	    				toast.show();
	    			}
	        		else
	        		{
	        			lancerPartie();
	        		}
        		}		
        });
    }
    
    public void lancerPartie() {
   	
		//creation de l'intent
		Intent defineIntent = new Intent(this, PlateauJeu.class);
		
		// et de parametres
		Bundle objetbundle = new Bundle();
		
		String mode="nouvellePartie";
		objetbundle.putString("mode", mode);
		objetbundle.putString("pseudoJ1", pseudoJ1);
		objetbundle.putString("pseudoJ2", pseudoJ2);
		objetbundle.putString("pseudoJ3", pseudoJ3);
		objetbundle.putString("typePartie", typePartie);
		objetbundle.putString("regle", regle);
		objetbundle.putInt("nbJoueurs", nbJoueurs);
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

