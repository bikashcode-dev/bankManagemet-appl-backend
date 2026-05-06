package app;

import Services.BankingService;
import Services.impl.BankServiceImpementation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    Welcome to Banking Applica1tion ");
        boolean RunningStatus = true;
        BankingService bankingService = new BankServiceImpementation();

        while (RunningStatus) {
            System.out.println("""  
                     1. Open Account
                               
                     2. Deposit
                     3. Withdraw
                               
                     4. Transfer amount
                     5. Account Statement
                     6. List of Accounts
                     7. Search Account
                     8. Press Ec = exit""");
            IO.print("Enter choice: ");
            String choice = sc.nextLine().trim();
            IO.print(choice +  " Choice");

            switch (choice)
            {
                case "1" -> openAccount(sc,bankingService );
                case "2" -> deposite(sc,bankingService);
                case "3" -> withdraw(sc,bankingService);
                case "4" -> trnsfer(sc,bankingService);
                case "5" -> accountSatatmant(sc,bankingService);
                case "6" -> listofAccount(sc,bankingService);
                case "7" -> SearchAccount(sc,bankingService);
                case "0" -> RunningStatus=false;
            }
        }

    }
    
    private static void openAccount(Scanner sc,BankingService bankingService){
        IO.print ("Enter fist name:");
        String name = sc.nextLine().trim();

        IO.print ("Enter Gender :");
        String Gender = sc.nextLine().trim();

        IO.print("Enter emil: ");
        String emil = sc.nextLine().trim();

        IO.println("Enter Account Type ( SAVING ACCOUNT | CURRENT ACCOUNT (:");
        String accountType = sc.nextLine().trim();

        IO.println("Enter amount to deposit(optional)");
        String amount = sc.nextLine().trim();
        if(amount.isBlank()) {amount = "0";}

        Double initial = Double.valueOf(amount);
      //  bankingService.openAccount(name,emil,accountType);
        String accountNumber = bankingService.openAccount(name,emil,accountType,Gender);
        if(initial>0){
            bankingService.deposite(accountNumber,initial,"");
            IO.print("account Opened successfully "+accountNumber);
        }
    }

    private static void deposite(Scanner sc,BankingService bankingService){
        IO.print("Enter Account no :" );
        String accountNumber = sc.nextLine().trim();

        IO.print("Enter amount to deposit:");
        double amount = sc.nextDouble();

        bankingService.deposite(accountNumber,amount,"Deposited");// Transtion
        IO.print("Deposited sussesfullly"+accountNumber);

    }

    private static void withdraw(Scanner sc, BankingService bankingService){
        IO.print("Enter Account no :" );
        String accountNumber = sc.nextLine().trim();

        IO.print("Enter amount to withdraw :");
        double withdrawAmount = sc.nextDouble();

        bankingService.withdraw(accountNumber,withdrawAmount, "Withdrawn");
        IO.print("Withdraw sussesfullly"+accountNumber + withdrawAmount);

    }
    private static void trnsfer(Scanner sc, BankingService bankingService){
        IO.print("Enter Account NO From transfer :" );
        String fromAccount = sc.nextLine().trim();

        IO.print("Enter acount no to Transfer :" );
        String toAcount = sc.nextLine().trim();

        IO.println("Enter Amount to transfer: ");
        double amount = sc.nextDouble();

        bankingService.transfer(fromAccount,toAcount,amount,"");

    }
    private static void accountSatatmant(Scanner sc,BankingService bankingService){
        System.out.println("Enter Account No :");
        String byAccountNumber = sc.nextLine().trim();

        bankingService.getStatment(byAccountNumber).forEach(c ->{
            System.out.println(" Ac number:" + c.getAccountNumber() +" | UID "+ c.getTransactionID()+" | Rs." + c.getAmount() + " | TG ID :"+ c.getDateTime());
        });
    }


    private static void listofAccount(Scanner sc,BankingService bankingService){
        bankingService.listofAccounts().forEach(a ->{
            System.out.println(a.getAcountNumber() + " | "+a.getAccounTtype() + " | " + a.getBalance());
        });
    }
    private static void SearchAccount(Scanner sc,BankingService bankingService){
        System.out.println("Enter costomer Name by account");
        String name_query = sc.nextLine().trim();
        bankingService.searchAccountByCountomerName(name_query).forEach(account ->
                System.out.println(account.getAcountNumber()+" | "+ account.getAccounTtype()+" | " + account.getBalance()+" | "+account.getCustomer_id()));
    }
}
