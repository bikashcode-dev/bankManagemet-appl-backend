package Services;

import Domain.Account;
import Domain.Transaction;
import java.util.*;
import java.util.List;
import java.util.Map;

public interface BankingService {

     public String openAccount(String name, String email, String accountType,String gender);
     List<Account> listofAccounts();
     public void deposite(String accountNumber,double amount,String note);

     public void withdraw(String accountNumber,double amount,String note);

    void transfer(String fromAccount, String toAcount, double amount, String withdraw);

    List<Transaction> getStatment(String byAccountNumber);

    List<Account> searchAccountByCountomerName(String name_query);
}
