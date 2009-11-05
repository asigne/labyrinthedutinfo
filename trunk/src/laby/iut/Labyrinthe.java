package laby.iut;

import Java.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Labyrinthe extends Activity 
{
	ImageView fg1, fg3, fg5, fd1, fd3, fd5, fh1, fh3, fh5, fb1, fb3, fb5, imageCourante;
	RotateAnimation rotation0, rotation90, rotation180, rotation270;
	boolean initialisationPlateau;
    
	TextView Text01;
    
    Partie maPartie;
    Plateau monPlateau;
    Case caseCourante;	
    Coup monCoup;
    
    
    int ligne, colonne;
    String fleche;
    
    //coordonnées du plateau de jeu
    int xmin=13, xmax=307;
	int ymin=86, ymax=380;
	int tailleCase = 42; //taille d'une case du plateau
	int tailleFleche = 13; //largeur fleche


public void onCreate(Bundle savedInstanceState)
    {    	    	
         super.onCreate(savedInstanceState);
         setContentView(R.layout.jeu);   
         initImageView();
         
         Text01 = (TextView) findViewById(R.id.Text01);
         
         maPartie=new Partie("Partie1");     
         monPlateau=maPartie.getMonPlateau();
         caseCourante=maPartie.getCaseCourante();
         initPlateau2D();
         afficheCaseCourante(0);
    }  

//methode permettant de gérer les clic sur l'écran   
public boolean onTouchEvent(MotionEvent event)
{
	if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			int x = (int) (event.getX());
			int y = (int) (event.getY());
			Text01.setText("x:"+x+" y:"+y);
			
			if(x>xmin && x<=xmax && y>ymin && y<ymax)
			{
				actionCase(x, y);
				return true;
			}
			else if(x>xmin-tailleFleche && x<=xmax+tailleFleche && y>ymin-tailleFleche && y<ymax+tailleFleche)
			{
				actionFleche(x,y);
				return true;
			}
			else if(x>240 && y>350)
			{
				//action sur la caseCourante
				caseCourante=maPartie.getCaseCourante();
				caseCourante.rotate(90);
				int indiceRotation=caseCourante.getRotation();
				Text01.setText("click"+indiceRotation);
	    		afficheCaseCourante(150);
				return true;
			}
		}
	return false;
}


private void initImageView() {
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
        imageCourante = (ImageView) findViewById(R.id.CaseCourante);
}

//click sur une case du plateau
public void actionCase(int x, int y)
{
	//int ligne=0, colonne=0;
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
	int flag = monPlateau.getCase(ligne, colonne).getFlag();
	Text01.setText("ligne:"+ligne+" colonne:"+colonne+"    "+flag);
}

//click sur une fleche autour du plateau
public void actionFleche(int x, int y)
{				
	int modif;
	//int ligne=0, colonne=0;
	actionCase(x,y);
	if(x>xmin-tailleFleche && x<xmin)
	{
		fleche="gauche";
	}
	else if(x>xmax && x<xmax+tailleFleche)
	{
		fleche="droite";
	}
	else if(y>ymin-tailleFleche && y<ymin)
	{
		fleche="haut";
	}
	else if(y>ymax && y<ymax+tailleFleche)
	{
		fleche="bas";
	}		
	
	if(fleche=="haut" ||fleche=="bas")
		{
			modif=colonne;
		}
	else
		{
			modif=ligne;
		}	
	
	Text01.setText("fleche:"+fleche+" numero:"+ligne+colonne);
	if(modif==1 || modif==3 || modif==5)
		{
			monCoup = new Coup(caseCourante, modif, fleche);
			maPartie.modifierPlateau(monCoup);
			lockFleche(fleche,modif);
			caseCourante=maPartie.getCaseCourante();
			MaJPlateau(modif, fleche);
			afficheCaseCourante(0);	
			testCaseAccessible(0,0); // ligne pour illustrer le role de la methode fonction : 
														//en considerant que le joureur se trouve en 0,0
		}

}

