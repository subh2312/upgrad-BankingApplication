package service;

import dao.BankDatabase;
import entity.Account;
import entity.Card;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BankServiceImpl implements BankService{
    private BankDatabase database;

    public BankServiceImpl() {
        this.database = new BankDatabase();
    }

    @Override
    public void createAccount(String name, String type, long l, String password) {
        Account account=new Account(name,type,l);
        account.setPassword(password);
        database.createAccount(account);
        long cardNumber = (long) (Math.random() * 100000000000000L);
        int cardPin = (int)(Math.random()*10000);
        Card card = new Card(cardNumber,cardPin,account.getId());
        database.createCard(card);
        database.issueCard(card.getId(), account.getId());
        System.out.println("\n\tYour Account Details\n\tDont Forget Account Number\n");
        System.out.println("**************************");
        System.out.println("ID : "+account.getId());
        System.out.println("Name : "+account.getName());
        System.out.println("Account Type : "+account.getAccountType());
        System.out.println("Account Number : "+account.getAccountNumber());
        System.out.println("Daily withdrawal limit : "+card.getWithdrawalLimit());
        System.out.println("Card Number : "+card.getCardNumber());
        System.out.println("Card Pin : "+card.getCardPin());
        System.out.println("Available Balance : "+account.getBalance());
    }

    @Override
    public boolean validatePassword(Long id, String pass) {
        Account account = database.getAccountById(id);
        return (account.getPassword()==pass)?true:false;

    }

    @Override
    public void depositMoney(Long id,double amount) {
        Account account=database.getAccountById(id);
        account.deposit(amount);
        Map<String,String> newTransaction = new HashMap<>();
        newTransaction.put(String.valueOf(new Date()),"Credit : "+String.valueOf(amount));
        database.addTransaction(id,newTransaction);
        System.out.println(new Date()+" --> Credited "+amount+" successfully. Available balance : "+account.getBalance());
    }

    @Override
    public void withdrawMoney(Long id, double withdraw) {
        Account account=database.getAccountById(id);
        double currBalance = account.getBalance();
        if(withdraw>currBalance){
            System.out.println("Insufficient balance");
        }
        else {
            account.withdraw(withdraw);
            System.out.println("Available balance : "+account.getBalance());
        }

        Map<String,String> newTransaction = new HashMap<>();
        newTransaction.put("Debit",String.valueOf(withdraw));
        newTransaction.put(String.valueOf(new Date()),"Debit : "+String.valueOf(withdraw));
        database.addTransaction(id,newTransaction);
    }

    @Override
    public void checkBalance(Long id) {
        Account account=database.getAccountById(id);
        System.out.println("Available balance : "+account.getBalance());
        Map<String,String> newTransaction = new HashMap<>();
        newTransaction.put(String.valueOf(new Date()),"Generic : Balance Check");
        database.addTransaction(id,newTransaction);

    }

    @Override
    public void displayAccountDetails(Long id) {
        Account account = database.getAccountById(id);
        System.out.println("ID : "+account.getId());
        System.out.println("Name : "+account.getName());
        System.out.println("Account Type : "+account.getAccountType());
        System.out.println("Account Number : "+account.getAccountNumber());
         System.out.println("Available Balance : "+account.getBalance());
        Map<String,String> newTransaction = new HashMap<>();
        newTransaction.put(String.valueOf(new Date()),"Generic : View Account Details");
        database.addTransaction(id,newTransaction);

    }

    @Override
    public void getCardDetails(Long id) {
        Card card = database.getCardByAccountId(id);
        System.out.println("Card Number : "+card.getCardNumber());
        System.out.println("Card Pin : "+card.getCardPin());
        System.out.println("Daily withdrawal limit : "+card.getWithdrawalLimit());
        Map<String,String> newTransaction = new HashMap<>();
        newTransaction.put(String.valueOf(new Date()),"Generic : View Card Details");
        database.addTransaction(id,newTransaction);
    }

    @Override
    public void createNewCard(Long id) {
        Account account = database.getAccountById(id);
        Card card = database.getCardByAccountId(id);
        System.out.println("Card Disabled successfully : "+card.getCardNumber());
        long cardNumber = (long) (Math.random() * 100000000000000L);
        int cardPin = (int)(Math.random()*10000);
        Card newCard = new Card(cardNumber,cardPin,id);
        database.createCard(card);
        database.issueCard(card.getId(), id);
        System.out.println("New Card Issued Successfully:-");
        System.out.println("Card Number : "+card.getCardNumber());
        System.out.println("Card Pin : "+card.getCardPin());
        System.out.println("Daily withdrawal limit : "+card.getWithdrawalLimit());
        Map<String,String> newTransaction = new HashMap<>();
        newTransaction.put(String.valueOf(new Date()),"Generic : Card replacement");
        database.addTransaction(id,newTransaction);

    }

    @Override
    public String update(Long id,String key, String value) {
        Account account = database.getAccountById(id);
        switch (key){
            case "name":
                String name = account.getName();
                account.setName(value);
                database.createAccount(account);
                System.out.println("Account Name successfully changed from "+name+" to "+account.getName());
                Map<String,String> newTransaction = new HashMap<>();
                newTransaction.put(String.valueOf(new Date()),"Generic : Account name change");
                database.addTransaction(id,newTransaction);
                break;
            case "type":
                String type = account.getAccountType();
                account.setAccountType(value);
                database.createAccount(account);
                System.out.println("Account Type successfully changed from "+type+" to "+account.getAccountType());
                Map<String,String> newTransaction1 = new HashMap<>();
                newTransaction1.put(String.valueOf(new Date()),"Generic : Account Type Change");
                database.addTransaction(id,newTransaction1);
                break;
            case "pin":
                Card card = database.getCardByAccountId(id);
                int oldCardPin = card.getCardPin();
                card.setCardPin(Integer.valueOf(value));
                database.createCard(card);
                database.issueCard(card.getId(),id);
                System.out.println("Card Pin successfully changed from "+oldCardPin+" to "+card.getCardPin());
                Map<String,String> newTransaction2 = new HashMap<>();
                newTransaction2.put(String.valueOf(new Date()),"Generic : Card Pin Change");
                database.addTransaction(id,newTransaction2);
                break;
        }
        return value;
    }

    @Override
    public void viewTransactionHistory(Long id) {
        Map<String,String> newTransaction2 = new HashMap<>();
        newTransaction2.put(String.valueOf(new Date()),"Generic : View Transaction");
        database.addTransaction(id,newTransaction2);
        Map<String,String> transactions = database.getTransactions(id);
        for (Map.Entry m:transactions.entrySet()){
            int i=1;
            System.out.println(i+". "+m.getKey()+"  -->  "+m.getValue());
            i++;
        }
    }


}
