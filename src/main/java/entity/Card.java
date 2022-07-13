package entity;

public class Card {
    private static Long counter=1L;
    Long id;
    long cardNumber;
    double withdrawalLimit;
    int cardPin;
    Long accountId;

    public Card(long cardNumber, int cardPin, Long accountId) {
        this.cardNumber = cardNumber;
        this.withdrawalLimit = 50000.00;
        this.cardPin = cardPin;
        this.accountId = accountId;
        this.id=counter;
        counter++;
    }

    public Long getId() {
        return id;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public int getCardPin() {
        return cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }
}
