package kr.hs.emirim.gagae;

import com.google.firebase.firestore.auth.User;

public class UserInfo {
    public int limit_money;
    public String name;
    public int use_money;
    public int income_money;

    public UserInfo(){
        
    }

    public UserInfo(int limit_money, String name, int use_money, int income_money) {
        this.limit_money = limit_money;
        this.name = name;
        this.use_money = use_money;
        this.income_money = income_money;
    }

    public int getLimit_money() {
        return limit_money;
    }

    public void setLimit_money(int limit_money) {
        this.limit_money = limit_money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUse_money() {
        return use_money;
    }

    public void setUse_money(int use_money) {
        this.use_money = use_money;
    }

    public int getIncome_money() {
        return income_money;
    }

    public void setIncome_money(int income_money) {
        this.income_money = income_money;
    }


}
