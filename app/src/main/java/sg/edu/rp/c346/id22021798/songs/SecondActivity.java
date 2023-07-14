package sg.edu.rp.c346.id22021798.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button back, fiveStars;
    ListView lv;
    ArrayAdapter<String> aa;
    ArrayList<Song> arrList;
    boolean filter;

    @Override
    protected void onResume() {
        super.onResume();
        aa = new ArrayAdapter(SecondActivity.this, android.R.layout.simple_list_item_1, arrList);
        lv.setAdapter(aa);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        back = findViewById(R.id.back);
        fiveStars = findViewById(R.id.btnShow5Stars);
        lv = findViewById(R.id.lv);

        filter = false;

        DBHelper db = new DBHelper(SecondActivity.this);
        db.close();
        DBHelper lvdb = new DBHelper(SecondActivity.this);
        arrList = lvdb.getSongContent();
        lvdb.close();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        fiveStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                arrList.clear();
                if(!filter){
                    arrList.addAll(dbh.getFiveStarSongs());
                    filter = true;
                    fiveStars.setText("Show all songs");
                }
                else {
                    arrList.addAll(dbh.getSongContent());
                    filter = false;
                    fiveStars.setText("Show all songs with 5 stars");
                }
                aa.notifyDataSetChanged();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = arrList.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }
}

