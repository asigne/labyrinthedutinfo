package laby.iut;

import Java.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;


public class AffichageCartes extends Activity {
    /** Called when the activity is first created. */
    
    Utilisateur j1, j2, j3, j4;
    Partie partie1;
       
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichagecartes);

        j1=new Utilisateur("j1");
        j2=new Utilisateur("j2");
        j3=new Utilisateur("j3");
        j4=new Utilisateur("j4");
        partie1 = new Partie("partie1");
        
        partie1.ajouterJoueur(j1);
        partie1.ajouterJoueur(j2);
        partie1.ajouterJoueur(j3);
        partie1.ajouterJoueur(j4);
        
        partie1.lancerPartie();
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, j1));
        //gridview.setAdapter(new ImageAdapter(this));
    }
}