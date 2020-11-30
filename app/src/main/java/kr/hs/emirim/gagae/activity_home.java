package kr.hs.emirim.gagae;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_home extends AppCompatActivity {
    private TextView limitmoney, diaryTextView;
    private TextView uName, show_money;
    private ImageButton btn_add, btn_setting;
    private CalendarView calendarView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("Users");
    private DatabaseReference mReference = firebaseDatabase.getReference("Users");
    private DatabaseReference dReference = firebaseDatabase.getReference("Users");
    private long time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

        uName = (TextView) findViewById(R.id.username);
        limitmoney = findViewById(R.id.money);
        uName = findViewById(R.id.username);
        btn_setting = findViewById(R.id.btn_setting);
        btn_add = findViewById(R.id.btn_add);
        show_money = findViewById(R.id.show_money);
        diaryTextView=findViewById(R.id.diaryTextView);
        getfirebase(uid);
        getlimitmoney(uid);

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
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setVisibility(View.VISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d" ,year ,month+1 ,dayOfMonth));
                String Syear = String.valueOf(year);
                String Smonth = String.valueOf(month+1);
                String yemo = Syear+"-"+Smonth;
                dReference = dReference.child(uid).child("가계부").child(yemo).child(String.valueOf(dayOfMonth)).child("소비 금액");
                dReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            String here = dataSnapshot.getValue().toString();
                            show_money.setText(here);
                        }else {
                            show_money.setText("기록된 소비가 없어요");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    public void getfirebase(String uid) {
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
    }

    public void getlimitmoney(String uid) {
        mReference = mReference.child(uid).child("limitmoney"); // 변경값을 확인할 child 이름
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String limit = dataSnapshot.getValue().toString();
                limitmoney.setText(limit);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - time >= 2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

        else if(System.currentTimeMillis() - time < 2000 ){
            finish();
        }
    }


}
