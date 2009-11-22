package laby.iut;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
//import java.lang.Thread;
import Java.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.os.Vibrator;

import Java.Joueur;

public class PlateauJeu extends Activity 
{
	ImageView fg1, fg3, fg5, fd1, fd3, fd5, fh1, fh3, fh5, fb1, fb3, fb5, imageCarteCourante;
	ImageView pionBleu, pionRouge, pionVert, pionJaune;
	RotateAnimation rotation0, rotation90, rotation180, rotation270;
	boolean initialisationPlateau;
	TextView Text01, textInfo, textJoueurActif;
    LinearLayout lbleu,lvert, lrouge, ljaune;
    TableLayout lPlateau;
    Button btnJouer, btnAnnuler; 
    String flecheInterdite;
    int indiceInterdit;
    
    Partie maPartie;
    Plateau monPlateau;
    Case caseCourante;	
    Coup monCoup;
    
	boolean ctrouve=false;
    
   	//Utilisateur j1, j2, j3, j4;
   	Joueur joueurActif, ia, j1, j2, j3, j4;
   	//IA ia;
   	
   	int sauvModif;
	Case sauvCaseCourante, sauvCaseSortante;
	String sauvFleche;
	String sauvFlecheInterdite;
    int sauvIndiceInterdit;
    int sauvPosLigne, sauvPosColonne;
	
    
    TextView tJ1, tJ2, tJ3, tJ4;
   	
        
    //int ligne, colonne;
    String fleche;

    boolean deplacement=false, premiereModif=true;
    boolean plateauModif=false;
    //boolean jeuFait;
    
    //parametre de l'application
    int xmin=13, xmax=307, ymin=61, ymax=355;//coordonnées du plateau de jeu
	int tailleCase = 42; //taille d'une case du plateau
	int tailleFleche = 13; //largeur fleche
	
	int indicePremiereCaseTableau=R.id.l0_c0;
	int incidePremiereImageCarte=R.drawable.ca;
	int indicePremierL=(R.drawable.l);
	int indicePremierT=(R.drawable.ta)-1;
	int indiceI=R.drawable.i;
	int indicePremierPion=R.id.lbleu;

