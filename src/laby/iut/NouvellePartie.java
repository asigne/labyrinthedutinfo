package laby.iut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class NouvellePartie extends Activity {

	Button newSolo, newRes, newLoc, rejMulti;
	String typePartie;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullscreen();
		setContentView(R.layout.nouvellepartie);

		newSolo = (Button) findViewById(R.id.newSolo);
		newLoc = (Button) findViewById(R.id.newLocal);
		newRes = (Button) findViewById(R.id.newRes);
		rejMulti = (Button) findViewById(R.id.rejMulti);

		newSolo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				typePartie = "solo";
				newSolo();

			}
		});
		
		newLoc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				typePartie = "multi";
				partieLocale();

			}
		});

		newRes.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				typePartie = "multi";
				Serveur();

			}
		});
		
		rejMulti.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				typePartie = "multi";
				Client();
			}
		});
	}
	
	public void partieLocale() {
		// creation de l'intent
		Intent defineIntent = new Intent(this, PartieLocale.class);
		Bundle objetbundle = new Bundle();
		objetbundle.putString("typePartie", typePartie);
		defineIntent.putExtras(objetbundle);
		// lancement de la nouvelle activity
		startActivity(defineIntent);
	}
	
	public void Serveur() {
		// creation de l'intent
		Intent defineIntent = new Intent(this, PlateauJeuReseau.class);
		// et de parametres
		Bundle objetbundle = new Bundle();

		String mode = "nouvellePartie";
		objetbundle.putString("mode", mode);
		objetbundle.putString("typeJoueur", "serveur");
		objetbundle.putString("typePartie", typePartie);
		defineIntent.putExtras(objetbundle);

		// lancement de la nouvelle activity
		startActivity(defineIntent);
	}

	public void newSolo() {
		// creation de l'intent
		Intent defineIntent = new Intent(this, Solo.class);
		Bundle objetbundle = new Bundle();
		objetbundle.putString("typePartie", typePartie);
		defineIntent.putExtras(objetbundle);
		// lancement de la nouvelle activity
		startActivity(defineIntent);
	}

	public void newMulti() {
		// creation de l'intent
		Intent defineIntent = new Intent(this, Multi.class);
		Bundle objetbundle = new Bundle();
		objetbundle.putString("typePartie", typePartie);
		defineIntent.putExtras(objetbundle);
		// lancement de la nouvelle activity
		startActivity(defineIntent);
	}
	

	public void Client() {
		// creation de l'intent
		Intent defineIntent = new Intent(this, PlateauJeuReseau.class);
		// et de parametres
		Bundle objetbundle = new Bundle();

		String mode = "nouvellePartie";
		objetbundle.putString("mode", mode);
		objetbundle.putString("typeJoueur", "client");
		objetbundle.putString("typePartie", typePartie);
		defineIntent.putExtras(objetbundle);

		// lancement de la nouvelle activity
		startActivity(defineIntent);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	public void setFullscreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
