package kr.hs.emirim.gagae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private frag_calendar fc;
    private frag_add fa;
    private frag_setting fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_calendar:
                        setFrag(0);
                        break;
                    case R.id.action_add:
                        setFrag(1);
                        break;
                    case R.id.action_setting:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        fc = new frag_calendar();
        fa = new frag_add();
        fs = new frag_setting();
        setFrag(0); //첫 프래그먼트 어떤거로 지정할지

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame,fc);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, fa);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,fs);
                ft.commit();
                break;
        }
    }
}