	String pseudo, regle, difficulte;
	
	
	

public void onCreate(Bundle savedInstanceState)
    {    	    	
         super.onCreate(savedInstanceState);
         setFullscreen();
         setContentView(R.layout.jeu);   

         // récuperation du pseudo et des parametres de la partie
  		 Bundle objetParametre  = this.getIntent().getExtras(); 
  		 pseudo = objetParametre.getString("pseudo");
  		 regle = objetParametre.getString("regle");
  		 difficulte = objetParametre.getString("difficulte");
          
 
         
         initDesID();
         
                           
         maPartie=new Partie("Partie1", regle, difficulte); 			//creation de la partie     
         monPlateau=maPartie.getMonPlateau(); 		//recuperation du plateau de la partie
         caseCourante=maPartie.getCaseCourante();	//recuperation de la case courante de la partie
         
         
         				//creation des joueurs
         j1=new Utilisateur(pseudo);				
         //ia=new IA("Ordinateur");					
         j2=new Utilisateur("Rouge");				
         //j3=new Utilisateur("Vert");				
         //j4=new Utilisateur("Jaune");				
         
         				//ajout des joueurs a la partie
         j1.RejoindrePartie(maPartie);				
         //ia.RejoindrePartie(maPartie);			
         j2.RejoindrePartie(maPartie);
         //j3.RejoindrePartie(maPartie);
         //j4.RejoindrePartie(maPartie);
         
         maPartie.lancerPartie();					//lancement de la partie
         
         initPlateau2D();							//initialisation de l'affichage du plateau en 2D
         
         affichePions();   							//affichage des pions sur le plateau
         afficheCaseCourante(0);					//affichage de la case courante
         afficheScores();
         
         joueurActif=j1;
         //affichage de la carte courante du joueur
         afficheCarteCourante();
         
         //WriteSettings(this, maPartie);
		CharSequence text = "A "+joueurActif.getNom()+" de jouer !";
		int duration = Toast.LENGTH_SHORT;
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(0,0,0);
		toast.show();
		
		text = "Commencez par modifier le plateau puis déplacez votre pion";
		duration = Toast.LENGTH_LONG;
		toast = Toast.makeText(context, text , duration);
		toast.show();
         
         btnJouer.setOnClickListener(new View.OnClickListener()
         {
        	 public void onClick(View v)
             {       			
            	 tourDejeu();
             }
            	 
          
            	 
         }); 
         
         btnAnnuler.setOnClickListener(new View.OnClickListener()
         {
             public void onClick(View v)
             {
            	 if(!maPartie.getPartieFinie())
            	 {
            		 annulerDernierCoup();
            	 }
             }
         });
    }  

public void WriteSettings(Context context, Partie data){ 
    FileOutputStream fOut = null; 
    ObjectOutputStream oos = null;
    OutputStreamWriter osw = null; 

	    try{ 
	       fOut = context.openFileOutput("aa.txt",MODE_APPEND);   
	       oos = new ObjectOutputStream(fOut);
	       osw = new OutputStreamWriter(fOut);
	       //oos.writeObject(data); 
	       //oos.flush();
	        osw.write("data"); 
	        osw.flush(); 
	       //popup surgissant pour le résultat
	        Toast.makeText(context, "Settings saved",Toast.LENGTH_SHORT).show(); 
	        }
       	catch (Exception e) {       
                Toast.makeText(context, "Settings not saved",Toast.LENGTH_SHORT).show(); 
        } 
       	
        finally { 
           try { 
                 oos.close(); 
                 fOut.close(); 
           		}
           catch (IOException e) { 
                   Toast.makeText(context, "Settings not saved",Toast.LENGTH_SHORT).show(); 
          } 
        } 
   }




//methode permettant de gérer les clic sur l'écran   
public boolean onTouchEvent(MotionEvent event)
{
	if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			int x = (int) (event.getX());
			int y = (int) (event.getY());
			//textInfo.setText("x:"+x+" y:"+y);
			if(!maPartie.getPartieFinie())
			{
				//gestion du click en fonction des coordonnées
				if(x>xmin && x<=xmax && y>ymin && y<ymax && plateauModif==true)
				{
					//action sur une case du plateau
					actionCase(x, y);
					return true;
				}
				else if(x>xmin-tailleFleche && x<=xmax+tailleFleche && y>ymin-tailleFleche && y<ymax+tailleFleche)
				{
					//action sur une fleche
					if(!plateauModif)
					{		
						actionFleche(x,y);
					}
					else
					{
						textInfo.setText("Vous avez déjà modifier le plateau");
					}
					return true;
				}
				else if(x>240 && y>400)
				{
					//action sur la caseCourante
					actionCaseCourante();
					return true;
				}
				else if(x>180 && x<230 && y<480 && y>400)
				{
					//action sur la carteCourante
					actionCarteCourante();
					return true;
				}
			}
		}
	return false;
}

//recupere les ID des différents objets graphiques
public  void initDesID() {
	fg1 = (ImageView) findViewById(R.id.fg1);
    fg3 = (ImageView) findViewById(R.id.fg3);
    fg5 = (ImageView) findViewById(R.id.fg5);
    fd1 = (ImageView) findViewById(R.id.fd1);
    fd3 = (ImageView) findViewById(R.id.fd3);
    fd5 = (ImageView) findViewById(R.id.fd5);
    fh1 = (ImageView) findViewById(R.id.fh1);
    fh3 = (ImageView) findViewById(R.id.fh3);
    fh5 = (ImageView) findViewById(R.id.fh5);
    fb1 = (ImageView) findViewById(R.id.fb1);
    fb3 = (ImageView) findViewById(R.id.fb3);
    fb5 = (ImageView) findViewById(R.id.fb5); 
    pionBleu = (ImageView) findViewById(R.id.pbleu);
    pionRouge = (ImageView) findViewById(R.id.prouge);
    pionVert = (ImageView) findViewById(R.id.pvert);
    pionJaune = (ImageView) findViewById(R.id.pjaune);
    imageCarteCourante = (ImageView) findViewById(R.id.CarteCourante);
    lbleu = (LinearLayout) findViewById(R.id.lbleu);
    lvert= (LinearLayout) findViewById(R.id.lvert);
    lrouge = (LinearLayout) findViewById(R.id.lrouge);
    ljaune = (LinearLayout) findViewById(R.id.ljaune);
    //Text01 = (TextView) findViewById(R.id.textInfo);
    textInfo = (TextView) findViewById(R.id.textInfo);
    textJoueurActif = (TextView) findViewById(R.id.textJoueurActif);
    btnJouer = (Button) findViewById(R.id.Jouer);
    btnAnnuler = (Button) findViewById(R.id.Annuler);
    
    //lPlateau = (TableLayout) findViewById(R.id.Plateau);
    
    tJ1 = (TextView) findViewById(R.id.textJ1);
    tJ2 = (TextView) findViewById(R.id.textJ2);
    tJ3 = (TextView) findViewById(R.id.textJ3);
    tJ4 = (TextView) findViewById(R.id.textJ4);
    
    
}

