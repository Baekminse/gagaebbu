package kr.hs.emirim.gagae;

import android.widget.EditText;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.auth.User;

import java.util.Date;


    @IgnoreExtraProperties
    public class Users{

        public static String username;
        public static String email;
        private String pw;
        public int inmyPocket;
        public int limitMoney;

        public Users(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.pw = password;
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Users(int inmyPocket, int limitMoney) {
            this.inmyPocket = inmyPocket;
            this.limitMoney = limitMoney;
        }
    }
