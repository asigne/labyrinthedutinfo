package Java;

import java.util.ArrayList;

import laby.iut.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Integer[] mThumbIds;
    private Context mContext;
    ArrayList<Integer> listeCarte;
    int nbCartes, numCarte, taille;
    int i=0;
    
    int indicePremiereCarte=R.drawable.ca;
    
    public ImageAdapter(Context c, ArrayList<Integer> listeCarte) {
        mContext = c;
        this.listeCarte = new ArrayList<Integer>();
        this.listeCarte = listeCarte;
        nbCartes=this.listeCarte.size();
        mThumbIds = new Integer[nbCartes];
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
    	position=listeCarte.size();
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            if(i<nbCartes)
        	{
	            imageView.setLayoutParams(new GridView.LayoutParams(90, 140));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(8, 8, 8, 8);
        		numCarte=(listeCarte.get(i));
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
}