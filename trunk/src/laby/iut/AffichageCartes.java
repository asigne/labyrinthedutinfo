package laby.iut;

import java.util.ArrayList;

import Java.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class AffichageCartes extends Activity {
    
	Utilisateur j1, j2, j3, j4;
	Object monJoueur;
	Partie partie1;
    Button retour;
    
    TextView Text01;
    Bundle objetarret;
    
    Object listeCarte;
       
	public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.affichagecartesjoueur);
        retour = (Button) findViewById(R.id.retour);
        Text01 = (TextView) findViewById(R.id.TextView01);
        
        objetarret  = this.getIntent().getExtras(); 
        //listeCarte = new ArrayList<Carte>();
        //listeCarte = objetarret.getParcelableArrayList("listeCarte");
        
        //monJoueur = objetarret.
        
        j1=new Utilisateur("j1");
        j2=new Utilisateur("j2");
        j3=new Utilisateur("j3");
        j4=new Utilisateur("j4");
        partie1 = new Partie("partie1");
        
        partie1.ajouterJoueur(j1);
        partie1.ajouterJoueur(j2);
        //partie1.ajouterJoueur(j3);
        //partie1.ajouterJoueur(j4);
        
        partie1.lancerPartie();
          
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, j1));
        
        
        
        retour.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
				retourJeu();
            }
        });  
    }
    
    
    private void retourJeu() {
    	Text01.setText("retour");
    	//Intent defineIntent = new Intent(this, Labyrinthe.class);
    	//startActivity(defineIntent);
    }
    
    
    
}