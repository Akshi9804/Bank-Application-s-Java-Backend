package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class BankAccount {
    String accountHolderName, typeOfAccount;
    static int uniqueIDForAccount=0;
    int accountNumber, amount;
    static ArrayList<BankAccount> bankAccounts= new ArrayList<>();
    ArrayList<Transaction> transactions= new ArrayList<>();


    public BankAccount(String name, String type, int deposit) {
        this.accountNumber = uniqueIDForAccount+1;
        uniqueIDForAccount+=1;
        this.accountHolderName = name;
        this.typeOfAccount = type;
        this.amount = deposit;
    }

    public BankAccount(){}

    public String createBankAccount()
    {
        bankAccounts.add(this);
        return "Successfully created a bank account with account number: " + this.accountNumber;
    }

    public void bankAccountActivities()
    {
        Scanner sc = new Scanner(System.in);
        boolean flag=true;
        while(flag)
        {
            System.out.println("----------Menu for User Actions----------");
            System.out.println("0: Exit \n1. Get Details \n2: Check balance \n3: Deposit \n4: Withdraw \n5: Generate Statement \n6: Back \n");
            int x = sc.nextInt();
            sc.nextLine();
            switch(x)
            {
                case 0: exit(0);
                case 1:
                {
                    System.out.println("Bank account number: " + this.accountNumber);
                    System.out.println("Account holder name: "+this.accountHolderName);
                    System.out.println("Account type: " + this.typeOfAccount);
                    break;
                }
                case 2:
                {
                    System.out.println("Available amount: " + this.amount);
                    break;
                }
                case 3:
                {
                    System.out.print("Enter amount for deposit: ");
                    int money=sc.nextInt();
                    Transaction newTransaction = new Transaction("deposit",money);
                    this.amount+=money;
                    this.transactions.add(newTransaction);
                    break;
                }
                case 4:
                {
                    System.out.print("Enter amount for withdrawal: ");
                    int money=sc.nextInt();
                    System.out.println(money);
                    if(money<=this.amount) {
                        Transaction newTransaction = new Transaction("withdrawal", money);
                        this.amount -= money;
                        this.transactions.add(newTransaction);
                        System.out.println("Transaction successful");
                    }
                    else{
                        System.out.println("Available amount is less than withdrawal amount");
                    }
                    break;
                }
                case 5:
                {
                    for(Transaction t : this.transactions)
                    {

                        System.out.println("--------------------------------------");
                        System.out.println("Transaction id: "+t.transactionId);
                        System.out.println("Type of transaction: " + t.type);
                        System.out.println("Date: " + t.date);
                        System.out.println("Amount: " + t.amount);
                    }
                    break;
                }
                case 6:
                {
                    flag=false;
                    System.out.println("Getting back to user account");
                    break;
                }
            }

        }
    }
    public void switchToBankAccount(int accountNumber){
        for (int i = 0; i < bankAccounts.size(); i++)
        {
            BankAccount account = bankAccounts.get(i);
            if (accountNumber==account.accountNumber) {
                System.out.println("\n Switched to " + " " + account.accountNumber);
                account.bankAccountActivities();
            } else {
                System.out.println("Enter the correct bank account number");
            }
        }

    }

    public class Transaction{
        static int id=1;
        int transactionId, amount;
        Date date = new Date();
        String type;
        Transaction(String type, int amount)
        {
            this.transactionId=id;
            id+=1;
            this.type=type;
            this.amount=amount;
        }
    }

}