//click sur la carteCourante
public void actionCarteCourante() {
	//recuperation de la liste des identifiants des cartes
		if(maPartie.partieEnfant())
			{	
				ArrayList<Integer> listeCarte= new ArrayList<Integer>();
				for(int i=0; i<joueurActif.getListCarte().size(); i++)
				{
					listeCarte.add(joueurActif.getListCarte().get(i).getIdentifiant());
				}
			
				//creation de l'intent
				Intent defineIntent = new Intent(this, AffichageCartes.class);
				//creation de l'objet Bundle
				Bundle objetbundle = new Bundle();
				
				//ajout de la liste de carte au bundle
				objetbundle.putIntegerArrayList("listeCarte",listeCarte);
				//ajout du bundle à l'intent
				defineIntent.putExtras(objetbundle);
				//lancement de la nouvelle activity
				startActivity(defineIntent);
			}
		else
			{
				textJoueurActif.setText("notification pas le droit");
			}
}

//click sur la caseCourante : 
public void actionCaseCourante() {
	caseCourante=maPartie.getCaseCourante();		//recuperation de la carte couante
	caseCourante.rotate(90);						//rotation de la carte
	afficheCaseCourante(150);						//affichage de la carte
}

//revoie l'indice de la colonne du plateau en fonction du click sur l'ecran
public int CoordToColonne(int x)
{
	int colonne=0;
	if(x<=xmin+tailleCase)
		{
			colonne=0;
		}
	else if(x<=xmin+tailleCase*2)
		{
			colonne=1;
		}	
	else if(x<=xmin+tailleCase*3)
	    {
	  		colonne=2;
	    }
	else if(x<=xmin+tailleCase*4)
	    {
			colonne=3;
	    }
	else if(x<=xmin+tailleCase*5)
		{
	   		colonne=4;
	    }
	else if(x<=xmin+tailleCase*6)
	    {
			colonne=5;
	    }
	else if(x<=xmin+tailleCase*7)
	    {
	    	colonne=6;
	    }
	return colonne;
}

//revoie l'indice de la ligne du plateau en fonction du click sur l'ecran
public int CoordToLigne(int y)
{
	int ligne=0;
	if(y<=ymin+tailleCase)
		{
			ligne=0;
		}	
	else if(y<=ymin+tailleCase*2)
		{
			ligne=1;
		}
	else if(y<=ymin+tailleCase*3)
		{
			ligne=2;
		}
	else if(y<=ymin+tailleCase*4)
		{
			ligne=3;
		}
	else if(y<=ymin+tailleCase*5)
		{
			ligne=4;
		}
	else if(y<=ymin+tailleCase*6)
		{
			ligne=5;
		}
	else if(y<=ymin+tailleCase*7)
		{
			ligne=6;
		}		
	return ligne;
}

//click sur une case du plateau
public void actionCase(int x, int y)
{
	if(plateauModif) 					//plateau doit etre modifier avec de deplacer un pion
	{
		int ligne=CoordToLigne(y);		//converti le click en ligne
		int colonne=CoordToColonne(x);	//converti le click en colonne
		if(joueurActif.seDeplacer(ligne, colonne))	//le joueur doit pouvoir aller sur une case
			{
				deplacement=true;	//deplacement est vrai
				affichePions();		// affichage des pions sur le plateau
			 	ctrouve=joueurActif.testCarteTrouvee();	 	
			 	if(ctrouve==true){
			 		ctrouve=false;
		 			Context context = getApplicationContext();
			 		
		 			CharSequence text = "Carte trouvée!";
		 			int duration = Toast.LENGTH_LONG;
		 			Toast toast = Toast.makeText(context, text, duration);
		 			
		 			CharSequence text1 = "Appuyer sur <JOUER> pour finir le tour";
		 			int duration1 = Toast.LENGTH_LONG;
		 			Toast toast1 = Toast.makeText(context, text1, duration1);
		 			
		 			toast.setGravity(0,0,0);
		 			toast.show();	 
		 			toast1.show();	 
			 	}
			}
		else
			{
				CharSequence text = "Deplacement interdit";
				int duration = Toast.LENGTH_SHORT;
				Context context = getApplicationContext();
			    Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				
			}
	}
	else
	{
		CharSequence text = "Vous devez modifier le plateau avant de vous deplacer";
		int duration = Toast.LENGTH_LONG;
		Context context = getApplicationContext();
	    Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		//textInfo.setText("Vous devez modifier le plateau avant de vous deplacer");
	}
}

