package laby.iut;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Java.Carte;
import Java.Case;
import Java.Coup;
import Java.IA;
import Java.Joueur;
import Java.L;
import Java.Partie;
import Java.Plateau;
import Java.T;
import Java.Utilisateur;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
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

public class PlateauJeu extends Activity {

	ImageView fg1, fg3, fg5, fd1, fd3, fd5, fh1, fh3, fh5, fb1, fb3, fb5,
	imageCarteCourante;
	ImageView pionBleu, pionRouge, pionVert, pionJaune;
	RotateAnimation rotation0, rotation90, rotation180, rotation270;
	boolean initialisationPlateau, jeuPossible = true;
	TextView Text01, textInfo, textJoueurActif;
	LinearLayout lbleu, lvert, lrouge, ljaune;
	TableLayout lPlateau;
	Button btnJouer, btnAnnuler, btnJoueurSvt;
	String flecheInterdite;
	int indiceInterdit;
	Partie maPartie;
	Plateau monPlateau;
	Case caseCourante;
	Coup monCoup;

	Vibrator leVibreur;

	boolean ctrouve = false;

	// Utilisateur j1, j2, j3, j4;
	Joueur ia, j1, j2, j3, j4;
	// IA ia;

	int sauvModif;
	Case sauvCaseCourante, sauvCaseSortante;
	String sauvFleche;
	String sauvFlecheInterdite;
	int sauvIndiceInterdit;
	int sauvPosLigne, sauvPosColonne;

	TextView tJ1, tJ2, tJ3, tJ4;

	// int ligne, colonne;
	String fleche;

	boolean deplacement = false, premiereModif = true;
	boolean plateauModif = false;
	// boolean jeuFait;
	
	

	// parametre de l'application
	//int xmin = 13, xmax = 307, ymin = 61, ymax = 355;// coordonn�es du plateau
	int xmin = 0, xmax = 0, ymin = 0, ymax = 0;// coordonn�es du plateau
	// de jeu
	int tailleCase = 42; // taille d'une case du plateau
	int tailleFleche = 13; // largeur fleche

	int indicePremiereCaseTableau = R.id.l0_c0;
	int incidePremiereImageCarte = R.drawable.ca;
	int indicePremierL = (R.drawable.l);
	int indicePremierT = (R.drawable.ta) - 1;
	int indiceI = R.drawable.i;
	int indicePremierPion = R.id.lbleu;

	String pseudo1, pseudo2, pseudo3, pseudo4, regle, difficulte, typePartie;
	int nbJoueurs;

