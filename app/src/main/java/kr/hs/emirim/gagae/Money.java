package kr.hs.emirim.gagae;

import java.util.Date;

public class Money {
    int money;
    Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public int getMoney() {
        return money;
    }
}