//click sur une fleche autour du plateau
public boolean actionFleche(int x, int y)
{	
	if(!deplacement)	//modif interdite apres le deplacement du pion
	{
		// recuperation de la ligne ou de la colonne (fleche, modif)
		int modif=0;
		if(x>xmin-tailleFleche && x<xmin+tailleCase)
		{
			fleche="gauche";
			modif=CoordToLigne(y);
		}
		else if(x>xmax-tailleCase && x<xmax+tailleFleche)
		{
			fleche="droite";
			modif=CoordToLigne(y);
		}
		else if(y>ymin-tailleFleche && y<ymin+tailleCase)
		{
			fleche="haut";
			modif=CoordToColonne(x);;
		}
		else if(y>ymax-tailleCase && y<ymax+tailleFleche)
		{
			fleche="bas";
			modif=CoordToColonne(x);
		}		
		
		
		if(modif==indiceInterdit && fleche==flecheInterdite)
		{
			CharSequence text = "Action interdite";
			int duration = Toast.LENGTH_SHORT;
			Context context = getApplicationContext();
		    Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		else
		{
			if(modif==1 || modif==3 || modif==5)
				{				
					btnAnnuler.setVisibility(0);	//cache le bouton "annuler"
					sauvModif=modif;
					sauvFleche=fleche;
					sauvFlecheInterdite=flecheInterdite;
					sauvIndiceInterdit=indiceInterdit;
					
					//creation de la modification du plateau
					monCoup=((Utilisateur) joueurActif).genererCoup(caseCourante, modif, fleche);
					//modification du plateau
					joueurActif.modifierPlateau(monCoup);
					//deplacement des joueurs situes sur les cases mobiles concernées
					traitementJoueurSurCaseMobile(modif, fleche);
					//verouillage de la fleche interdite
					lockFleche(fleche,modif);
					//recuperation de la nouvelle caseCourante
					caseCourante=maPartie.getCaseCourante();
					
					sauvPosLigne=joueurActif.getPosLigne();
					sauvPosColonne=joueurActif.getPosColonne();
					sauvCaseSortante=caseCourante.sauvCase();
					//affichage du nouveau plateau
					MaJPlateau(modif, fleche);
					//affichage de la nouvelle carteCourante
					afficheCaseCourante(0);	
					plateauModif=true;		//le plateau a été modifié
					joueurActif.testCasesAccessibles();	//test des cases accessibles par le joueurActif
					return true;
				}
		}
	}
	else
	{
		CharSequence text = "Vous ne pouvez pas modifier le plateau après avoir déplacé votre pion !";
		int duration = Toast.LENGTH_SHORT;
		Context context = getApplicationContext();
	    Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	return false;
}

//permet d'annuler la derniere modification du plateau
public void annulerDernierCoup()
{
	if(!deplacement || (sauvPosLigne==joueurActif.getPosLigne() && sauvPosColonne==joueurActif.getPosColonne()))
		// le joueur ne peut pas annuler s'il a déplacé son pion
		{
			if(sauvFleche=="haut")
			{
				fleche="bas";
			}
			else if(sauvFleche=="bas")
			{
				fleche="haut";
			}
			else if(sauvFleche=="gauche")
			{
				fleche="droite";
			}
			else if(sauvFleche=="droite")
			{
				fleche="gauche";
			}
			
			if(sauvFlecheInterdite=="haut")
			{
				flecheInterdite="bas";
			}
			else if(sauvFlecheInterdite=="bas")
			{
				flecheInterdite="haut";
			}
			else if(sauvFlecheInterdite=="gauche")
			{
				flecheInterdite="droite";
			}
			else if(sauvFlecheInterdite=="droite")
			{
				flecheInterdite="gauche";
			}
			//creation de la modification du plateau
			monCoup=((Utilisateur) joueurActif).genererCoup(sauvCaseSortante, sauvModif, fleche);
			//modification du plateau
			joueurActif.modifierPlateau(monCoup);
			//deplacement des joueurs situes sur les cases mobiles concernées
			traitementJoueurSurCaseMobile(sauvModif, fleche);
			//recuperation de la nouvelle caseCourante
			caseCourante=maPartie.getCaseCourante();
			//affichage du nouveau plateau
			MaJPlateau(sauvModif, fleche);
			//affichage de la nouvelle carteCourante
			afficheCaseCourante(0);	
			
			if(premiereModif) //si la premiere modif est annulée
			{
				unlockFleche(); //deverrouillage de toutes les flechs
			}
			else //sinon reverrouillage de la fleche precedent la modification du plateau
			{
				lockFleche(flecheInterdite, sauvIndiceInterdit);
			}
			
			plateauModif=false;
			deplacement=false;
			CharSequence text = "Déplacement annulé";
			int duration = Toast.LENGTH_SHORT;
			Context context = getApplicationContext();
		    Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			btnAnnuler.setVisibility(4);	//rend invisible le bouton annuler
		}
		else
		{
		textInfo.setText("Vous ne pouvez pas annuler la modification tant que votre joueur ne se " +
					"trouve pas où il était avant, Ligne:"+(sauvPosLigne+1)+" Colonne:"+(sauvPosColonne+1));
		}
}

//deplacement des joueurs sur les case mobiles en mouvement
public void traitementJoueurSurCaseMobile(int indice, String fleche)
{
	ArrayList<Joueur> listEnCoursDeTest=null;
	Joueur JCT; //joueur en Cours de Traitement

	if(fleche=="haut")
	{	
		for(int ligne=6; ligne>=0 ; ligne--)
		{
			listEnCoursDeTest=monPlateau.getCase(ligne, indice).getListJoueur();
			for(int i=0;i<listEnCoursDeTest.size();i++)
			{
				JCT=listEnCoursDeTest.get(i);
				JCT.modifPosition(ligne, indice);
			}		
		}	
	}
	else if(fleche=="bas")
	{
		for(int ligne=0; ligne<=6 ; ligne++)
		{
			listEnCoursDeTest=monPlateau.getCase(ligne, indice).getListJoueur();
			for(int i=0;i<listEnCoursDeTest.size();i++)
			{
				JCT=listEnCoursDeTest.get(i);
				JCT.modifPosition(ligne, indice);
			}
		}
	}
	else if(fleche=="gauche")
	{
		for(int colonne=6;colonne>=0;colonne--)
		{
			listEnCoursDeTest=monPlateau.getCase(indice, colonne).getListJoueur();
			for(int i=0;i<listEnCoursDeTest.size();i++)
			{
				JCT=listEnCoursDeTest.get(i);
				JCT.modifPosition(indice, colonne);
			}
		}
	}
	else if(fleche=="droite")
	{
		for(int colonne=0;colonne<=6;colonne++)
		{
			listEnCoursDeTest=monPlateau.getCase(indice, colonne).getListJoueur();
			for(int i=0;i<listEnCoursDeTest.size();i++)
			{
				JCT=listEnCoursDeTest.get(i);
				JCT.modifPosition(indice, colonne);
			}
		}
	}
	affichePions();
}
	
//methode desactivant la fleche opposée apres un coup
public void lockFleche(String fleche, int indice){
	indiceInterdit=indice;
	
	fb1.setVisibility(3);
	fb3.setVisibility(3);
	fb5.setVisibility(3);
	fh1.setVisibility(3);
	fh3.setVisibility(3);
	fh5.setVisibility(3);
	fg1.setVisibility(3);
	fg3.setVisibility(3);
	fg5.setVisibility(3);
	fd1.setVisibility(3);
	fd3.setVisibility(3);
	fd5.setVisibility(3);	

	if(fleche=="haut")
		{
			switch(indice)
			{
				case 1:
					fb1.setVisibility(4);
					break;
				case 3:
					fb3.setVisibility(4);
					break;
				case 5:
					fb5.setVisibility(4);
					break;
			}
			flecheInterdite="bas";
		}
	else if(fleche=="bas")
		{
			switch(indice)
			{
				case 1:
					fh1.setVisibility(4);
					break;
				case 3:
					fh3.setVisibility(4);
					break;
				case 5:
					fh5.setVisibility(4);
					break;
			}
			flecheInterdite="haut";
		}
	else if(fleche=="gauche")
		{
			switch(indice)
			{
				case 1:
					fd1.setVisibility(4);
					break;
				case 3:
					fd3.setVisibility(4);
					break;
				case 5:
					fd5.setVisibility(4);
					break;
			}
			flecheInterdite="droite";
		}
	else if(fleche=="droite")
		{
			switch(indice)
			{
				case 1:
					fg1.setVisibility(4);
					break;
				case 3:
					fg3.setVisibility(4);
					break;
				case 5:
					fg5.setVisibility(4);
					break;
			}
			flecheInterdite="gauche";
		}
}

//methode desactivant toutes les fleches
public void unlockFleche(){
	fb1.setVisibility(3);
	fb3.setVisibility(3);
	fb5.setVisibility(3);
	fh1.setVisibility(3);
	fh3.setVisibility(3);
	fh5.setVisibility(3);
	fg1.setVisibility(3);
	fg3.setVisibility(3);
	fg5.setVisibility(3);
	fd1.setVisibility(3);
	fd3.setVisibility(3);
	fd5.setVisibility(3);
	indiceInterdit=0;
	flecheInterdite="";
}

// methode initilisant les rotations
public void initRotation(int tailleImage, int duree)
   {
		//rotation 0° / 360°
	   	int centre=tailleImage/2;
		rotation0 = new RotateAnimation(0, 360, centre, centre);
		rotation0.setDuration(duree);
		rotation0.setFillAfter(true);
	   	
		//rotation 90°
		rotation90 = new RotateAnimation(0, 90, centre, centre);
		rotation90.setDuration(duree);
		rotation90.setFillAfter(true);
		
		//rotation 180°
		rotation180 = new RotateAnimation(0, 180, centre, centre);
	 	rotation180.setDuration(duree);
	 	rotation180.setFillAfter(true);
	 	
		//rotation 270°
	 	rotation270 = new RotateAnimation(0,270, centre, centre);
		rotation270.setDuration(duree);
		rotation270.setFillAfter(true);  
   }

//methode permettant d'afficher le plateau à l'initiation 
public void initPlateau2D()
   {
		int k=0;		// compteur pour selectionner l'id de la case du tableaux
		int noCase;
		ImageView ICT;
		
		for(int ligne=0;ligne<7;ligne++)
		{
			for(int colonne=0;colonne<7;colonne++)
			{
				noCase=indicePremiereCaseTableau+k;
				ICT = (ImageView) findViewById(noCase);
				afficheICT(monPlateau.getCase(ligne, colonne), ICT, 2000, 42);
				k++;
			}
		k++;
		}
		initialisationPlateau=true;
   }
   
//methode permettant de mettre a jour l'affichage du plateau
public void MaJPlateau(int modif, String sens)
{
	int k;		// compteur pour selectionner l'id de la case du tableaux
	int noCase;
	ImageView ICT;
	if(sens=="haut" || sens=="bas")
		{	
			k=modif;
			int colonne=modif;
			for(int ligne=0;ligne<7;ligne++)
				{	
					noCase=indicePremiereCaseTableau+k;
					ICT = (ImageView) findViewById(noCase);
					afficheICT(monPlateau.getCase(ligne, colonne), ICT, 0, 42);
					k=k+8;
				}	
		}
	else
		{
		k=0;
		switch(modif)
			{
				case 1:
					k=8;
					break;
				case 3:
					k=24;
					break;
				case 5:
					k=40;
					break;
			}	
			int ligne=modif;
			for(int colonne=0;colonne<7;colonne++)
				{
					noCase=indicePremiereCaseTableau+k;
					ICT = (ImageView) findViewById(noCase);
					afficheICT(monPlateau.getCase(ligne, colonne), ICT, 0, 42);
					k++;
				}
		}
}

//methode permettant d'afficher une case sur le plateau
public void afficheICT(Case caseATraiter, ImageView imageCourante, int duration, int dimensionCase)
	{
		/*description des parametres de la fonction
		 * caseATraiter permet d'acceder aux attributs de la case en cours de traintement
		 * ImageView pointe sur l'objet ImageView en cours de traitement
		 * duration est la valeur pour la durée de la rotation
		 * dimention est la dimention des cases suivant que l'on traite le plateau 
		 * 			ou la caseCourante
		 */
		int noImage;	//numero de l'image à affecter à la case en cours de traitement
		int noImageCourante; 
		
		//recuperation du numero de l'image a partir de l'intance de la case en cours de traitement
		noImageCourante=caseATraiter.getNoImage();

		//selection de l'image a afficher
		if(caseATraiter instanceof T)
		{
			noImage=indicePremierT+noImageCourante;
		}
		else if(caseATraiter instanceof L)
		{
			noImage=indicePremierL+noImageCourante;
		}
		else
		{
			noImage=indiceI;
		}
		//affichage de l'image
		imageCourante.setImageDrawable(getResources().getDrawable(noImage));
		
		//initialisation des rotations
		initRotation(dimensionCase,duration);
				
		//rotation de l'image
		int indiceRotation;
		indiceRotation=(caseATraiter.getRotation());			
		switch(indiceRotation)
		{
		case 0:
			imageCourante.setAnimation(rotation0);
			break;
		case 90:
			imageCourante.setAnimation(rotation90);
			break;
		case 180:
			imageCourante.setAnimation(rotation180);
			break;
		case 270:
			imageCourante.setAnimation(rotation270);
			break;
		}	
	}

/*
public void affichePlateau()
{
	int noCase;
	ImageView ICT;
	int k=0;
	
	for(int ligne=0; ligne<7;ligne++)
	{
		for(int colonne=0; colonne<7;colonne++)
		{	
			noCase=indicePremiereCaseTableau+k;
			ICT = (ImageView) findViewById(noCase);
			afficheICT(monPlateau.getCase(ligne, colonne), ICT, 0, 42);
			k++;
		}
		k++;
	}
}*/


//methode permettant d'afficher la case courante
public void afficheCaseCourante(int duration)
   {   
		//recuperation de la case a traiter
		Case caseATraiter=maPartie.getCaseCourante();
		//recuperation de l'ImageView a traiter
		ImageView ICT = (ImageView) findViewById(R.id.CaseCourante);
		//application de la modificiation
		afficheICT(caseATraiter, ICT, duration, 80);
   }

//methode permettant d'afficher la carte objectif du joueur
public void afficheCarteCourante()
	{
		Carte carteCourante;
		int noImageCarteCourante;
		if(!joueurActif.getListCarte().isEmpty())
			{
				carteCourante=joueurActif.getCarteObjectif();
				
			}
		else
			{
				carteCourante=new Carte(24+joueurActif.getIdentifiant());
			}
		noImageCarteCourante=incidePremiereImageCarte+carteCourante.getIdentifiant();
		imageCarteCourante.setImageDrawable(getResources().getDrawable(noImageCarteCourante));
	}

//methode permettant de mettre a jour la position des joueurs
public void affichePions()
	{
		for(int i=0; i<maPartie.getListJoueur().size();i++)
		{
			affichePion(maPartie.getListJoueur().get(i));
		}
	}

//methode permettant de mettre a jour la position d'un joueur
public void affichePion(Joueur joueurCourant)
	{
		int moitiee=tailleCase/2;
		int quart=tailleCase/4;
		int posLigne, posColonne, coordX=70, coordY=70;
		int nbJoueurCase;
		int numPion;
		LinearLayout pionCourant = null;
		
		posLigne = joueurCourant.getPosLigne();
		posColonne =joueurCourant.getPosColonne();
		numPion=joueurCourant.getIdentifiant();
		switch(numPion)
		{
			case 0:
				pionCourant= lbleu;
				lbleu.setVisibility(0);
				break;
			case 1:
				pionCourant= lrouge;
				lrouge.setVisibility(0);
				break;
			case 2:
				pionCourant= lvert;
				lvert.setVisibility(0);
				break;
			case 3:	
				pionCourant= ljaune;
				ljaune.setVisibility(0);
				break;
		}
		nbJoueurCase=monPlateau.getCase(posLigne, posColonne).getListJoueur().size();

		switch(nbJoueurCase)
		{
			case 1:
				coordX=posColonne*tailleCase+xmin+quart;
				coordY=posLigne*tailleCase+ymin+quart;
				break;
			case 2:
				if(joueurCourant.equals(monPlateau.getCase(posLigne, posColonne).getListJoueur().get(0)))
					{
					coordX=posColonne*tailleCase+xmin;
					coordY=posLigne*tailleCase+ymin;   //-50 a cause de la barre avec le nom de l'application
					}
				else
					{
						coordX=posColonne*tailleCase+xmin+moitiee;
						coordY=posLigne*tailleCase+ymin+moitiee;
					}
				break;
			case 3:
				if(joueurCourant.equals(monPlateau.getCase(posLigne, posColonne).getListJoueur().get(0)))
				{
					coordX=posColonne*tailleCase+xmin;
					coordY=posLigne*tailleCase+ymin;   //-50 a cause de la barre avec le nom de l'application
				}
				else if(joueurCourant.equals(monPlateau.getCase(posLigne, posColonne).getListJoueur().get(1)))
				{
					coordX=posColonne*tailleCase+xmin+moitiee;
					coordY=posLigne*tailleCase+ymin+moitiee;
				}
				else
				{
					coordX=posColonne*tailleCase+xmin+moitiee;
					coordY=posLigne*tailleCase+ymin;   //-50 a cause de la barre avec le nom de l'application
				}
				break;
			case 4:
				if(joueurCourant.equals(monPlateau.getCase(posLigne, posColonne).getListJoueur().get(0)))
				{
					coordX=posColonne*tailleCase+xmin;
					coordY=posLigne*tailleCase+ymin;   //-50 a cause de la barre avec le nom de l'application
				}
				else if(joueurCourant.equals(monPlateau.getCase(posLigne, posColonne).getListJoueur().get(1)))
				{
					coordX=posColonne*tailleCase+xmin+moitiee;
					coordY=posLigne*tailleCase+ymin+moitiee;
				}
				else if(joueurCourant.equals(monPlateau.getCase(posLigne, posColonne).getListJoueur().get(2)))
				{
					coordX=posColonne*tailleCase+xmin+moitiee;
					coordY=posLigne*tailleCase+ymin;   //-50 a cause de la barre avec le nom de l'application
				}
				else
				{
					coordX=posColonne*tailleCase+xmin;
					coordY=posLigne*tailleCase+ymin+moitiee;
				}
				break;
		}
        pionCourant.setPadding(coordX, coordY, 0, 0);
	}


public void afficheScores()
{
	Joueur JCT; //joueur en Cours de Traitement
	for(int i=0; i<maPartie.getListJoueur().size();i++)
	{
		JCT=maPartie.getListJoueur().get(i);
		switch(i)
		{
		case 0:
			tJ1.setText(JCT.getNom()+" : "+JCT.getListCarte().size());
			break;
		case 1:
			tJ2.setText(JCT.getNom()+" : "+JCT.getListCarte().size());		
			break;
		case 2:
			tJ3.setText(JCT.getNom()+" : "+JCT.getListCarte().size());
			break;
		case 3:
			tJ4.setText(JCT.getNom()+" : "+JCT.getListCarte().size());
			break;
		}
	}	
}


/*public void afficheEcran()
{
	affichePlateau();
	afficheCarteCourante();
	afficheCaseCourante(0);
	affichePions();	
}*/

public void tourDejeu(){
	if(plateauModif)
		{
	 	premiereModif=false;
	 	ctrouve=joueurActif.testCarteTrouvee();
	 	joueurActif.testJoueurGagnant();
	 	
	 	if(ctrouve==true){
 			CharSequence text = "BRAVO!!!";	
 			int duration = Toast.LENGTH_SHORT;
 			Context context = getApplicationContext();
 			Toast toast = Toast.makeText(context, text, duration);
 			toast.setGravity(0, 0, 0);
 			toast.show();	 	
			joueurActif.modifCarteObjectif();
	 	}
	 	
	 	
	 	if(maPartie.getPartieFinie())
		 	{
		 		//partie finie
	 			CharSequence text = "Partie Gagnée par : "+joueurActif.getNom();
	 			int duration = Toast.LENGTH_SHORT;
	 			Context context = getApplicationContext();
	 			Toast toast = Toast.makeText(context, text, duration);
	 			toast.show();
		 	}
		else
			{
			afficheCarteCourante();
			affichePions();	
			afficheScores();
			joueurActif=maPartie.joueurSuivant(joueurActif);
			deplacement=false;
			btnAnnuler.setVisibility(4);
			
			CharSequence text = "A "+joueurActif.getNom()+" de jouer !";
			int duration = Toast.LENGTH_SHORT;
			Context context = getApplicationContext();
		    Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
			text = "Commencez par modifier le plateau puis déplacez votre pion";
			duration = Toast.LENGTH_LONG;
			toast = Toast.makeText(context, text, duration);
			toast.show();
			afficheCarteCourante();
			plateauModif=false;
			if(joueurActif instanceof IA)
				{
					((IA) joueurActif).jouer(maPartie);
					tourDejeu();
				}
			}
		}
	else
		{
			CharSequence text = "Vous devez obligatoirement modifier le plateau";
			int duration = Toast.LENGTH_LONG;
			Context context = getApplicationContext();
		    Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
}	



public void setFullscreen() { 
    requestWindowFeature(Window.FEATURE_NO_TITLE); 
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
            WindowManager.LayoutParams.FLAG_FULLSCREEN); 
} 



}