//methode desactivant la fleche opposée apres un coup
public void lockFleche(String fleche, int indice){
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
		}
	else
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
		}
}

//methode permettant d'afficher le plateau et la carte courante
public void testCaseAccessible(int ligne, int colonne)
   {
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<7;j++)
			{
				monPlateau.getCase(i,j).setFlag(0);
				monPlateau.getCase(i,j).setEntree(-1);
				monPlateau.getCase(i,j).setSortie(0);
			}	
		}
	   
	   fonction(ligne, colonne, maPartie); 
   }

// methode initilisant les rotations
public void initRotation(int tailleImage, int duree)
   {
	   	int centre=tailleImage/2;
		rotation0 = new RotateAnimation(0, 360, centre, centre);
		rotation0.setDuration(duree);
		rotation0.setFillAfter(true);
	   	
		rotation90 = new RotateAnimation(0, 90, centre, centre);
		rotation90.setDuration(duree);
		rotation90.setFillAfter(true);
		
		rotation180 = new RotateAnimation(0, 180, centre, centre);
	 	rotation180.setDuration(duree);
	 	rotation180.setFillAfter(true);
	 	
	 	rotation270 = new RotateAnimation(0,270, centre, centre);
		rotation270.setDuration(duree);
		rotation270.setFillAfter(true);  
   }

