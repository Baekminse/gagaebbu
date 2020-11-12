package kr.hs.emirim.gagae;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.auth.User;

import java.util.Date;


    @IgnoreExtraProperties
    public class Users{

        public String username;
        public String email;
        private String password;
        public int inmyPocket;
        public int limitMoney;

        public Users() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Users(String username, String email, String password, int inmyPocket, int limitMoney) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.inmyPocket = inmyPocket;
            this.limitMoney = limitMoney;
        }




}
