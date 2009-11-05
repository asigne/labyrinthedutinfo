package Java;

import laby.iut.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    Joueur monJoueur;
    int numCarte;
    int i=0;
    
    public ImageAdapter(Context c, Joueur j1) {
        mContext = c;
        monJoueur=j1;
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
    	position=monJoueur.getListCarte().size();
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            if(i<monJoueur.getListCarte().size())
        	{
	            imageView.setLayoutParams(new GridView.LayoutParams(90, 140));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
        		numCarte=(monJoueur.getListCarte().get(i).getIdentifiant()-1);
        		i++;
        		imageView.setImageResource(0x7f020023+numCarte);
        	}
        } else {
            imageView = (ImageView) convertView;
        }
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.ua,
            R.drawable.uf,
            R.drawable.ua,
            R.drawable.uf,
            R.drawable.ua,
            R.drawable.uf,
            R.drawable.ua,
            R.drawable.uf,
            R.drawable.ua,
            R.drawable.uf,
            R.drawable.ua,
            R.drawable.uf
    };
}