package kr.hs.emirim.gagae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class activity_join extends AppCompatActivity {
    private static final String TAG = "activity_join";
    private FirebaseAuth mAuth;
    private EditText join_email, join_name, join_pw;
    private Button join_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        join_email = (EditText) findViewById(R.id.join_email);
        join_name = (EditText) findViewById(R.id.join_name);
        join_pw = (EditText) findViewById(R.id.join_pw);
        join_ok = (Button) findViewById(R.id.join_ok);

        join_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(join_email.getText().toString(), join_pw.getText().toString(), join_name.getText().toString());
            }
        });
        }


    private void createUser(String email, String password, String name){
        mAuth = FirebaseAuth.getInstance();
        //파이어베이스에 신규계정 등록
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // 가입 성공
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();
                            String name = join_name.getText().toString().trim();
                            //해쉬맵 테이블을 데이터베이스 저장
                            HashMap<Object, String> hashMap = new HashMap<>();
                            hashMap.put("uid", uid);
                            hashMap.put("email", email);
                            hashMap.put("name", name);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);
                            //가입이 이루어지면 가입 화면 빠져나감.
                            Intent intent = new Intent(activity_join.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "회원가입 성공!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "회원가입 실패, email 형식, 비밀번호는 최소 6자리", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }
    public boolean onSupportNavigateUp(){
        onBackPressed(); //뒤로가기 버튼 누르면
        return super.onSupportNavigateUp();
    }
}


