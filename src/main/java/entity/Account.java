package entity;

public class Account {
    private static Long counter=1L;
    private Long id;
    private String name;
    private String accountType;
    private double balance;

    private Long accountNumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;



    public Account(String name, String accountType, Long accountNumber) {
        this.name = name;
        this.accountType = accountType;
        this.accountNumber=accountNumber;
        this.id=counter;
        counter++;
        this.balance=0.0;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        this.balance+=amount;
    }

    public void withdraw(double withdraw) {
        this.balance-=withdraw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
