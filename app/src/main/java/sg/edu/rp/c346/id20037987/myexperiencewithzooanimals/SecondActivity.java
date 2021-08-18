package sg.edu.rp.c346.id20037987.myexperiencewithzooanimals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText etName, etExp, etLocation;
    Button btnInsert, btnShowAniExpList;
    //RadioGroup rg;
    RatingBar rbInsertStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etName = findViewById(R.id.etAniName);
        etExp = findViewById(R.id.etAniExp);
        etLocation = findViewById(R.id.etLocation);
        btnInsert = findViewById(R.id.btnInsertAniExp);
        btnShowAniExpList = findViewById(R.id.btnShowAnimalList);
        //rg = findViewById(R.id.rgStars);
        rbInsertStar = findViewById(R.id.ratingBar2);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String exp = etExp.getText().toString().trim();
                if (name.length() == 0 || exp.length() == 0){
                    Toast.makeText(SecondActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String locate = etLocation.getText().toString().trim();
                //int size = Integer.valueOf(size_str);
                //int stars = getStars();

                DBHelper dbh = new DBHelper(SecondActivity.this);

                int rating = (int) rbInsertStar.getRating();

                //long result = dbh.insertSong(title, singers, year, stars);
                long result = dbh.insertAniExp(name, exp, locate, rating);

                if (result != -1) {
                    Toast.makeText(SecondActivity.this, "Animal Experience Inserted", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etExp.setText("");
                    etLocation.setText("");
                } else {
                    Toast.makeText(SecondActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnShowAniExpList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}