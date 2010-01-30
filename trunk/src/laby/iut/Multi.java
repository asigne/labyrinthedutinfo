package laby.iut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Multi extends Activity {

	Button newLocal, newReseau;
	String typePartie;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullscreen();

		setContentView(R.layout.multi);

		Bundle objetParametre = this.getIntent().getExtras();
		typePartie = objetParametre.getString("typePartie");

		newLocal = (Button) findViewById(R.id.newLocal);
		newReseau = (Button) findViewById(R.id.newReseau);

		newLocal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				partieLocale();
			}
		});

		newReseau.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				partieReseau();
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

	public void partieReseau() {
		// creation de l'intent
		// Intent defineIntent = new Intent(this, PartieReseau.class);
		// lancement de la nouvelle activity
		// startActivity(defineIntent);
	}

	public void setFullscreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
