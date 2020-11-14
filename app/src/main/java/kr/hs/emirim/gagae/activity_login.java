package kr.hs.emirim.gagae;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class activity_login extends AppCompatActivity {

    private Button login_button;
    private EditText login_email, login_pw;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private static final String TAG = "SignInActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        login_button = findViewById(R.id.login_btn);
        login_email = findViewById(R.id.login_email);
        login_pw = findViewById(R.id.login_pw);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_login.this, activity_join.class));
                finish();
            }
        });
    }



}