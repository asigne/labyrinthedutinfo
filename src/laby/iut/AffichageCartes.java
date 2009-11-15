package laby.iut;

import java.util.ArrayList;

import Java.*;
import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class AffichageCartes extends Activity {
    
	Utilisateur j1, j2, j3, j4;
	Object monJoueur;
	Partie partie1;
    Button retour;
    
    TextView nbCartesRestantes;
    
    ArrayList<Carte> listeCarte;
       
	public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	setFullscreen();
        setContentView(R.layout.affichagecartesjoueur);
        nbCartesRestantes = (TextView) findViewById(R.id.nbCartesRestantes);
        
        listeCarte = new ArrayList<Carte>();    
        
					        //Intent getIntent=getIntent();
					        //Bundle objet = this.getIntent().getExtras();
					      //  listeCarte = objet.getParcelableArrayList("listeCarte");
					        //monJoueur = objetarret.
        
        j1=new Utilisateur("j1");
        j2=new Utilisateur("j2");
        //j3=new Utilisateur("j3");
		//j4=new Utilisateur("j4");
        partie1 = new Partie("partie1");
        
        j1.RejoindrePartie(partie1);	
        j2.RejoindrePartie(partie1);
        //j3.RejoindrePartie(partie1);				    
              
        partie1.lancerPartie();
          
        
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        int nbCartes=j1.getListCarte().size();
        nbCartesRestantes.setText("Nombre de cartes restantes : "+(nbCartes-1));
        gridview.setAdapter(new ImageAdapter(this, j1, nbCartes));
          
         
         
	}
	
	public void setFullscreen() { 
	    requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	            WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	} 
    
    
}