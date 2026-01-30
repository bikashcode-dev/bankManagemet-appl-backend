package Repositery;

import Domain.Transaction;

import java.util.*;

// key for ancount id and list of trandcarion store
public class TransacationRepository {
    private final Map<String, List<Transaction>> txByAccount = new HashMap<>();

    public void add(Transaction transaction) {
        List<Transaction> list = txByAccount.computeIfAbsent(transaction.getAccountNumber(), k -> new ArrayList<>());
        list.add(transaction);
    }

    public List<Transaction> findByaccount(String byAccountNumber) {
        return new ArrayList<>(txByAccount.getOrDefault(byAccountNumber,Collections.emptyList()));
    }
}
