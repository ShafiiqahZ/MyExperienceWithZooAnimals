package sg.edu.rp.c346.id20037987.myexperiencewithzooanimals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<AnimalExperience> aniExpList;
    //ArrayAdapter adapter;
    CustomAdapter caAniExp;
    //String islandDesc;
    int requestCode = 9;
    Button btn5Stars;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.button5Stars);
        btnInsert = (Button) this.findViewById(R.id.buttonInsert);
        DBHelper dbh = new DBHelper(this);
        aniExpList = dbh.getAllAniExp();
        dbh.close();

        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songList);
        //lv.setAdapter(adapter);

        caAniExp = new CustomAdapter(this, R.layout.row, aniExpList);

        lv.setAdapter(caAniExp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ThirdActivity.class);
                i.putExtra("aniExp", aniExpList.get(position));
                startActivityForResult(i, requestCode);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                aniExpList.clear();
                aniExpList.addAll(dbh.getAllAniExpByStars(5));
                caAniExp.notifyDataSetChanged();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == this.requestCode && resultCode == RESULT_OK){
            DBHelper dbh = new DBHelper(this);
            aniExpList.clear();
            aniExpList.addAll(dbh.getAllAniExp());
            dbh.close();
            caAniExp.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}