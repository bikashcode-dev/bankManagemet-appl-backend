package Repositery;

import Domain.Account;

import java.util.*;

public class AccountRepositery {
    private final Map<String, Account> accountsBynumber = new HashMap<>();

    public void saveAccount(Account account){
        accountsBynumber.put(account.getAcountNumber(),account);
    }

    public List<Account > findAll() {
        return new ArrayList<>(accountsBynumber.values());
    }

    public Optional<Account> findByNumber(String accountNumber) {
        return Optional.ofNullable(accountsBynumber.get(accountNumber));
    }

    public List<Account> findByCustomerID(String customerId) {
        List<Account> accounts = new ArrayList<>();
        for(Account account : accountsBynumber.values()){
            if(account.getCustomer_id().equals(customerId)){
                accounts.add(account);
            }
        }
        return accounts;

    }
}
