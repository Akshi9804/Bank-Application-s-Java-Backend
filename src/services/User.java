package services;
import java.util.ArrayList;
import java.util.Scanner;
import services.BankAccount;
import static java.lang.System.exit;

public class User {
    String username, password,mobile,email;
    ArrayList<Integer> bankAccounts= new ArrayList<>();
    static boolean isLoggedIn=false;
    static ArrayList<User> registeredUsers= new ArrayList<>();
    public User(String username, String mobile, String email, String password){
        this.username=username;
        this.mobile=mobile;
        this.email=email;
        this.password=password;
    }

    public User(){}

    public String userSignUp(){
        registeredUsers.add(this);
        for(User user : registeredUsers)
        {
            System.out.println(user.username);
        }
        return "Successfully created an account";
    }

    public boolean userLogin(String mobile, String password){
        for (int i = 0; i < registeredUsers.size(); i++) {
            User user = registeredUsers.get(i);
            if(mobile.equals(user.mobile))
            {
                if(password.equals((user.password)))
                {
                    isLoggedIn=true;
                    System.out.println("Successfully LoggedIn");
                    user.loggedInUserActivities();
                    return true;
                }
                else{
                    System.out.println("Invalid password");
                    return false;
                }
            }
        }
        System.out.println("No account exists with the provided mobile number");
        return false;
    }

    public String userLogout()
    {
        isLoggedIn=false;
        return  "Successfully logged out";
    }

    public boolean loggedInUserActivities()
    {
        Scanner sc = new Scanner(System.in);
        while(isLoggedIn)
        {
            System.out.println("----------Menu for User Actions----------");
            System.out.println("0: Exit \n1. Get Details \n2: Create an Account \n3: Switch to an Account \n4: Logout \n");
            int x = sc.nextInt();
            sc.nextLine();
            switch(x)
            {
                case 0: exit(0);
                case 1:
                {
                    System.out.println("Username: "+this.username);
                    System.out.println("Mobile: "+this.mobile);
                    System.out.println("Email: "+this.email);
                    System.out.println("Password: "+this.password);
                    if(this.bankAccounts.isEmpty())
                    {
                        System.out.println("No bank accounts available");
                    }
                    else {
                        System.out.println("Bank Accounts: ");
                        for(int acc : this.bankAccounts)
                        {
                            System.out.print(acc+"\t");
                        }
                    }
                    System.out.println();
                    break;
                }
                case 2:
                {
                    System.out.println("Enter account holder's name:");
                    String name = sc.nextLine();

                    String type;
                    while(true)
                    {
                        System.out.println("Choose the type of account: \n1. Savings account \n2. Checking account");
                        int t = sc.nextInt();
                        type=switch(t){
                            case 1 -> "savings";
                            case 2 -> "checking";
                            default -> null;
                        };
                        if(type != null)
                            break;
                        System.out.println("Invalid option. Please choose a valid account type");
                    }

                    System.out.println("Enter initial deposit");
                    int deposit = sc.nextInt();
                    sc.nextLine();
                    BankAccount newAccount = new BankAccount(name,type,deposit);
                    this.bankAccounts.add(newAccount.accountNumber);
                    System.out.println(newAccount.createBankAccount());
                    break;
                }
                case 3:
                {
                    System.out.print("Enter the bank account number you want to switch to: ");
                    int acc=sc.nextInt();
                    System.out.println(acc);
                    sc.nextLine();
                    boolean accExistence = this.bankAccounts.contains(acc);
                    System.out.println(accExistence);
                    if(accExistence)
                    {
                        BankAccount bankAccountObj = new BankAccount();
                        bankAccountObj.switchToBankAccount(acc);
                    }
                    else{
                        System.out.println("Enter the correct bank account number");
                    }
                    break;
                }
                case 4:
                {
                    isLoggedIn=false;
                    System.out.println("Logged out successfully");
                    break;
                }
            }
        }
        return true;

    }
}
