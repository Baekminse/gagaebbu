package kr.hs.emirim.gagae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class activity_add extends AppCompatActivity {

    private TextInputEditText usemoney;
    private Button btn_commit;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Calendar cal = Calendar.getInstance();

        btn_commit = findViewById(R.id.btn_commit);
        usemoney = findViewById(R.id.usemoney);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM/dd");
        final String date = format.format(Calendar.getInstance().getTime());


        databaseReference = databaseReference.child(uid);

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usemoney != null){
                    databaseReference.child("가계부").child(date).child("소비 금액");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("가계부").child(date).child("소비 금액").setValue(usemoney.getText().toString());
                            startActivity(new Intent(activity_add.this, activity_home.class));
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }else{
                    Toast.makeText(getApplicationContext(),"값이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        usemoney.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(usemoney.getWindowToken(), 0);
                    btn_commit.performClick();
                    return true;
                }
                return false;
            }
        });



    }
}