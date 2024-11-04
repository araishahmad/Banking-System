package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccountRecord bankAccountRecord = new BankAccountRecord();
        UserFunctions userFunctions = new UserFunctions();
        System.out.println("\nWelcome to " + Bank.bankName + "\n");

        int choice;
        do{
            System.out.println("1 - Create account");
            System.out.println("2 - Login account");
            System.out.println("3 - Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice){
                case 1:
                    BankImplementation.createAccount(input, bankAccountRecord, userFunctions);
                    break;

                case 2:
                    BankImplementation.LoginAccount(input, userFunctions, bankAccountRecord);
                    break;

                case 3:
                    System.out.println("Program terminated Successfully\n - Powered by Tech Byte");
                    break;

                default:
                    throw new IllegalStateException("Invalid choice: "+choice);
            }
        } while(choice != 3);
    }
}