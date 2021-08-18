package sg.edu.rp.c346.id20037987.myexperiencewithzooanimals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<AnimalExperience> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<AnimalExperience> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        versionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater) //LINE 29-33 is the same for all files where it will read the file to find the textViewName etc
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewAniName);
        TextView tvDesc = rowView.findViewById(R.id.textViewExperience);
        //TextView tvStar = rowView.findViewById(R.id.textViewStar);

        RatingBar rbStars = rowView.findViewById(R.id.ratingBar);

        TextView tvLocation = rowView.findViewById(R.id.textViewLocation);

        ImageView ivAnimal = rowView.findViewById(R.id.ivImage);

        // Obtain the Android Version information based on the position
        AnimalExperience currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentVersion.getName());
        tvLocation.setText(currentVersion.getLocation());

        //rbStars.getNumStars();
        rbStars.setRating(currentVersion.getStars());

        //rbStars.setText(String.valueOf(currentVersion.getStars()));
        //tvStar.setText(String.valueOf(currentVersion.toString()));
        tvDesc.setText(currentVersion.getExp());

        if (currentVersion.getName().equalsIgnoreCase("Monkey") ) {
            ivAnimal.setImageResource(R.drawable.monkey);
            //ivNew.setVisibility(View.VISIBLE);
        } else if (currentVersion.getName().equalsIgnoreCase("Giraffe")){
            ivAnimal.setImageResource(R.drawable.giraffe);
        } else if (currentVersion.getName().equalsIgnoreCase("Hippo")){
            ivAnimal.setImageResource(R.drawable.hippo);
        } else if (currentVersion.getName().equalsIgnoreCase("Tiger")){
            ivAnimal.setImageResource(R.drawable.tiger);
        } else if (currentVersion.getName().equalsIgnoreCase("Zebra")){
            ivAnimal.setImageResource(R.drawable.zebra);
        } else {
            ivAnimal.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

}
