package kr.hs.emirim.gagae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class activity_join extends AppCompatActivity {
    private static final String TAG = "activity_join";
    private FirebaseAuth mAuth;
    private TextInputEditText join_email, join_name, join_pw;
    private Button join_ok;
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        join_email = (TextInputEditText) findViewById(R.id.join_email);
        join_name = (TextInputEditText) findViewById(R.id.join_name);
        join_pw = (TextInputEditText) findViewById(R.id.join_pw);
        join_ok = (Button) findViewById(R.id.join_ok);

        join_name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                    join_email.requestFocus();
                    return true;
                }
                return false;
            }
        });

        join_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                    join_pw.requestFocus();
                    return true;
                }
                return false;
            }
        });

        join_pw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(join_pw.getWindowToken(), 0);
                    join_ok.performClick();
                    return true;
                }
                return false;
            }
        });

        join_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(join_email.getText().toString(), join_pw.getText().toString());
            }
        });


    }


    private void createUser(String email, final String password) {
        mAuth = FirebaseAuth.getInstance();
        //파이어베이스에 신규계정 등록
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // 가입 성공
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();
                            String name = join_name.getText().toString().trim();
                            String pw = join_pw.getText().toString().trim();
                            String limitmoney = "0";
                            //해쉬맵 테이블을 데이터베이스 저장
                            HashMap<Object, String> hashMap = new HashMap<>();
                            hashMap.put("uid", uid);
                            hashMap.put("email", email);
                            hashMap.put("pw", pw);
                            hashMap.put("name", name);
                            hashMap.put("limitmoney", limitmoney);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);
                            //가입이 이루어지면 가입 화면 빠져나감.
                            Intent intent = new Intent(activity_join.this, activity_login.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "회원가입 성공!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "회원가입 실패, 중복된 계정이거나 이메일 형식이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed(); //뒤로가기 버튼 누르면
        return super.onSupportNavigateUp();
    }

}
