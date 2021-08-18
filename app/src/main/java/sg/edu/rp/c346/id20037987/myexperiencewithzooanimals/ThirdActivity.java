package sg.edu.rp.c346.id20037987.myexperiencewithzooanimals;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etExp, etLocation;
    //RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    //RadioGroup rg;
    RatingBar rbModifyStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etAniName);
        etExp = (EditText) findViewById(R.id.etAniExp);
        etLocation = (EditText) findViewById(R.id.etLocation);

        rbModifyStars = (RatingBar) findViewById(R.id.ratingBar3);

        Intent i = getIntent();
        final AnimalExperience currentAniExp = (AnimalExperience) i.getSerializableExtra("aniExp");

        etID.setText(currentAniExp.getId()+"");
        etName.setText(currentAniExp.getName());
        etExp.setText(currentAniExp.getExp());
        etLocation.setText(currentAniExp.getLocation());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentAniExp.setName(etName.getText().toString().trim());
                currentAniExp.setExp(etExp.getText().toString().trim());
                currentAniExp.setLocation(etLocation.getText().toString().trim());
                /*int size = 0;
                try {
                    size = Integer.valueOf(etSize.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid size entered", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //currentIsland.setSize(size);

                //int selectedRB = rg.getCheckedRadioButtonId();
                //RadioButton rb = (RadioButton) findViewById(selectedRB);
                //currentSong.setStars(Integer.parseInt(rb.getText().toString()));
                int rating = (int) rbModifyStars.getRating();
                currentAniExp.setStars(rating);

                int result = dbh.updateAniExp(currentAniExp);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Animal Experience updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete " + etName.getText() + " experience?");
                myBuilder.setCancelable(false);

                DBHelper dbh = new DBHelper(ThirdActivity.this);

                //Configure the "positive" button
                /*myBuilder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }
                });*/

                //myBuilder.setPositiveButton("CANCEL", null);

                //Configure the "negative" button
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int result = dbh.deleteAniExp(currentAniExp.getId());
                        if (result>0){
                            Toast.makeText(ThirdActivity.this, "Animal Experience deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                //Configure the "nuetral" button
                //myBuilder.setNeutralButton("Cancel", null);
                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        

    }
}