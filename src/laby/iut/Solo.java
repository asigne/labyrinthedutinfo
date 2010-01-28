package laby.iut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Solo extends Activity {

	Spinner sRegle, sDifficulte;
	@SuppressWarnings("unchecked")
	ArrayAdapter aRegle, aDifficulte;
	Button lancerPartie;
	EditText editPseudo;
	String pseudo, regle, difficulte, typePartie;
    
    public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setFullscreen();
		
		setContentView(R.layout.solo);
	      
        sRegle = (Spinner) findViewById(R.id.regle);
        aRegle = ArrayAdapter.createFromResource(
                this, R.array.regle, android.R.layout.simple_spinner_item);
        aRegle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sRegle.setAdapter(aRegle);
        
        sDifficulte = (Spinner) findViewById(R.id.difficulte);
        aDifficulte = ArrayAdapter.createFromResource(
                this, R.array.difficulte, android.R.layout.simple_spinner_item);
        aDifficulte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDifficulte.setAdapter(aDifficulte);
        
        lancerPartie = (Button) findViewById(R.id.jouer);
        
        lancerPartie.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        		{   
        			//editPseudo = (EditText) findViewById(R.id.editpseudo);
        			regle = (String) sRegle.getSelectedItem();
        			difficulte = (String) sDifficulte.getSelectedItem();
        			/*pseudo = editPseudo.getEditableText().toString();
        			if (pseudo.length()==0 || pseudo.length()>10){
        				CharSequence text = "pseudo entre 1 et 10 caracteres";
        				int duration = Toast.LENGTH_SHORT;
        				Context context = getApplicationContext();
        				Toast toast = Toast.makeText(context, text, duration);
        				toast.show();
        			}
        			else{*/
        				lancerPartie();
        			//}
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
		typePartie="solo";
		//objetbundle.putString("pseudo", pseudo);
		objetbundle.putString("regle", regle);
		objetbundle.putString("difficulte", difficulte);
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

