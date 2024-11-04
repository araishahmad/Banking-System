package src;

import java.util.ArrayList;
import java.util.Scanner;

public class UserFunctions {
    static final int minLen = 8;
    static final int maxLen = 16;

    public static boolean isValidString(String str){
        return str.matches("[a-zA-Z]+");
    }

    public static boolean isValidCNIC(String CNIC){
        return CNIC.matches("\\d{13}");
    }

    public static boolean isValidContact(String contactNumber){
        return contactNumber.matches("\\d{11}");
    }

    public static int isValidInt(Scanner input){
        int value;
        while(true) {
            try {
                value = Integer.parseInt(input.nextLine());
                break;
            } catch(NumberFormatException e){
                System.out.println("Invalid input, Enter an int value: ");
            }
        } return value;
    }

    public static double isValidDouble(Scanner input){
        double value;
        while(true){
            try{
                value = Double.parseDouble(input.nextLine());
                break;
            } catch(NumberFormatException e){
                System.out.println("Invalid input, Enter a double value: ");
            }
        } return value;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password, int minimumLength, int maximumLength){
        return ((password.length() >= minimumLength) && (password.length() <= maximumLength));
    }
    public static void register(Scanner input, BankAccountRecord bankAccountRecord) {
        System.out.print("Enter first name of user: ");
        String firstName = input.nextLine();
        while(!isValidString(firstName)){
            System.out.print("Enter valid first name: ");
            firstName = input.nextLine();
        }

        System.out.print("Enter last name of user: ");
        String lastName = input.nextLine();
        while(!isValidString(lastName)){
            System.out.print("Enter valid last name: ");
            lastName = input.nextLine();
        }

        System.out.print("Enter CNIC: ");
        String CNIC = input.nextLine();
        while (!isValidCNIC(CNIC)) {
            System.out.print("Invalid CNIC format, Enter again (13 digits): ");
            CNIC = input.nextLine();
        }

        System.out.print("Enter email: ");
        String email = input.nextLine();
        for(User user : bankAccountRecord.getUsers()){
            while(user.getEmail().equals(email)){
                System.out.println("Account already registered on "+user.getEmail());
                System.out.print("Enter a new email id: ");
                email = input.nextLine();
            }
            break;
        }
        while (!isValidEmail(email)) {
            System.out.print("Invalid email entered, Enter again (admin@email.com): ");
            email = input.nextLine();
        }

        System.out.print("Enter password: ");
        String password = input.nextLine();
        final int minimumLength = 8;
        final int maximumLength = 16;
        while (!isValidPassword(password, minimumLength, maximumLength)) {
            System.out.print("Less Characters entered, (8-16 digits): ");
            password = input.nextLine();
        }

        System.out.print("Enter contact Number: ");
        String contactNumber = input.nextLine();
        while (!isValidContact(contactNumber)) {
            System.out.print("Invalid contact Number format, Enter again (11 digits): ");
            contactNumber = input.nextLine();
        }

        System.out.print("Enter house no.: ");
        int houseNo = isValidInt(input);

        System.out.print("Enter street no.: ");
        int streetNo = isValidInt(input);

        System.out.print("Enter city: ");
        String city = input.nextLine();
        while(!isValidString(city)){
            System.out.println("Enter valid city: ");
            city = input.nextLine();
        }

        System.out.print("Enter province: ");
        String province = input.nextLine();
        while(!isValidString(province)){
            System.out.println("Enter valid province: ");
            province = input.nextLine();
        }

        System.out.print("Enter country: ");
        String country = input.nextLine();
        while(!isValidString(province)){
            System.out.println("Enter valid country: ");
            country = input.nextLine();
        }

        BankAccountType selectedAccountType = null;

        do {
            for (BankAccountType bankAccountType : BankAccountType.values())
                System.out.println(" - " + bankAccountType);

            System.out.print("Select account type: ");
            String accountType = input.nextLine();
            try {
                selectedAccountType = BankAccountType.valueOf(accountType);
            } catch (IllegalArgumentException e) {
                System.out.println("Enter a valid account choice");
                continue;
            }
            break;
        } while (true);

        if (selectedAccountType.equals(BankAccountType.CurrentAccount)) {
            System.out.print("Enter total amount: ");
            double totalAmount = isValidDouble(input);

            CurrentAccount currentAccount = new CurrentAccount(totalAmount, 0.0, 0.0);
            Address address = new Address(houseNo, streetNo, city, province, country);

            int accountNumber = (int) (Math.random() * 90000) + 100000;
            String accountNumberString = String.valueOf(accountNumber);
            User newUser = new User(firstName, lastName, email, password, CNIC, accountNumberString, contactNumber, address);

            newUser.addAccounts(currentAccount);
            bankAccountRecord.addUser(newUser);
            System.out.println("Current account created successfully");
        } else {
            System.out.print("Enter total amount: ");
            double totalAmount = isValidDouble(input);

            SavingAccount savingAccount = new SavingAccount(totalAmount, 0.0, 0.0);
            savingAccount.interestAmount();
            Address address = new Address(houseNo, streetNo, city, province, country);

            int accountNumber = (int) (Math.random() * 90000) + 100000;
            String accountNumberString = String.valueOf(accountNumber);
            User newUser = new User(firstName, lastName, email, password, CNIC, accountNumberString, contactNumber, address);

            newUser.addAccounts(savingAccount);
            bankAccountRecord.addUser(newUser);
            System.out.println("Saving account created successfully");
        }
        System.out.println();
    }
    public static void deleteAccount(Scanner input, BankAccountRecord bankAccountRecord){
        System.out.print("Enter email: ");
        String deleteEmail = input.nextLine();
        while(!isValidEmail(deleteEmail)){
            System.out.print("Enter a valid email: ");
            deleteEmail = input.nextLine();
        }

        System.out.print("Enter password: ");
        String deletePassword = input.nextLine();
        while(!isValidPassword(deletePassword, minLen, maxLen)){
            System.out.print("Enter a valid password, (8-16 digits): ");
            deletePassword = input.nextLine();
        }

        System.out.print("Enter CNIC to delete account: ");
        String deleteCNIC = input.nextLine();
        while(!isValidCNIC(deleteCNIC)){
            System.out.print("Invalid CNIC format, Enter again (13 digits)");
            deleteCNIC = input.nextLine();
        }

        // Create a list to hold users to be deleted
        ArrayList<User> usersToDelete = new ArrayList<>();

        for(User deleteUser : bankAccountRecord.getUsers()) {
            if (deleteUser.getEmail().equals(deleteEmail) && deleteUser.getPassword().equals(deletePassword)) {
                BankAccountType deleteBankAccountType = null;
                do {
                    for (BankAccountType bankAccountType1 : BankAccountType.values())
                        System.out.println(" - " + bankAccountType1);

                    System.out.print("Select account type: ");
                    String deleteAccountType = input.nextLine();

                    try {
                        deleteBankAccountType = BankAccountType.valueOf(deleteAccountType);
                    } catch (IllegalArgumentException e) {
                        System.out.print("Enter a valid account choice");
                        continue;
                    }
                    break;
                } while (true);

                if (deleteBankAccountType.equals(BankAccountType.CurrentAccount)) {
                    CurrentAccount currentAccount = new CurrentAccount(deleteCNIC);
                    deleteUser.removeAccount(currentAccount);
                } else {
                    SavingAccount savingAccount = new SavingAccount(deleteCNIC);
                    deleteUser.removeAccount(savingAccount);
                }
                usersToDelete.add(deleteUser);
            }
        }
        for (User user : usersToDelete) {
            if (bankAccountRecord.removeUser(user.getEmail(), user.getPassword()))
                System.out.println(user.getFirstName() + " " + user.getLastName() + "'s account deleted successfully");
        }
    }
}