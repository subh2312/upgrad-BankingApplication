package dao;

import entity.Account;
import entity.Card;

import java.util.HashMap;
import java.util.Map;

public class BankDatabase {
    Map<Long, Account> accounts = new HashMap<>();
    Map<Long, Card> cards = new HashMap<>();
    Map<Long, Long> cardToAccount = new HashMap<>();
    Map<Long, Map<String,String >> transactionHistory = new HashMap<>();
    public void createAccount(Account account){

        accounts.put(account.getId(), account);
    }

    public void createCard(Card card){
        cards.put(card.getId(), card);
    }

    public void issueCard(Long cardId,Long accountId){
        cardToAccount.put(accountId,cardId);
    }

    public Account getAccountById(Long id) {
        Account account = accounts.get(id);
        return account;
    }

    public void addTransaction(Long id, Map<String, String> newTransaction) {
        transactionHistory.put(id, newTransaction);
    }

    public Card getCardByAccountId(Long id) {
        Long cardId = cardToAccount.get(id);
        return getCardByCardId(cardId);
    }

    private Card getCardByCardId(Long cardId) {
        return cards.get(cardId);
    }


    public Map<String, String> getTransactions(Long id) {
        return transactionHistory.get(id);

    }
}
