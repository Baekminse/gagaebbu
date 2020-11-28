package kr.hs.emirim.gagae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_home extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView limitmoney;
    private TextView uName;
    private ImageButton btn_add, btn_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child().addListenerForSingleValueEvent(
                new ValueEventListener () {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                    }
                });
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            //Inflate class with dataSnapShot
            Users user = dataSnapshot.getValue(Users.class);

            //...
        }
        Users user = dataSnapshot.getValue(Users.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        uName = (TextView)findViewById(R.id.username);
        uName.setText();

        limitmoney = findViewById(R.id.money);
        uName = findViewById(R.id.username);
        btn_setting = findViewById(R.id.btn_setting);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_home.this, activity_add.class));
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_home.this, activity_setting.class));
            }
        });

    }
}