package laby.iut;

import Java.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Labyrinthe extends Activity 
{
	ImageView fg1;
    ImageView fg2;
    ImageView fg3;
    ImageView fd1;
    ImageView fd2;
    ImageView fd3;
    ImageView fh1;
    ImageView fh2;
    ImageView fh3;
    ImageView fb1;
    ImageView fb2;
    ImageView fb3;
    ImageView imageCourante;
    Partie maPartie;
    Plateau monPlateau;
    Case caseCourante;
    ImageView TabImageView[][];
    int ligne, colonne;
    
    TextView Text01;
    
    RotateAnimation rotation0;
    RotateAnimation rotation90;
    RotateAnimation rotation180;
    RotateAnimation rotation270;
   
public boolean onTouchEvent(MotionEvent event)
{
	if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			int x = (int) (event.getX());
			int y = (int) (event.getY());

  
			int xmin=13, xmax=307;
			int ymin=86, ymax=380;
			int tailleCase = 42;
			colonne=0;
			ligne=0;
			if(x>xmin && x<=xmax && y>ymin && y<ymax)
				{
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
					//TabImageView[ligne][colonne].setVisibility(4);
					
					//appeler la methode permettant de placer un joueur sur une case !
					
					return true;

				}
			return false;
		}
	return false;           
}
    
public void onCreate(Bundle savedInstanceState)
   {    	    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeu);    
        
        fg1 = (ImageView) findViewById(R.id.fg1);
        fg2 = (ImageView) findViewById(R.id.fg2);
        fg3 = (ImageView) findViewById(R.id.fg3);
        fd1 = (ImageView) findViewById(R.id.fd1);
        fd2 = (ImageView) findViewById(R.id.fd2);
        fd3 = (ImageView) findViewById(R.id.fd3);
        fh1 = (ImageView) findViewById(R.id.fh1);
        fh2 = (ImageView) findViewById(R.id.fh2);
        fh3 = (ImageView) findViewById(R.id.fh3);
        fb1 = (ImageView) findViewById(R.id.fb1);
        fb2 = (ImageView) findViewById(R.id.fb2);
        fb3 = (ImageView) findViewById(R.id.fb3);   
        imageCourante = (ImageView) findViewById(R.id.CaseCourante);
        Text01 = (TextView) findViewById(R.id.Text01);
        
       
        maPartie=new Partie("Partie1");     
        monPlateau=maPartie.getMonPlateau();
        caseCourante=maPartie.getCaseCourante();
      

        afficheEcranJeu();
        
        //action sur les fleches du haut
    	fh1.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierColonne(1, maPartie, "haut");		
				afficheEcranJeu();
				lockFleche(fb1);				
			}
	    });
	    fh2.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierColonne(3, maPartie, "haut");		
				afficheEcranJeu();
				lockFleche(fb2);	
			}
	    });
	    fh3.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierColonne(5, maPartie, "haut");		
				afficheEcranJeu();
				lockFleche(fb3);	
			}
	    });
	    //action sur les fleches du bas
	    fb1.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierColonne(1, maPartie, "bas");		
				afficheEcranJeu();
				lockFleche(fh1);	
			}
	    });
	    fb2.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierColonne(3, maPartie, "bas");		
				afficheEcranJeu();
				lockFleche(fh2);	
			}
	    });
	    fb3.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierColonne(5, maPartie, "bas");		
				afficheEcranJeu();
				lockFleche(fh3);	
			}
	    });
	    //action sur les fleches de gauche
	    fg1.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierLigne(1, maPartie, "gauche");		
				afficheEcranJeu();
				lockFleche(fd1);	
			}
	    });
	    fg2.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierLigne(3, maPartie, "gauche");		
				afficheEcranJeu();
				lockFleche(fd2);	
			}
	    });
	    fg3.setOnClickListener(new View.OnClickListener()

	    {
			public void onClick(View v) {
				monPlateau.modifierLigne(5, maPartie, "gauche");		
				afficheEcranJeu();
				lockFleche(fd3);	
			}
	    });
	    //action sur les fleches de droite
	    fd1.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierLigne(1, maPartie, "droite");		
				afficheEcranJeu();
				lockFleche(fg1);	
			}
	    });
	    fd2.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierLigne(3, maPartie, "droite");		
				afficheEcranJeu();
				lockFleche(fg2);	
			}
	    });
	    fd3.setOnClickListener(new View.OnClickListener()
	    {
			public void onClick(View v) {
				monPlateau.modifierLigne(5, maPartie, "droite");		
				afficheEcranJeu();
				lockFleche(fg3);	
			}
	    });
	    
	    imageCourante.setOnClickListener(new View.OnClickListener()
	    {	
	    	public void onClick(View v) 
	    	{
	    		caseCourante=maPartie.getCaseCourante();
				caseCourante.rotate(90);
				int indiceRotation=caseCourante.getRotation();
				Text01.setText("click"+indiceRotation);
	    		afficheCaseCourante();
	    	}
	    });
   }

   public void lockFleche(ImageView fleche){
		fb1.setVisibility(3);
		fb2.setVisibility(3);
		fb3.setVisibility(3);
		fh1.setVisibility(3);
		fh2.setVisibility(3);
		fh3.setVisibility(3);
		fg1.setVisibility(3);
		fg2.setVisibility(3);
		fg3.setVisibility(3);
		fd1.setVisibility(3);
		fd2.setVisibility(3);
		fd3.setVisibility(3);	
		fleche.setVisibility(4);
   }
   
   public void afficheEcranJeu()
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
	   
	   
	   fonction(0,0, maPartie); 
	   Text01.setText("afficheecranjeu");
	   affichePlateau2D();
	   afficheCaseCourante();
   }
   
   public void initRotation(int tailleImage)
   {
	   	int centre=tailleImage/2;
		rotation0 = new RotateAnimation(0, 0, centre, centre);
		//rotation0.setDuration(2);
		rotation0.setFillAfter(true);
	   	
	   	
		rotation90 = new RotateAnimation(0, 90, centre, centre);
		//rotation90.setDuration(2);
		rotation90.setFillAfter(true);
		
		rotation180 = new RotateAnimation(0, 180, centre, centre);
	 	//rotation180.setDuration(2);
	 	rotation180.setFillAfter(true);
	 	
	 	rotation270 = new RotateAnimation(0,270, centre, centre);
		//rotation270.setDuration(2);
		rotation270.setFillAfter(true);  
   }

   
   
   public void affichePlateau2D()
   {
		ImageView ICT; //Image en Cours de Traitement
		int k=0;		// compteur pour selectionner l'id de la case du tableaux
		int noCase;		//indice de la case du tableau en cours de traitement
		int noImage;	//numero de l'image à affecter à la case en cours de traitement
		Case caseCourante; //case en cours de traitement
		
		
    	initRotation(42);	//initialisation des rotations adaptées aux case du plateau
		
		for(int ligne=0;ligne<7;ligne++)
		{
			for(int colonne=0;colonne<7;colonne++)
			{
				caseCourante = monPlateau.getCase(ligne, colonne);
				int noImageCourante=caseCourante.getNoImage();
				
				int indiceRotation=(caseCourante.getRotation());			
				noCase=0x7f050003+k;
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
			k++;
			}
		k++;
		}	
   }
   
   public void afficheCaseCourante()
   {   
	   initRotation(80);
	   
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
	   Text01.setText("affiche: "+indiceRotation);
	   switch(indiceRotation)
		{
		case 0:
			Text01.setText("case0");
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
