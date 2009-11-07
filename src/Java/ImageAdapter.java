package Java;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    Joueur joueur;
    int nbCartes, numCarte;
    int i=0;
    
    int indicePremiereCarte=0x7f020023;
    
    public ImageAdapter(Context c, Joueur monJoueur) {
        mContext = c;
        joueur=monJoueur;
        nbCartes=monJoueur.getListCarte().size();
   }
    
    public ImageAdapter(Context c) {
      mContext = c;
    }	

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
      

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	position=joueur.getListCarte().size();
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            if(i<nbCartes)
        	{
	            imageView.setLayoutParams(new GridView.LayoutParams(90, 140));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
        		numCarte=(joueur.getListCarte().get(i).getIdentifiant());
        		i++;
        		imageView.setImageResource(indicePremiereCarte+numCarte);
        	}
        } 
        else 
        {
            imageView = (ImageView) convertView;
        }
        return imageView;
    }

    private Integer[] mThumbIds = new Integer[12];
	}