//methode permettant d'afficher le plateau 
public void initPlateau2D()
   {
		int k=0;		// compteur pour selectionner l'id de la case du tableaux

		for(int ligne=0;ligne<7;ligne++)
		{
			for(int colonne=0;colonne<7;colonne++)
			{
				afficheICT(k, ligne, colonne, 2000);
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
	
	if(sens=="haut" || sens=="bas")
		{	
			k=modif;
			int colonne=modif;
			for(int ligne=0;ligne<7;ligne++)
				{	
					afficheICT(k, ligne, colonne, 0);
					k=k+8;
				}	
		}
	else
		{
		k=0;
		Text01.setText(""+modif+""+sens);
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
					afficheICT(k, ligne, colonne, 0);
					k++;
				}
		}
}

//methode permettant d'afficher une case sur le plateau
public void afficheICT(int k, int ligne, int colonne, int duration)
	{
		//k est le compteur pour selectionner l'id de la case du tableaux
		ImageView ICT; //Image en Cours de Traitement
		int noCase;		//indice de la case du tableau en cours de traitement
		int noImage;	//numero de l'image à affecter à la case en cours de traitement
		Case caseCourante; //case en cours de traitement
		int noImageCourante;
		int indiceRotation;
		
		initRotation(42,duration);
		
		caseCourante = monPlateau.getCase(ligne, colonne);
		noImageCourante=caseCourante.getNoImage();
		
		noCase=0x7f050004+k;
		ICT = (ImageView) findViewById(noCase);
		
		//selection de l'image a afficher
		if(monPlateau.getCase(ligne,colonne) instanceof T)
		{
			noImage=0x7f020010+noImageCourante;
		}
		else if(monPlateau.getCase(ligne,colonne) instanceof L)
		{
			noImage=0x7f020006+noImageCourante;
		}
		else
		{
			noImage=0x7f020004;
		}
		//affichage de l'image
		ICT.setImageDrawable(getResources().getDrawable(noImage));
		
		//rotation de l'image
		indiceRotation=(caseCourante.getRotation());			
		switch(indiceRotation)
		{
		case 0:
			ICT.setAnimation(rotation0);
			break;
		case 90:
			ICT.setAnimation(rotation90);
			break;
		case 180:
			ICT.setAnimation(rotation180);
			break;
		case 270:
			ICT.setAnimation(rotation270);
			break;
		}
	}

//methode permettant d'afficher la case courante
public void afficheCaseCourante(int duration)
   {   
	   initRotation(80,duration);
	   
	   int noImage;
	   Case caseCourante=maPartie.getCaseCourante();
	   int noImageCaseCourante=caseCourante.getNoImage();
	   if(maPartie.getCaseCourante() instanceof T)
		{
			noImage=0x7f020010+noImageCaseCourante;
			imageCourante.setImageDrawable(getResources().getDrawable(noImage));
		}
		else if(maPartie.getCaseCourante() instanceof L)
		{
			noImage=0x7f020006+noImageCaseCourante;
			imageCourante.setImageDrawable(getResources().getDrawable(noImage));
		}
		else
		{
			imageCourante.setImageDrawable(getResources().getDrawable(0x7f020004));
		}
	   
	   int indiceRotation=(caseCourante.getRotation());	
	   //Text01.setText("affiche: "+indiceRotation);
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

//fonction permettant de savoir si une case est accessible depuis la postion du joueur
// correspondant aux parametres ligne et colonne.
public static void fonction(int ligne, int colonne, Partie maPartie)
   {
		Plateau monPlateau=maPartie.getMonPlateau();
		Case maCase=monPlateau.getCase(ligne, colonne);
		int sortie=maCase.getSortie();
		int entree=maCase.getEntree();
		int L1=0, C1=0, S=0, E=0;
		boolean Ok = false;
		
		monPlateau.getCase(ligne, colonne).setFlag(1);

	   
	   for(int i=maCase.getSortie()+1;i<5;i++)
	   {
		   if(sortie!=entree)
			   {
			   		switch(i)
				   		{
					   		case 1: //haut
					   			if(ligne>0 && maCase.getTabDroit(1)==true &&
					   					monPlateau.getCase(ligne-1, colonne).getTabDroit(3)==true &&
					   					monPlateau.getCase(ligne-1, colonne).getFlag()==0)
					   				{
					   					L1=-1;
					   					C1=0;
					   					S=1;
					   					E=3;
					   					Ok=true;
					   				}	
					   			break;
					   			
					   		case 2: //droite
					   			if(colonne<6 && maCase.getTabDroit(2)==true &&
					   					monPlateau.getCase(ligne, colonne+1).getTabDroit(4)==true &&
					   					monPlateau.getCase(ligne, colonne+1).getFlag()==0)
					   				{
					   					L1=0;
					   					C1=1;
					   					S=2;
					   					E=4;
					   					Ok=true;
					   				}		   			
					   			break;
					   			
					   		case 3: //bas
					   			if(ligne<6 && maCase.getTabDroit(3)==true &&
					   					monPlateau.getCase(ligne+1, colonne).getTabDroit(1)==true &&
					   					monPlateau.getCase(ligne+1, colonne).getFlag()==0)
					   				{
					   					L1=1;
					   					C1=0;
					   					S=3;
					   					E=1;
					   					Ok=true;
					   				}
					   			break;
					   			
					   		case 4: //gauche
					   			if(colonne>0 && maCase.getTabDroit(4)==true &&
					   					monPlateau.getCase(ligne, colonne-1).getTabDroit(2)==true &&
					   					monPlateau.getCase(ligne, colonne-1).getFlag()==0)
					   				{
					   					L1=0;
					   					C1=-1;
					   					S=4;
					   					E=2;
					   					Ok=true;
					   				}	
					   			break;
				   		}
				   if(Ok==true)
				   		{
					   		monPlateau.getCase(ligne, colonne).setSortie(S);
					   		ligne=ligne+L1;
					   		colonne=colonne+C1;
					   		monPlateau.getCase(ligne, colonne).setEntree(E);
					   		monPlateau.getCase(ligne, colonne).setFlag(1);
					   		
					   		fonction(ligne,colonne, maPartie);
					   		
					   		ligne=ligne-L1;
					   		colonne=colonne-C1;
					   		
				   		}
			   }
		   
		   }
   }
}
