package kr.hs.emirim.gagae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_setting extends AppCompatActivity {

    private EditText st_limitmoney;
    private Button btn_logout, btn_signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        st_limitmoney = findViewById(R.id.st_limitmoney);
        btn_logout = findViewById(R.id.btn_logout);
        btn_signout = findViewById(R.id.btn_signout);

        b


    }
}