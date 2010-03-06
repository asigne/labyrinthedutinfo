package laby.iut;

import java.util.ArrayList;

import Java.ImageAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

public class AffichageCartes extends Activity {

	ArrayList<Integer> liste;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setFullscreen();
		setContentView(R.layout.affichagecartes);
		TextView nbCartesRestantes = (TextView) findViewById(R.id.nbCartesRestantes);

		liste = this.getIntent().getExtras().getIntegerArrayList("listeCarte");

		GridView gridview = (GridView) findViewById(R.id.gridview);
		int nbCartes = liste.size();
		nbCartesRestantes.setText("Nombre de cartes restantes : " + (nbCartes));
		gridview.setAdapter(new ImageAdapter(this, liste));
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