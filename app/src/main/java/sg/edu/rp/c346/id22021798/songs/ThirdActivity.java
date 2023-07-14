package sg.edu.rp.c346.id22021798.songs;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    Button update, delete, cancel;
    EditText sTitle, singers, yor, id;
    RadioGroup rating;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        cancel = findViewById(R.id.btnCancel);
        id = findViewById(R.id.etID);
        sTitle = findViewById(R.id.etTitle);
        singers = findViewById(R.id.etSingers);
        yor = findViewById(R.id.etYear);
        rating = findViewById(R.id.rgStars);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        id.setText(""+data.getId());
        sTitle.setText(data.getTitle());
        singers.setText(data.getSinger());
        yor.setText(""+data.getYear());
        String stars = data.getStars();
        if(stars.equals("1")){
            rating.check(R.id.radio1);
        } else if (stars.equals("2")) {
            rating.check(R.id.radio2);
        }else if (stars.equals("3")) {
            rating.check(R.id.radio3);
        }else if (stars.equals("4")) {
            rating.check(R.id.radio4);
        }else {
            rating.check(R.id.radio5);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setSinger(singers.getText().toString());
                data.setTitle(sTitle.getText().toString());
                data.setYear(Integer.parseInt(yor.getText().toString()));
                int ratingID = rating.getCheckedRadioButtonId();
                String star = "";
                if (ratingID == R.id.radio1) {
                    star = "1";
                } else if (ratingID == R.id.radio2) {
                    star = "2";
                } else if (ratingID == R.id.radio3) {
                    star = "3";
                } else if (ratingID == R.id.radio4) {
                    star = "4";
                } else {
                    star = "5";
                }
                data.setStars(star);
                dbh.updateSong(data);
                dbh.close();
                Toast.makeText(ThirdActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                Toast.makeText(ThirdActivity.this, "Delete successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(i);
                Toast.makeText(ThirdActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}