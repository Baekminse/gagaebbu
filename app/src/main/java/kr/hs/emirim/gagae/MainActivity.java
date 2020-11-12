package kr.hs.emirim.gagae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startActivity(new Intent(MainActivity.this, activity_join.class));



        ImageButton ibt2 = (ImageButton)findViewById(R.id.imageView3);
        ibt2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(),activity_add.class);
                startActivity(i2);
            }
        });
    }

}