package kr.hs.emirim.gagae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class activity_home extends AppCompatActivity {
    private TextView limitmoney;
    private TextView uName;
    private ImageButton btn_add, btn_setting;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        uName = (TextView) findViewById(R.id.username);
        limitmoney = findViewById(R.id.money);
        uName = findViewById(R.id.username);
        btn_setting = findViewById(R.id.btn_setting);
        btn_add = findViewById(R.id.btn_add);

        databaseReference = databaseReference.child(uid).child("name"); // 변경값을 확인할 child 이름
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    String userN = dataSnapshot.getValue().toString();
                    uName.setText(userN);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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