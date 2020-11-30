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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;


public class activity_login extends AppCompatActivity {

    private Button login_btn, join_btn;
    private TextInputEditText login_email, login_pw;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        login_btn = findViewById(R.id.login_btn);
        login_email = findViewById(R.id.login_email);
        login_pw = findViewById(R.id.login_pw);
        join_btn = findViewById(R.id.btn_join);

        login_email.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                    login_pw.requestFocus();
                    return true;
                }
                return false;
            }
        });

        login_pw.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(login_pw.getWindowToken(), 0);
                    login_btn.performClick();
                    return true;
                }
                return false;
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(login_email.getText().toString(), login_pw.getText().toString());
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_login.this, activity_join.class));
                finish();
            }
        });
    }

    private void loginUser(final String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            Toast.makeText(activity_login.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(activity_login.this, activity_home.class));
                            finish();
                        } else {
                            // 로그인 실패
                            Toast.makeText(activity_login.this, "아이디 또는 이메일이 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
