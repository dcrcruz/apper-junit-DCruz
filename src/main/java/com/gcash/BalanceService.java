package com.gcash;

public class BalanceService {

    private final AccountRepository accountRepository;

    public BalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Double getBalance(String id) {
        Account account = accountRepository.getAccount(id);
        return account.getBalance();
    }

    public void debit(String id, Double amount) {
        Account account = accountRepository.getAccount(id);

        if (account != null) {
            Double currentBalance = account.getBalance();
            account.setBalance(currentBalance - amount);
        } else {
            System.out.println("Invalid ID");
        }
    }

    public void credit(String id, Double amount) {
        Account account = accountRepository.getAccount(id);

        if (account != null) {
            Double currentBalance = account.getBalance();
            account.setBalance(currentBalance + amount);
        } else {
            System.out.println("Invalid ID");
        }
    }

    public void transfer(String from, String to, Double amount) {
        Account account1 = accountRepository.getAccount(from);
        Account account2 = accountRepository.getAccount(to);

        if (account1.getId() !=null & account2.getId() !=null) {
            Double senderBalance = account1.getBalance();
            Double recipientBalance = account2.getBalance();

            if (senderBalance >= amount) {
                account1.put(senderBalance - amount);
                account2.put(recipientBalance + amount);
            } else {
                System.out.println("Insufficient balance");;
            }
        } else {
            System.out.println("Invalid ID");
        }
    }
}