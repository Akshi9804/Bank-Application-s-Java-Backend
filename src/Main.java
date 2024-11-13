import java.sql.SQLOutput;
import java.util.Scanner;
import services.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.println("----------Menu for User Account Actions----------");
            System.out.println("0: Exit \n1: Sign Up \n2: Login \n");
            int x = sc.nextInt();
            sc.nextLine();
            switch(x)
            {
                case 0: exit(0);
                case 1:
                {
                    System.out.println("Enter Username:");
                    String username = sc.nextLine();

                    System.out.println("Enter Mobile:");
                    String mobile= sc.nextLine();

                    System.out.println("Enter Email:");
                    String email= sc.nextLine();

                    System.out.println("Enter Password");
                    String password = sc.nextLine();
                    User userObj= new User(username,mobile,email,password);
                    System.out.println(userObj.userSignUp());
                    break;
                }
                case 2: {
                    while(true)
                    {
                        System.out.println("Enter Mobile:");
                        String mobile = sc.nextLine();

                        System.out.println("Enter Password");
                        String password = sc.nextLine();
                        User userObj = new User();
                        boolean loginResult = userObj.userLogin(mobile,password);
                        if(loginResult)
                        {
                            break;
                        }
                    }
                    break;
                }
            }

        }
    }
}