package kr.hs.emirim.gagae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class activity_setting extends AppCompatActivity {

    private TextInputEditText st_limitmoney;
    private Button btn_logout, btn_signout, btn_fixlimit;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        st_limitmoney = findViewById(R.id.st_limitmoney);
        btn_logout = findViewById(R.id.btn_logout);
        btn_signout = findViewById(R.id.btn_signout);
        btn_fixlimit = findViewById(R.id.btn_fixlimit);
        databaseReference = databaseReference.child(uid);

        btn_fixlimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> taskMap = new HashMap<String, Object>();
                taskMap.put("limitmoney", st_limitmoney.getText().toString());
                databaseReference.updateChildren(taskMap);
                startActivity(new Intent(activity_setting.this, activity_home.class));
                finish();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(activity_setting.this, "로그아웃 ㅠㅠ!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_setting.this, activity_login.class));
                    finish();
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mAuth.getCurrentUser().delete();
                    startActivity(new Intent(activity_setting.this, activity_login.class));
                Toast.makeText(activity_setting.this, "회원탈퇴 ㅠ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}