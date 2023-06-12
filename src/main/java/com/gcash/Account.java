package com.gcash;

public class Account {
    private final String id;
    private final String name;
    private double balance;

    public Account(String id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    public Double setBalance(Double balance) {
        this.balance = balance;
        return balance;
    }

//    public Double senderBalance(String from) {
//        return balance;
//    }
//
//    public Double recipientBalance(String to) {
//        return balance;
//    }

    public Double put(double balance) {
        this.balance = balance;
        return balance;
    }
}