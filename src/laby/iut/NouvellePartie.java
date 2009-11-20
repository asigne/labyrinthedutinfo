package laby.iut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class NouvellePartie extends Activity {

	Button newSolo, newMulti, rejMulti;
	
	public void onCreate(Bundle savedInstanceState) {  
	      super.onCreate(savedInstanceState);
	      setFullscreen();
	      setContentView(R.layout.nouvellepartie);
	      

	      newSolo = (Button) findViewById(R.id.newSolo);
	      newMulti = (Button) findViewById(R.id.newMulti);
	      //rejMulti = (Button) findViewById(R.id.rejMulti);
	      
	      newSolo.setOnClickListener(new View.OnClickListener()
	         {
	        	 public void onClick(View v)
	             {   
	        		 newSolo();
	             }
	         });
	}
	
	
	public void newSolo() {
		//creation de l'intent
		Intent defineIntent = new Intent(this, Solo.class);
		
		//lancement de la nouvelle activity
		startActivity(defineIntent);
	}
	
	public void setFullscreen() { 
	    requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	            WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	}  
}
