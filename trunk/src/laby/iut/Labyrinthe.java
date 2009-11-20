package laby.iut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Labyrinthe extends Activity {

    Button nouvelle, charger, parametres, apropos, aide, quitter;
    
    public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);
	      setFullscreen();
	      setContentView(R.layout.accueil);
	      
	      
	      initDesID();
	      

	      	      
	      nouvelle.setOnClickListener(new View.OnClickListener()
	         {
	        	 public void onClick(View v)
	             {   
	        		 nouvellePartie();
	             }
	         });
	      
	      charger.setOnClickListener(new View.OnClickListener()
	         {
	        	 public void onClick(View v)
	             {   
	        		 ChargerPartie();
	             }
	         });
	      
	      parametres.setOnClickListener(new View.OnClickListener()
	         {
	        	 public void onClick(View v)
	             {   
	        		 
	             }
	         });
	      
	      apropos.setOnClickListener(new View.OnClickListener()
	         {
	        	 public void onClick(View v)
	             {   
	        		 
	             }
	         });
	      
	      aide.setOnClickListener(new View.OnClickListener()
	         {
	        	 public void onClick(View v)
	             {   
	        		 
	             }
	         });
	      
	      quitter.setOnClickListener(new View.OnClickListener()
	         {
	        	 public void onClick(View v)
	             {   
	        		 finish();
	             }
	         });
 
	    }
    
	public void initDesID() {
		nouvelle = (Button) findViewById(R.id.nouvelle);
		charger = (Button) findViewById(R.id.charger);
		parametres = (Button) findViewById(R.id.parametres);
		apropos = (Button) findViewById(R.id.apropos);
		aide = (Button) findViewById(R.id.aide);
		quitter = (Button) findViewById(R.id.quitter);
	}

	public void nouvellePartie() {
		//creation de l'intent
		Intent defineIntent = new Intent(this, NouvellePartie.class);
		
		//lancement de la nouvelle activity
		startActivity(defineIntent);
	}
	
	public void ChargerPartie() {
		//creation de l'intent
		//Intent defineIntent = new Intent(this, Nouvelle.class);
		
		//lancement de la nouvelle activity
		//startActivity(defineIntent);
	}
	
	public void setFullscreen() { 
	    requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	} 
}