	// genere une notif avec le texte "text", la dur�e "duration"
	// et coordonn�e par defaut(en bas du plateau)
	public void notif(CharSequence text, int duration) {
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	// genere une notif avec le texte "text", la dur�e "duration"
	// et coordonn�e g(gravity),x(xoffset),y(yoffset)
	public void notif(CharSequence text, int duration, int g, int x, int y) {
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(g, x, y);
		toast.show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullscreen();
		setContentView(R.layout.jeu);
		
		Configuration c = getResources().getConfiguration();
		if(c.orientation == Configuration.ORIENTATION_PORTRAIT ) {
			xmin = 13;
			xmax = 307;
			ymin = 61;
			ymax = 355;// coordonn�es du plateau
			// de jeu
		} else if(c.orientation == Configuration.ORIENTATION_LANDSCAPE ){
			xmin = 13;
			xmax = 307;
			ymin = 13;
			ymax = 307;
			// coordonn�es du plateau
			// de jeu
		}

		
		// r�cuperation du pseudo et des parametres de la partie
		Bundle objetParametre = this.getIntent().getExtras();

		String mode = objetParametre.getString("mode");
		if (mode.equals("nouvellePartie")) {

			typePartie = objetParametre.getString("typePartie");
			regle = objetParametre.getString("regle");

			if (typePartie.equals("solo")) {
				difficulte = objetParametre.getString("difficulte");
				nbJoueurs = 2;
			}

			if (typePartie.equals("multi")) {
				difficulte = "";
				nbJoueurs = objetParametre.getInt("nbJoueurs");

				switch (nbJoueurs) {
				case 2:
					pseudo1 = objetParametre.getString("pseudoJ1");
					pseudo2 = objetParametre.getString("pseudoJ2");
					break;
				case 3:
					pseudo1 = objetParametre.getString("pseudoJ1");
					pseudo2 = objetParametre.getString("pseudoJ2");
					pseudo3 = objetParametre.getString("pseudoJ3");
					break;
				case 4:
					pseudo1 = objetParametre.getString("pseudoJ1");
					pseudo2 = objetParametre.getString("pseudoJ2");
					pseudo3 = objetParametre.getString("pseudoJ3");
					pseudo4 = objetParametre.getString("pseudoJ4");
					break;
				}
			}
		}

		initDesID();
		lancementPartie(mode);
		initPlateau2D(); // initialisation de l'affichage du plateau en 2D
		affichePions(); // affichage des pions sur le plateau
		afficheCaseCourante(0); // affichage de la case courante

		if (mode.equals("nouvellePartie")) {
			definirJoueurActif(j1);
		}
		afficheScores();

		// affichage de la carte courante du joueur
		afficheCarteCourante();
		montrerCaseObjectif();

		//textInfo.setText("");
		textJoueurActif.setText("");

		// CharSequence text;
		// text = "A "+maPartie.getJoueurActif().getNom()+" de jouer !";
		// notif(text,Toast.LENGTH_SHORT,0,0,0);

		// text =
		// "Commencez par modifier le plateau puis d�placez votre pion";
		// notif(text,Toast.LENGTH_SHORT);

		btnJouer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				tourDejeu();

			}
		});

		btnAnnuler.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!maPartie.getPartieFinie()) {
					annulerDernierCoup();
					//					Partie testa = new Partie("Partieaaa", regle, difficulte);
					//
					//					Context lecontext = getBaseContext();
					//					testa = charg(lecontext);
					//					if (testa != null) {
					//						// CharSequence text = "A "+
					//						// testa.getListJoueur().get(0).getNom() +
					//						// " de jouer !";
					//						// notif(text,Toast.LENGTH_SHORT,0,0,0);
					//					} else {
					//						// CharSequence text = "marche pas !";
					//						// notif(text,Toast.LENGTH_SHORT,0,0,0);
					//					}
				}

			}
		});

		btnJoueurSvt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				btnJoueurSvt.setVisibility(4);
				afficheCarteCourante();
				montrerCaseObjectif();
				jeuPossible = true;

			}
		});

	}

	public void lancementPartie(String mode) {

		if (mode.equals("nouvellePartie")) {
			maPartie = new Partie("Partie", regle, difficulte); // creation de
			// la partie

			monPlateau = maPartie.getMonPlateau(); // recuperation du plateau de
			// la partie
			caseCourante = maPartie.getCaseCourante(); // recuperation de la
			// case courante de la
			// partie

			if (typePartie.equals("solo")) {
				j1 = new Utilisateur("Joueur");
				j2 = new IA("IA");
				j1.RejoindrePartie(maPartie);
				j2.RejoindrePartie(maPartie);
			}
			if (typePartie.equals("multi")) {
				// creation des joueurs
				switch (nbJoueurs) {
				case 2:
					j1 = new Utilisateur(pseudo1);
					j2 = new Utilisateur(pseudo2);

					j1.RejoindrePartie(maPartie);
					j2.RejoindrePartie(maPartie);
					break;
				case 3:
					j1 = new Utilisateur(pseudo1);
					j2 = new Utilisateur(pseudo2);
					j3 = new Utilisateur(pseudo3);

					j1.RejoindrePartie(maPartie);
					j2.RejoindrePartie(maPartie);
					j3.RejoindrePartie(maPartie);
					break;
				case 4:
					j1 = new Utilisateur(pseudo1);
					j2 = new Utilisateur(pseudo2);
					j3 = new Utilisateur(pseudo3);
					j4 = new Utilisateur(pseudo4);

					j1.RejoindrePartie(maPartie);
					j2.RejoindrePartie(maPartie);
					j3.RejoindrePartie(maPartie);
					j4.RejoindrePartie(maPartie);
					break;
				}
			}
			maPartie.lancerPartie(); // lancement de la partie
		} else {
			Context lecontext = getBaseContext();
			maPartie = charger(lecontext);
			monPlateau = maPartie.getMonPlateau(); // creation de la partie
			// recuperation du plateau de la partie
			caseCourante = maPartie.getCaseCourante(); // recuperation de la
			// case courante de la
			// partie
			// lancement de la partie

			switch (nbJoueurs) {
			case 2:
				j1 = maPartie.getListJoueur().get(0);
				j2 = maPartie.getListJoueur().get(1);

				break;
			case 3:
				j1 = maPartie.getListJoueur().get(0);
				j2 = maPartie.getListJoueur().get(1);
				j3 = maPartie.getListJoueur().get(2);
				break;
			case 4:
				j1 = maPartie.getListJoueur().get(0);
				j2 = maPartie.getListJoueur().get(1);
				j3 = maPartie.getListJoueur().get(2);
				j4 = maPartie.getListJoueur().get(3);
				break;
			}
		}

	}

	public void definirJoueurActif(Joueur monJoueur) {
		maPartie.setJoueurActif(monJoueur);
		tJ1.setTextColor(Color.WHITE);
		tJ2.setTextColor(Color.WHITE);
		tJ3.setTextColor(Color.WHITE);
		tJ4.setTextColor(Color.WHITE);
		switch (monJoueur.getIdentifiant()) {
		case 0:
			tJ1.setTextColor(Color.rgb(0, 116, 232));
			break;
		case 1:
			tJ2.setTextColor(Color.RED);
			break;
		case 2:
			tJ3.setTextColor(Color.rgb(0, 128, 64));
			break;
		case 3:
			tJ4.setTextColor(Color.YELLOW);
			break;
		}
	}

	public void sauvegarder(Context context, Partie data) {
		FileOutputStream fOut = null;
		ObjectOutputStream oos = null;
		try {
			fOut = context.openFileOutput("sauvMaPartie", MODE_WORLD_WRITEABLE);
			oos = new ObjectOutputStream(fOut);
			oos.writeObject(maPartie);
			oos.flush();

			// popup surgissant pour le r�sultat
			//Toast.makeText(context, "Settings saved", Toast.LENGTH_SHORT)
			//.show();
		} catch (IOException e) {
			//Toast.makeText(context, "Settings not savedaa", Toast.LENGTH_SHORT)
			//.show();
		} finally {
			try {
				oos.close();
				fOut.close();
			} catch (IOException e) {
				//Toast.makeText(context, "Settings not savedbb",
				//		Toast.LENGTH_SHORT).show();
			}
		}
	}

	// methode permettant de g�rer les clic sur l'�cran
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (jeuPossible) {
			Configuration c = getResources().getConfiguration();
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				int x = (int) (event.getX());
				int y = (int) (event.getY());
				textInfo.setText("xmin:"+x+" xmax:"+y+" ymin:"+ymin+" ymax:"+ymax);
				if (!maPartie.getPartieFinie()) {
					// gestion du click en fonction des coordonn�es
					if (x > xmin && x <= xmax && y > ymin && y < ymax
							&& plateauModif == true) {
						// action sur une case du plateau
						textInfo.setText("bla");
						actionCase(x, y);
						return true;
					} else if (x > xmin - tailleFleche
							&& x <= xmax + tailleFleche
							&& y > ymin - tailleFleche
							&& y < ymax + tailleFleche) {
						// action sur une fleche
						if (!plateauModif) {
							actionFleche(x, y);
						} else {
							CharSequence text = "Vous avez d�j� modifier le plateau";
							notif(text, Toast.LENGTH_SHORT);
						}
						return true;
					} else{
						if(c.orientation == Configuration.ORIENTATION_PORTRAIT ) {
							if (x > 240 && y > 400) {
								// action sur la caseCourante
								actionCaseCourante();
								return true;
							} else if (x > 180 && x < 230 && y < 480 && y > 400) {
								// action sur la carteCourante
								actionCarteCourante();
								return true;
							}
						} else if(c.orientation == Configuration.ORIENTATION_LANDSCAPE ){
							if (x > 400 && y > 240) {
								// action sur la caseCourante
								actionCaseCourante();
								return true;
							} else if (x > 330 && x < 381 && y < 320 && y > 240) {
								// action sur la carteCourante
								actionCarteCourante();
								return true;
							}
						}
					}
				}
			//	textInfo.setText("aie");
			}
			return false;
		} else {
			CharSequence text = "Veuillez cliquer sur le bouton avant de jouer !";
			notif(text, Toast.LENGTH_SHORT, 0, 0, 0);
			return false;
		}
	}

	// recupere les ID des diff�rents objets graphiques
	public void initDesID() {
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
		lvert = (LinearLayout) findViewById(R.id.lvert);
		lrouge = (LinearLayout) findViewById(R.id.lrouge);
		ljaune = (LinearLayout) findViewById(R.id.ljaune);
		textJoueurActif = (TextView) findViewById(R.id.textJoueurActif);
		textInfo = (TextView) findViewById(R.id.textInfo);
		btnJouer = (Button) findViewById(R.id.Jouer);
		btnAnnuler = (Button) findViewById(R.id.Annuler);
		btnJoueurSvt = (Button) findViewById(R.id.JoueurSvt);

		// lPlateau = (TableLayout) findViewById(R.id.Plateau);
		tJ1 = (TextView) findViewById(R.id.textJ1);
		tJ2 = (TextView) findViewById(R.id.textJ2);
		tJ3 = (TextView) findViewById(R.id.textJ3);
		tJ4 = (TextView) findViewById(R.id.textJ4);

		leVibreur = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

	}

	// click sur la carteCourante
	public void actionCarteCourante() {
		// recuperation de la liste des identifiants des cartes
		if (maPartie.partieEnfant()) {
			ArrayList<Integer> listeCarte = new ArrayList<Integer>();
			for (int i = 0; i < maPartie.getJoueurActif().getListCarte().size(); i++) {
				listeCarte.add(maPartie.getJoueurActif().getListCarte().get(i)
						.getIdentifiant());
			}

			// creation de l'intent
			Intent defineIntent = new Intent(this, AffichageCartes.class);
			// creation de l'objet Bundle
			Bundle objetbundle = new Bundle();

			// ajout de la liste de carte au bundle
			objetbundle.putIntegerArrayList("listeCarte", listeCarte);
			// ajout du bundle � l'intent
			defineIntent.putExtras(objetbundle);
			// lancement de la nouvelle activity
			startActivity(defineIntent);
		} else {
			// textJoueurActif.setText("notification pas le droit");
			CharSequence text = "Interdit avec ce type de r�les";
			notif(text, Toast.LENGTH_SHORT);

		}
	}

	// click sur la caseCourante :
	public void actionCaseCourante() {
		caseCourante = maPartie.getCaseCourante(); // recuperation de la carte
		// couante
		caseCourante.rotate(90); // rotation de la carte
		afficheCaseCourante(150); // affichage de la carte
	}

	// revoie l'indice de la colonne du plateau en fonction du click sur l'ecran
	public int CoordToColonne(int x) {
		int colonne = 0;
		if (x <= xmin + tailleCase) {
			colonne = 0;
		} else if (x <= xmin + tailleCase * 2) {
			colonne = 1;
		} else if (x <= xmin + tailleCase * 3) {
			colonne = 2;
		} else if (x <= xmin + tailleCase * 4) {
			colonne = 3;
		} else if (x <= xmin + tailleCase * 5) {
			colonne = 4;
		} else if (x <= xmin + tailleCase * 6) {
			colonne = 5;
		} else if (x <= xmin + tailleCase * 7) {
			colonne = 6;
		}
		return colonne;
	}

	// revoie l'indice de la ligne du plateau en fonction du click sur l'ecran
	public int CoordToLigne(int y) {
		int ligne = 0;
		if (y <= ymin + tailleCase) {
			ligne = 0;
		} else if (y <= ymin + tailleCase * 2) {
			ligne = 1;
		} else if (y <= ymin + tailleCase * 3) {
			ligne = 2;
		} else if (y <= ymin + tailleCase * 4) {
			ligne = 3;
		} else if (y <= ymin + tailleCase * 5) {
			ligne = 4;
		} else if (y <= ymin + tailleCase * 6) {
			ligne = 5;
		} else if (y <= ymin + tailleCase * 7) {
			ligne = 6;
		}
		return ligne;
	}

	// click sur une case du plateau
	public void actionCase(int x, int y) {
		textInfo.setText("blo");
		if (plateauModif) // plateau doit etre modifier avec de deplacer un pion
		{
			int ligne = CoordToLigne(y); // converti le click en ligne
			int colonne = CoordToColonne(x); // converti le click en colonne
			if (maPartie.getJoueurActif().seDeplacer(ligne, colonne)) // le
				// joueur
				// doit
				// pouvoir
				// aller
				// sur
				// une
				// case
			{
				deplacement = true; // deplacement est vrai
				affichePions(); // affichage des pions sur le plateau
				ctrouve = maPartie.getJoueurActif().testCarteTrouvee();
				if (ctrouve == true) {
					ctrouve = false;

					// CharSequence text = "Carte trouv�e!";
					// notif(text,Toast.LENGTH_SHORT,0,0,0);
					leVibreur.vibrate(300);

					// text = "Appuyer sur <JOUER> pour finir le tour";
					// notif(text,Toast.LENGTH_SHORT);
				}

			} else {
				CharSequence text = "D�placement interdit";
				notif(text, Toast.LENGTH_SHORT);
			}
		} else {
			CharSequence text = "Vous devez modifier le plateau avant de vous d�placer";
			notif(text, Toast.LENGTH_SHORT);
			// textInfo.setText("Vous devez modifier le plateau avant de vous deplacer");
		}
	}

	// click sur une fleche autour du plateau
	public boolean actionFleche(int x, int y) {
		if (!deplacement) // modif interdite apres le deplacement du pion
		{
			// recuperation de la ligne ou de la colonne (fleche, modif)
			int modif = 0;
			if (x > xmin - tailleFleche && x < xmin + tailleCase) {
				fleche = "gauche";
				modif = CoordToLigne(y);
			} else if (x > xmax - tailleCase && x < xmax + tailleFleche) {
				fleche = "droite";
				modif = CoordToLigne(y);
			} else if (y > ymin - tailleFleche && y < ymin + tailleCase) {
				fleche = "haut";
				modif = CoordToColonne(x);
				;
			} else if (y > ymax - tailleCase && y < ymax + tailleFleche) {
				fleche = "bas";
				modif = CoordToColonne(x);
			}

			if (modif == indiceInterdit && fleche == flecheInterdite) {
				CharSequence text = "Action interdite";
				notif(text, Toast.LENGTH_SHORT);
			} else {
				if (modif == 1 || modif == 3 || modif == 5) {
					btnAnnuler.setVisibility(0); // cache le bouton "annuler"
					sauvModif = modif;
					sauvFleche = fleche;
					sauvFlecheInterdite = flecheInterdite;
					sauvIndiceInterdit = indiceInterdit;

					// creation de la modification du plateau
					monCoup = ((Utilisateur) maPartie.getJoueurActif())
					.genererCoup(caseCourante, modif, fleche);
					// modification du plateau
					maPartie.getJoueurActif().modifierPlateau(monCoup);
					// deplacement des joueurs situes sur les cases mobiles
					// concern�es
					maPartie.traitementJoueurSurCaseMobile(modif, fleche);
					affichePions();
					// verouillage de la fleche interdite
					lockFleche(fleche, modif);
					// recuperation de la nouvelle caseCourante
					caseCourante = maPartie.getCaseCourante();

					sauvPosLigne = maPartie.getJoueurActif().getPosLigne();
					sauvPosColonne = maPartie.getJoueurActif().getPosColonne();
					sauvCaseSortante = caseCourante.sauvCase();
					// affichage du nouveau plateau
					MaJPlateau(modif, fleche);
					// affichage de la nouvelle carteCourante
					afficheCaseCourante(0);
					plateauModif = true; // le plateau a deja ete modifie
					maPartie.getJoueurActif().testCasesAccessibles(
							maPartie.getMonPlateau()); // test des cases
					// accessibles par le
					// joueurActif
					return true;
				}
			}
		} else {
			CharSequence text = "Vous ne pouvez pas modifier le plateau apr�s avoir d�plac� votre pion !";
			notif(text, Toast.LENGTH_SHORT);
		}
		return false;
	}

	// permet d'annuler la derniere modification du plateau
	public void annulerDernierCoup() {
		/*
		 * if(!deplacement || (sauvPosLigne==joueurActif.getPosLigne() &&
		 * sauvPosColonne==joueurActif.getPosColonne())) // le joueur ne peut
		 * pas annuler s'il a d�plac� son pion {
		 */
		maPartie.getJoueurActif().seDeplacer(sauvPosLigne, sauvPosColonne);
		if (sauvFleche == "haut") {
			fleche = "bas";
		} else if (sauvFleche == "bas") {
			fleche = "haut";
		} else if (sauvFleche == "gauche") {
			fleche = "droite";
		} else if (sauvFleche == "droite") {
			fleche = "gauche";
		}

		if (sauvFlecheInterdite == "haut") {
			flecheInterdite = "bas";
		} else if (sauvFlecheInterdite == "bas") {
			flecheInterdite = "haut";
		} else if (sauvFlecheInterdite == "gauche") {
			flecheInterdite = "droite";
		} else if (sauvFlecheInterdite == "droite") {
			flecheInterdite = "gauche";
		}
		// creation de la modification du plateau
		monCoup = ((Utilisateur) maPartie.getJoueurActif()).genererCoup(
				sauvCaseSortante, sauvModif, fleche);
		// modification du plateau
		maPartie.getJoueurActif().modifierPlateau(monCoup);
		// deplacement des joueurs situes sur les cases mobiles concern�es
		maPartie.traitementJoueurSurCaseMobile(sauvModif, fleche);
		affichePions();
		// recuperation de la nouvelle caseCourante
		caseCourante = maPartie.getCaseCourante();
		// affichage du nouveau plateau
		MaJPlateau(sauvModif, fleche);
		// affichage de la nouvelle carteCourante
		afficheCaseCourante(0);

		if (premiereModif) // si la premiere modif est annul�e
		{
			unlockFleche(); // deverrouillage de toutes les flechs
		} else // sinon reverrouillage de la fleche precedent la modification du
			// plateau
		{
			lockFleche(flecheInterdite, sauvIndiceInterdit);
		}

		plateauModif = false;
		deplacement = false;
		CharSequence text = "Modification du plateau annul�e";
		notif(text, Toast.LENGTH_SHORT);
		btnAnnuler.setVisibility(4); // rend invisible le bouton annuler
		// }
		/*
		 * else {textInfo.setText(
		 * "Vous ne pouvez pas annuler la modification tant que votre joueur ne se "
		 * +
		 * "trouve pas o� il �tait avant, Ligne:"+(sauvPosLigne+1)+" Colonne:"
		 * +(sauvPosColonne+1)); }
		 */
	}

	

	// methode desactivant la fleche oppos�e apres un coup
	public void lockFleche(String fleche, int indice) {
		indiceInterdit = indice;

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

		if (fleche == "haut") {
			switch (indice) {
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
			flecheInterdite = "bas";
		} else if (fleche == "bas") {
			switch (indice) {
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
			flecheInterdite = "haut";
		} else if (fleche == "gauche") {
			switch (indice) {
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
			flecheInterdite = "droite";
		} else if (fleche == "droite") {
			switch (indice) {
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
			flecheInterdite = "gauche";
		}
	}

	// methode desactivant toutes les fleches
	public void unlockFleche() {
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
		indiceInterdit = 0;
		flecheInterdite = "";
	}

	// methode initilisant les rotations
	public void initRotation(int tailleImage, int duree) {
		// rotation 0� / 360�
		int centre = tailleImage / 2;
		rotation0 = new RotateAnimation(0, 360, centre, centre);
		rotation0.setDuration(duree);
		rotation0.setFillAfter(true);

		// rotation 90�
		rotation90 = new RotateAnimation(0, 90, centre, centre);
		rotation90.setDuration(duree);
		rotation90.setFillAfter(true);

		// rotation 180�
		rotation180 = new RotateAnimation(0, 180, centre, centre);
		rotation180.setDuration(duree);
		rotation180.setFillAfter(true);

		// rotation 270�
		rotation270 = new RotateAnimation(0, 270, centre, centre);
		rotation270.setDuration(duree);
		rotation270.setFillAfter(true);
	}

	// methode permettant d'afficher le plateau � l'initiation
	public void initPlateau2D() {
		int k = 0; // compteur pour selectionner l'id de la case du tableaux
		int noCase;
		ImageView ICT;

		for (int ligne = 0; ligne < 7; ligne++) {
			for (int colonne = 0; colonne < 7; colonne++) {
				noCase = indicePremiereCaseTableau + k;
				ICT = (ImageView) findViewById(noCase);
				afficheICT(monPlateau.getCase(ligne, colonne), ICT, 2000, 42);
				k++;
			}
			k++;
		}
		initialisationPlateau = true;
	}

	// methode permettant de mettre a jour l'affichage du plateau
	public void MaJPlateau(int modif, String sens) {
		int k; // compteur pour selectionner l'id de la case du tableaux
		int noCase;
		ImageView ICT;
		if (sens == "haut" || sens == "bas") {
			k = modif;
			int colonne = modif;
			for (int ligne = 0; ligne < 7; ligne++) {
				noCase = indicePremiereCaseTableau + k;
				ICT = (ImageView) findViewById(noCase);
				afficheICT(monPlateau.getCase(ligne, colonne), ICT, 0, 42);
				k = k + 8;
			}
		} else {
			k = 0;
			switch (modif) {
			case 1:
				k = 8;
				break;
			case 3:
				k = 24;
				break;
			case 5:
				k = 40;
				break;
			}
			int ligne = modif;
			for (int colonne = 0; colonne < 7; colonne++) {
				noCase = indicePremiereCaseTableau + k;
				ICT = (ImageView) findViewById(noCase);
				afficheICT(monPlateau.getCase(ligne, colonne), ICT, 0, 42);
				k++;
			}
		}
		montrerCaseObjectif();
	}

	// methode permettant d'afficher une case sur le plateau
	public void afficheICT(Case caseATraiter, ImageView imageCourante,
			int duration, int dimensionCase) {
		/*
		 * description des parametres de la fonction caseATraiter permet
		 * d'acceder aux attributs de la case en cours de traintement ImageView
		 * pointe sur l'objet ImageView en cours de traitement duration est la
		 * valeur pour la dur�e de la rotation dimention est la dimention des
		 * cases suivant que l'on traite le plateau ou la caseCourante
		 */
		int noImage; // numero de l'image � affecter � la case en cours de
		// traitement
		int noImageCourante;

		// recuperation du numero de l'image a partir de l'intance de la case en
		// cours de traitement
		noImageCourante = caseATraiter.getNoImage();

		// selection de l'image a afficher
		if (caseATraiter instanceof T) {
			noImage = indicePremierT + noImageCourante;
		} else if (caseATraiter instanceof L) {
			noImage = indicePremierL + noImageCourante;
		} else {
			noImage = indiceI;
		}
		// affichage de l'image
		imageCourante.setImageDrawable(getResources().getDrawable(noImage));

		// initialisation des rotations
		initRotation(dimensionCase, duration);

		// rotation de l'image
		int indiceRotation;
		indiceRotation = (caseATraiter.getRotation());
		switch (indiceRotation) {
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
	 * public void affichePlateau() { int noCase; ImageView ICT; int k=0;
	 * 
	 * for(int ligne=0; ligne<7;ligne++) { for(int colonne=0;
	 * colonne<7;colonne++) { noCase=indicePremiereCaseTableau+k; ICT =
	 * (ImageView) findViewById(noCase); afficheICT(monPlateau.getCase(ligne,
	 * colonne), ICT, 0, 42); k++; } k++; } }
	 */

	// methode permettant d'afficher la case courante
	public void afficheCaseCourante(int duration) {
		// recuperation de la case a traiter
		Case caseATraiter = maPartie.getCaseCourante();
		// recuperation de l'ImageView a traiter
		ImageView ICT = (ImageView) findViewById(R.id.CaseCourante);
		// application de la modificiation
		afficheICT(caseATraiter, ICT, duration, 80);
	}

	// methode permettant d'afficher la carte objectif du joueur
	public void afficheCarteCourante() {
		Carte carteCourante;
		int noImageCarteCourante;
		if (!maPartie.getJoueurActif().getListCarte().isEmpty()) {
			carteCourante = maPartie.getJoueurActif().getCarteObjectif();
		} else {
			carteCourante = new Carte(24 + maPartie.getJoueurActif()
					.getIdentifiant());
		}
		noImageCarteCourante = incidePremiereImageCarte
		+ carteCourante.getIdentifiant();
		imageCarteCourante.setImageDrawable(getResources().getDrawable(
				noImageCarteCourante));
	}

	// methode permettant de mettre a jour la position des joueurs
	public void affichePions() {
		for (int i = 0; i < maPartie.getListJoueur().size(); i++) {
			affichePion(maPartie.getListJoueur().get(i));
		}
	}

	// methode permettant de mettre a jour la position d'un joueur
	public void affichePion(Joueur joueurCourant) {
		int moitiee = tailleCase / 2;
		int quart = tailleCase / 4;
		int posLigne, posColonne, coordX = 70, coordY = 70;
		int nbJoueurCase;
		int numPion;
		LinearLayout pionCourant = null;

		posLigne = joueurCourant.getPosLigne();
		posColonne = joueurCourant.getPosColonne();
		numPion = joueurCourant.getIdentifiant();
		switch (numPion) {
		case 0:
			pionCourant = lbleu;
			lbleu.setVisibility(0);
			break;
		case 1:
			pionCourant = lrouge;
			lrouge.setVisibility(0);
			break;
		case 2:
			pionCourant = lvert;
			lvert.setVisibility(0);
			break;
		case 3:
			pionCourant = ljaune;
			ljaune.setVisibility(0);
			break;
		}
		nbJoueurCase = monPlateau.getCase(posLigne, posColonne).getListJoueur()
		.size();

		switch (nbJoueurCase) {
		case 1:
			coordX = posColonne * tailleCase + xmin + quart;
			coordY = posLigne * tailleCase + ymin + quart;
			break;
		case 2:
			if (joueurCourant.equals(monPlateau.getCase(posLigne, posColonne)
					.getListJoueur().get(0))) {
				coordX = posColonne * tailleCase + xmin;
				coordY = posLigne * tailleCase + ymin; // -50 a cause de la
				// barre avec le nom de
				// l'application
			} else {
				coordX = posColonne * tailleCase + xmin + moitiee;
				coordY = posLigne * tailleCase + ymin + moitiee;
			}
			break;
		case 3:
			if (joueurCourant.equals(monPlateau.getCase(posLigne, posColonne)
					.getListJoueur().get(0))) {
				coordX = posColonne * tailleCase + xmin;
				coordY = posLigne * tailleCase + ymin; // -50 a cause de la
				// barre avec le nom de
				// l'application
			} else if (joueurCourant.equals(monPlateau.getCase(posLigne,
					posColonne).getListJoueur().get(1))) {
				coordX = posColonne * tailleCase + xmin + moitiee;
				coordY = posLigne * tailleCase + ymin + moitiee;
			} else {
				coordX = posColonne * tailleCase + xmin + moitiee;
				coordY = posLigne * tailleCase + ymin; // -50 a cause de la
				// barre avec le nom de
				// l'application
			}
			break;
		case 4:
			if (joueurCourant.equals(monPlateau.getCase(posLigne, posColonne)
					.getListJoueur().get(0))) {
				coordX = posColonne * tailleCase + xmin;
				coordY = posLigne * tailleCase + ymin; // -50 a cause de la
				// barre avec le nom de
				// l'application
			} else if (joueurCourant.equals(monPlateau.getCase(posLigne,
					posColonne).getListJoueur().get(1))) {
				coordX = posColonne * tailleCase + xmin + moitiee;
				coordY = posLigne * tailleCase + ymin + moitiee;
			} else if (joueurCourant.equals(monPlateau.getCase(posLigne,
					posColonne).getListJoueur().get(2))) {
				coordX = posColonne * tailleCase + xmin + moitiee;
				coordY = posLigne * tailleCase + ymin; // -50 a cause de la
				// barre avec le nom de
				// l'application
			} else {
				coordX = posColonne * tailleCase + xmin;
				coordY = posLigne * tailleCase + ymin + moitiee;
			}
			break;
		}
		pionCourant.setPadding(coordX, coordY, 0, 0);
	}

	public void afficheScores() {
		tJ1.setTextColor(Color.WHITE);
		tJ2.setTextColor(Color.WHITE);
		tJ3.setTextColor(Color.WHITE);
		tJ4.setTextColor(Color.WHITE);
		Joueur JCT; // joueur en Cours de Traitement
		for (int i = 0; i < maPartie.getListJoueur().size(); i++) {
			JCT = maPartie.getListJoueur().get(i);
			switch (i) {
			case 0:
				tJ1.setText(JCT.getNom() + " : " + JCT.getListCarte().size());
				break;
			case 1:
				tJ2.setText(JCT.getNom() + " : " + JCT.getListCarte().size());
				break;
			case 2:
				tJ3.setText(JCT.getNom() + " : " + JCT.getListCarte().size());
				break;
			case 3:
				tJ4.setText(JCT.getNom() + " : " + JCT.getListCarte().size());
				break;
			}
		}

		switch (maPartie.getJoueurActif().getIdentifiant()) {
		case 0:
			tJ1.setTextColor(Color.rgb(0, 116, 232));
			break;
		case 1:
			tJ2.setTextColor(Color.RED);
			break;
		case 2:
			tJ3.setTextColor(Color.rgb(0, 128, 64));
			break;
		case 3:
			tJ4.setTextColor(Color.YELLOW);
			break;
		}
	}

	/*
	 * public void afficheEcran() { affichePlateau(); afficheCarteCourante();
	 * afficheCaseCourante(0); affichePions(); }
	 */

	public void tourDejeu() {

		if (plateauModif) {
			premiereModif = false;

			if (maPartie.getJoueurActif().testCarteTrouvee()) {
				// CharSequence text = "BRAVO!!!";
				// notif(text,Toast.LENGTH_SHORT,0,0,0);
				maPartie.getJoueurActif().modifCarteObjectif();
			}
			maPartie.getJoueurActif().testJoueurGagnant();

			if (maPartie.getPartieFinie()) {

				partieFinie();

				// partie finie : supprimer notif et mettre une boite de
				// dialogue pour recommencer la partie
				//CharSequence text = "Partie Gagn�e par : "+ maPartie.getJoueurActif().getNom();
				//notif(text, Toast.LENGTH_SHORT);
			} else {

				afficheScores();

				definirJoueurActif(maPartie.joueurSuivant(maPartie
						.getJoueurActif()));

				deplacement = false;
				btnAnnuler.setVisibility(4);
				jeuPossible = false;
				cacherCaseObjectif();
				btnJoueurSvt.setVisibility(0);
				// CharSequence text =
				// "A "+maPartie.getJoueurActif().getNom()+" de jouer !";
				// notif(text,Toast.LENGTH_SHORT,0,0,0);

				// text =
				// "Commencez par modifier le plateau puis d�placez votre pion";
				// notif(text,Toast.LENGTH_SHORT);
				Context lecontext = getBaseContext();
				sauvegarder(lecontext, maPartie);

				/*
				 * if (a == 0) { text = "impossible d'ouvrir fich" ; } if (a ==
				 * 1) { text = "autre erreur"; } if (a == 2) text = "ok"; if (a
				 * == 3) text ="pas ok";
				 */

				// notif(text,Toast.LENGTH_SHORT);
				afficheCarteCourante();
				afficheCaseCourante(0);

				plateauModif = false;
				if (maPartie.getJoueurActif() instanceof IA) {
					Coup coupIa = ((IA) maPartie.getJoueurActif()).jouer(indiceInterdit,flecheInterdite);
					plateauModif=true;
					MaJPlateau(coupIa.getModif(), coupIa.getSens());
					affichePions();
					tourDejeu();
				}
			}
		} else {
			CharSequence text = "Vous devez obligatoirement modifier le plateau";
			notif(text, Toast.LENGTH_SHORT);
		}
	}

	public void setFullscreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public void cacherCaseObjectif() {
		int k = 0; // compteur pour selectionner l'id de la case du tableaux
		int noCase;
		ImageView ICT;
		for (int ligne = 0; ligne < 7; ligne++) {
			for (int colonne = 0; colonne < 7; colonne++) {
				noCase = indicePremiereCaseTableau + k;
				ICT = (ImageView) findViewById(noCase);

				ICT.setPadding(0, 0, 0, 0);
				k++;
			}
			k++;
		}
	}

	public void montrerCaseObjectif() {
		Plateau monPlateau = maPartie.getMonPlateau();

		int k = 0; // compteur pour selectionner l'id de la case du tableaux
		int noCase;
		ImageView ICT;

		if (maPartie.getRegle().equals("Normal")) {
			if (!maPartie.getJoueurActif().getListCarte().isEmpty()) {
				int objectif = maPartie.getJoueurActif().getListCarte().get(0)
				.getIdentifiant();
				for (int ligne = 0; ligne < 7; ligne++) {
					for (int colonne = 0; colonne < 7; colonne++) {
						noCase = indicePremiereCaseTableau + k;
						ICT = (ImageView) findViewById(noCase);
						if (objectif == monPlateau.getCase(ligne, colonne)
								.getIdentifiant()) {
							ICT.setPadding(2, 2, 2, 2);
							ICT.setBackgroundColor(Color.RED);
						} else {
							ICT.setPadding(0, 0, 0, 0);
							// ICT.setBackgroundColor(R.color.red);
						}
						k++;
					}
					k++;
				}
			}
		} else {
			for (int ligne = 0; ligne < 7; ligne++) {
				for (int colonne = 0; colonne < 7; colonne++) {
					noCase = indicePremiereCaseTableau + k;
					ICT = (ImageView) findViewById(noCase);
					boolean objectif = false;
					int i = 0;

					while (objectif == false
							&& i < maPartie.getJoueurActif().getListCarte()
							.size()) {
						if (maPartie.getJoueurActif().getListCarte().get(i)
								.getIdentifiant() == monPlateau.getCase(ligne,
										colonne).getIdentifiant()) {
							objectif = true;
						}
						i++;
					}

					if (objectif) {
						ICT.setPadding(2, 2, 2, 2);
						ICT.setBackgroundColor(Color.RED);

					} else {
						ICT.setPadding(0, 0, 0, 0);
						// ICT.setBackgroundColor(R.color.red);
					}
					k++;
				}
				k++;
			}
		}
	}

	public Partie charger(Context context) {

		ObjectInputStream deserialise = null;
		Partie partie = null;
		try {
			deserialise = new ObjectInputStream(context.openFileInput("sauvMaPartie"));
			// InputStream openRawResource =
			// this.getResources().openRawResource(R.raw.kref);
			// deserialise = new ObjectInputStream(openRawResource);
			partie = (Partie) deserialise.readObject();
		}

		catch (NotSerializableException e) {
		//	Toast.makeText(context, "Settings not savedaa" + e.getMessage(),
		//			Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
		//	Toast.makeText(context, "Settings not savedaa" + e.getMessage(),
		//			Toast.LENGTH_SHORT).show();
		} catch (ClassNotFoundException e) {
		//	Toast.makeText(context, "Settings not savedaa" + e.getMessage(),
		//			Toast.LENGTH_SHORT).show();
		} finally {
			try {
				deserialise.close();
			}

			catch (IOException e) {
			//	Toast.makeText(context, "Settings not savedbb",
			//			Toast.LENGTH_SHORT).show();
			}
		}
		return partie;
	}
	
	public void partieFinie() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("La partie a été gagnée par \n"+ maPartie.getJoueurActif().getNom()).setCancelable(
				false).setPositiveButton("Recommencer",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						finish();
					}
				}).setNegativeButton("Menu principal",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						appelMenuPrincipal();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void appelMenuPrincipal() {
		// creation de l'intent
		Intent defineIntent = new Intent(this, Labyrinthe.class);
		// lancement de la nouvelle activity
		startActivity(defineIntent);
	}
	

}
