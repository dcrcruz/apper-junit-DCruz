package com.gcash;

import org.junit.jupiter.api.*;

class BalanceServiceTest {

    private BalanceService balanceService;
    private AccountRepository accountRepository;

//    @BeforeEach
//    public void setup() {
//        // Initialize the BalanceService with a mock AccountRepository
//        accountRepository = new AccountRepository();
//        balanceService = new BalanceService(accountRepository);
//    }

    // Before Each, After Each, After All, Before All
    @BeforeEach // life cycle: per method executions
    void setup() {
        System.out.println("Setting up...");
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);
    }

    @AfterEach
    void cleanup() {
        System.out.println("Cleaning up...");
        accountRepository.deleteAllAccounts();
    }

    @BeforeAll
    static void globalSetup() {
        System.out.println("Global setup");
    }

    @AfterAll
    static void globalCleaning() {
        System.out.println("Global cleaning");
    }

    @Test
    public void testGetBalance() {
        // Create account
        String account = accountRepository.createAccount("Derick", 100.0);

        // Test the getBalance() method
        Double balance = balanceService.getBalance(account);
        Assertions.assertEquals(100.0, balance, 0.0);
    }

    @Test
    public void testDebit() {
        // Create account
        String account = accountRepository.createAccount("Derick", 100.0);

        // Test the debit() method
        balanceService.debit(account,10.0);
        Double newBalance = balanceService.getBalance(account);
        Assertions.assertEquals(90.0, newBalance);
    }

    @Test
    public void testCredit() {
        // Create account
        String account = accountRepository.createAccount("Derick", 100.0);

        // Test the debit() method
        balanceService.credit(account,10.0);
        Double newBalance = balanceService.getBalance(account);
        Assertions.assertEquals(110.0, newBalance);
    }

    @Test
    public void testInvalidAccountDebitCredit() {
        // Verify invalid Id
        balanceService.debit("derick",10.0);
        Assertions.assertEquals("Invalid ID", "Invalid ID"); //for debit
        balanceService.credit("mae",10.0);
        Assertions.assertEquals("Invalid ID", "Invalid ID"); //for credit
    }

    @Test
    public void testTransfer() {
        // Create accounts from and to
        String account1 = accountRepository.createAccount("Derick", 100.0);
        String account2 = accountRepository.createAccount("Mae", 100.0);

        // Test the transfer() method
        balanceService.transfer(account1, account2, 50.0);
        Double balance1 = balanceService.getBalance(account1);
        Double balance2 = balanceService.getBalance(account2);
        Assertions.assertEquals(50, balance1);
        Assertions.assertEquals(150, balance2);
    }

    @Test
    public void testInvalidTransferBalance() {
        //Setup
        String account1 = accountRepository.createAccount("Derick", 100.0);
        String account2 = accountRepository.createAccount("Mae", 100.0);

        //Verify Insufficient Balance
        balanceService.transfer(account1, account2, 200.0);
        Double balance1 = balanceService.getBalance(account1);
        Double balance2 = balanceService.getBalance(account2);
        Assertions.assertEquals("Insufficient balance","Insufficient balance");
    }

    @Test
    public void testInvalidTransferId() {
        //Setup
        String account1 = accountRepository.createAccount(null, 10.0);
        String account2 = accountRepository.createAccount("Mae", 10.0);

        //Verify Invalid Account ID
        balanceService.transfer(account1, account2,10.0);
        Assertions.assertEquals("Invalid ID", "Invalid ID");
    }
}
