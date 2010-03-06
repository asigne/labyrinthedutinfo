package laby.iut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pseudos2 extends Activity {

	Button jouer;
	EditText pseudo1, pseudo2;
	String regle, pseudoJ1, pseudoJ2, typePartie;
	int nbJoueurs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullscreen();

		setContentView(R.layout.pseudos2j);
		// r�cuperation du pseudo et des parametres de la partie
		Bundle objetParametre = this.getIntent().getExtras();
		nbJoueurs = objetParametre.getInt("nbJoueurs");
		regle = objetParametre.getString("regle");
		typePartie = objetParametre.getString("typePartie");
		jouer = (Button) findViewById(R.id.jouer);
		jouer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				pseudo1 = (EditText) findViewById(R.id.pseudoJoueur1);
				pseudoJ1 = pseudo1.getEditableText().toString();
				pseudo2 = (EditText) findViewById(R.id.pseudoJoueur2);
				pseudoJ2 = pseudo2.getEditableText().toString();
				if (pseudoJ1.length() == 0 || pseudoJ1.length() > 10
						|| pseudoJ2.length() == 0 || pseudoJ2.length() > 10) {
					CharSequence text = "pseudo entre 1 et 10 caracteres";
					int duration = Toast.LENGTH_SHORT;
					Context context = getApplicationContext();
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				} else {
					lancerPartie();
				}
			}
		});
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	public void lancerPartie() {

		// creation de l'intent
		Intent defineIntent = new Intent(this, PlateauJeu.class);

		// et de parametres
		Bundle objetbundle = new Bundle();

		String mode = "nouvellePartie";
		objetbundle.putString("mode", mode);
		objetbundle.putString("pseudoJ1", pseudoJ1);
		objetbundle.putString("pseudoJ2", pseudoJ2);
		objetbundle.putString("typePartie", typePartie);
		objetbundle.putString("regle", regle);
		objetbundle.putInt("nbJoueurs", nbJoueurs);
		defineIntent.putExtras(objetbundle);

		// lancement de la nouvelle activity
		startActivity(defineIntent);
	}

	public void setFullscreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
