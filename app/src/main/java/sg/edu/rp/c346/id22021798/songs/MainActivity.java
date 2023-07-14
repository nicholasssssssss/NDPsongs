package sg.edu.rp.c346.id22021798.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insert, show;
    EditText sTitle, singers, yor;
    RadioGroup rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsertSong);
        show = findViewById(R.id.btnShowList);
        sTitle = findViewById(R.id.etTitle);
        singers = findViewById(R.id.etSingers);
        yor = findViewById(R.id.etYear);
        rating = findViewById(R.id.rgStars);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                int ratingID = rating.getCheckedRadioButtonId();
                String star = "";
                if(ratingID == R.id.radio1){
                    star = "1";
                }else if(ratingID == R.id.radio2){
                    star = "2";
                }else if(ratingID == R.id.radio3){
                    star = "3";
                }else if(ratingID == R.id.radio4){
                    star = "4";
                }else{
                    star = "5";
                }
                db.insertSong(sTitle.getText().toString(), singers.getText().toString(), Integer.parseInt(yor.getText().toString()), star);
                db.close();
                Toast.makeText(MainActivity.this, "New song inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }

}
