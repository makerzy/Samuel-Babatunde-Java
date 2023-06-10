package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        //update this
        int total = 0;
        for(AccountRecord charge: charges){
            total+= charge.getCharge();
        }
        return total;
    }

//    Made by me
    public void addCharges(AccountRecord accountRecord){
        charges.add(accountRecord);
    }

    public List<AccountRecord> getCharges() {
        return charges;
    }

    @Override
    public String toString() {
        //update this
        return String.format("%d %s %d", this.id, this.getName(), this.getBalance());
    }
}
