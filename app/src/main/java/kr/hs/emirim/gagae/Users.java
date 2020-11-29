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
        public static String uid;

        public Users(String username, String email, String password, String uid, int inmyPocket, int limitMoney) {
            this.username = username;
            this.email = email;
            this.pw = password;
            this.uid = uid;
            this.inmyPocket = inmyPocket;
            this.limitMoney = limitMoney;
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Users( ) {

        }

        public static void setUsername(String username) {
            Users.username = username;
        }

        public static void setEmail(String email) {
            Users.email = email;
        }

        public static void setUid(String uid) {
            Users.uid = uid;
        }

        public static String getUsername() {
            return username;
        }

        public static String getUid() {
            return uid;
        }
    }
