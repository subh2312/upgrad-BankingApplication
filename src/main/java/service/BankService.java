package service;

public interface BankService {
    void createAccount(String name, String type, long l, String password);

    boolean validatePassword(Long id, String pass);

    void depositMoney(Long id,double amount);

    void withdrawMoney(Long id, double withdraw);

    void checkBalance(Long id);

    void displayAccountDetails(Long id);

    void getCardDetails(Long id);

    void createNewCard(Long id);

    String update(Long id,String pin, String new_pin);

    void viewTransactionHistory(Long id);
}
