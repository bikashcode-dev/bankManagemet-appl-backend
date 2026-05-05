package Services.impl;

import Domain.Account;
import Domain.Costumer;
import Domain.Transaction;
import Domain.Type;

import Exceptions.AccoutNotFoundExecption;
import Exceptions.InsuffecientFundsExecption;
import Exceptions.ValidationException;

import Repositery.AccountRepositery;
import Repositery.CustomerRepository;
import Repositery.TransacationRepository;
import Services.BankingService;

import util.Validation;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BankServiceImpementation implements BankingService {


    private final AccountRepositery accountRepositery =  new AccountRepositery();
    private final TransacationRepository transacationRepository =  new TransacationRepository();
    private final CustomerRepository customerRepository =  new CustomerRepository();
//
//
//  validation cheak )))))))):((((((((((
    private final Validation<String> validationname = name ->{
        if(name==null||name.isEmpty())throw new ValidationException("Name khali nhi chorna hai ? Name not be empty");
    };
    private final Validation<String> validationemail = email->{
        if(email.isEmpty()||!email.contains("@")) throw new ValidationException("Email is empty and must be contains @ ");
    };

    private final Validation<String> validationaAccountType = accountType->{
        if(accountType==null||accountType.isEmpty()||!(accountType.equalsIgnoreCase("SAVINGS")||accountType.contains("CURRENT")))throw new ValidationException("Account Type is empty and type must be SAVINGS or CURRENT");
    };

    @Override
    public String openAccount(String name, String email, String accountType,String Gender) {
        String costomerId = UUID.randomUUID().toString();

        validationname.validate(name);
        validationemail.validate(email);
        validationaAccountType.validate(accountType);


        Costumer c = new Costumer(costomerId,name,email,Gender);
        customerRepository.save(c);

        String accountNumber = getAccountNumber();
        // Account object
        Account accont = new Account(accountNumber,costomerId,0,accountType);
        // Acount data  saving process
        accountRepositery.saveAccount(accont);
        return accountNumber;
    }

    @Override
    public List<Account> listofAccounts() {
        return accountRepositery.findAll().stream().sorted(Comparator.comparing(Account::getAcountNumber)).collect(Collectors.toList());
    }

    @Override
    public void deposite(String accountNumber, double amount, String note) {
        Account account = accountRepositery.findByNumber(accountNumber).orElseThrow(() -> new AccoutNotFoundExecption(" Account number not found :" +accountNumber));
        account.setBalance(account.getBalance()+amount);
        Transaction transaction = new Transaction(account.getAcountNumber(),amount,UUID.randomUUID().toString(),note,LocalDateTime.now(),Type.DEPOSIT);
        transacationRepository.add(transaction);
    }

    @Override
    public void withdraw(String accountNumber, double amount, String note) {
        Account account = accountRepositery.findByNumber(accountNumber).orElseThrow(() ->
                new AccoutNotFoundExecption("Account number not found :" +accountNumber));

        try {
            if(account.getBalance()>=amount){
                account.setBalance(account.getBalance()-amount);
            }
            else{
                IO.println("Insufficient balance ? paisa kam hai ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Transaction transaction = new Transaction(accountNumber,amount,UUID.randomUUID().toString(),note,LocalDateTime.now(),Type.WITHDRAW);
        transacationRepository.add(transaction);

    }

    @Override
    public void transfer(String fromAccount, String toAcount, double amount, String note) {
        if(fromAccount.equals(toAcount)) throw new ValidationException( "Galat Acount dala hai ? Enter an AC no : "+fromAccount);

         Account account1 = accountRepositery.findByNumber(fromAccount)
                 .orElseThrow(() -> new AccoutNotFoundExecption("Account number not found :" + fromAccount));

         Account account2 = accountRepositery.findByNumber(toAcount)
                 .orElseThrow(() -> new AccoutNotFoundExecption("Account number not found :" +toAcount));

        try {
            if(account1.getBalance()>=amount){
               account1.setBalance(account1.getBalance()-amount);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // update tracation ho rha hai yha pr ))))): OOOOOOO
        //account1.setBalance(account1.getBalance()-amount);

        account2.setBalance(account2.getBalance()+amount);

       // iska use nhi hai :-  Transaction transaction = new Transaction(account1,amount,UUID.randomUUID().toString(),)

        // less boiller plate code , jada jhmela hai ismee

        /*Transaction acount1transaction = new Transaction(account1.getAcountNumber(),
                amount,UUID.randomUUID().toString(),note,LocalDateTime.now(),Type.TRANSFER_IN); //Enum
        transacationRepository.add(acount1transaction);
        Transaction acount2transtion = new Transaction(account2.
                getAcountNumber(),amount,UUID.randomUUID().toString(),note,LocalDateTime.now(),Type.TRANSFER_OUT);
        transacationRepository.add(acount2transtion);
         */

        // on the sport obj crete
        transacationRepository.add(new Transaction(account1.getAcountNumber(),amount,UUID.randomUUID().toString(),note,LocalDateTime.now(),Type.TRANSFER_IN));
        transacationRepository.add(new Transaction(account2.getAcountNumber(),amount,UUID.randomUUID().toString(),note,LocalDateTime.now(),Type.TRANSFER_OUT));

    }

    @Override
    public List<Transaction> getStatment(String byAccountNumber) {
        return transacationRepository.findByaccount(byAccountNumber).stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountByCountomerName(String name_query) {
        String query = (name_query==null)?"":name_query.toLowerCase().trim();
        List<Account> accounts = new ArrayList<>();
        for(Costumer c : customerRepository.findAll()){
         if(c.getName().toLowerCase().contains(query)){
             accounts.addAll(accountRepositery.findByCustomerID(c.getId()));
            }
        }
        accounts.sort(Comparator.comparing(Account::getAcountNumber));
        return accounts;
    }


    private String getAccountNumber(){
        int  size =accountRepositery.findAll().size() + 1;
        return String.format("AC%06d",size);
    }
}
