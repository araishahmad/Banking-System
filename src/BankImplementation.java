package src;

import java.util.Scanner;

public class BankImplementation {
    static final int minLen = 8;
    static final int maxLen = 16;
    UserFunctions userFunctions = new UserFunctions();
    BankAccountRecord bankAccountRecord = new BankAccountRecord();

    public static void createAccount(Scanner input, BankAccountRecord bankAccountRecord, UserFunctions userFunctions){
        userFunctions.register(input, bankAccountRecord);
    }

    public static void LoginAccount(Scanner input, UserFunctions userFunctions, BankAccountRecord bankAccountRecord){
        System.out.print("Enter email id: ");
        String loginEmail = input.nextLine();
        while(!userFunctions.isValidEmail(loginEmail)){
            System.out.print("Enter valid email (admin@gmail.com): ");
            loginEmail = input.nextLine();
        }

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();
        while(!userFunctions.isValidPassword(loginPassword, minLen, maxLen)){
            System.out.print("Enter valid password: ");
            loginPassword = input.nextLine();
        }

        boolean loginUserFound = false;
        for (User loginUser : bankAccountRecord.getUsers()) {
            if (loginUser.getEmail().equals(loginEmail) && loginUser.getPassword().equals(loginPassword)) {
                loginUserFound = true;
                loggedInMenu(input, userFunctions, bankAccountRecord, loginUser);
                break;
            }
        }
        if (!loginUserFound)
            System.out.println("No account registered on the Email: " + loginEmail);
    }

    public static void loggedInMenu(Scanner input, UserFunctions userFunctions, BankAccountRecord bankAccountRecord, User loginUser){
        int caseChoice;
        do {
            System.out.println("1 - Delete user account.");
            System.out.println("2 - View account details.");
            System.out.println("3 - Deposit amount.");
            System.out.println("4 - Withdraw amount.");
            System.out.println("5 - Check balance.");
            System.out.println("6 - Update Details.");
            System.out.println("7 - Log out.");

            System.out.print("Enter choice: ");
            caseChoice = userFunctions.isValidInt(input);

            switch (caseChoice) {
                case 1:
                    userFunctions.deleteAccount(input, bankAccountRecord);

                    break;

                case 2:
                    System.out.println("Details of accounts");
                    bankAccountRecord.displayAccounts();
                    break;

                case 3:
                    System.out.print("Enter CNIC of user to deposit amount: ");
                    String depositCNIC = input.nextLine();
                    while(!userFunctions.isValidCNIC(depositCNIC)){
                        System.out.print("Invalid CNIC format, Enter again (13 digits): ");
                        depositCNIC = input.nextLine();
                    }

                    boolean userCNICFound = false;
                    for (User user : bankAccountRecord.getUsers()) {
                        if (user.getCNIC().equals(depositCNIC)) {
                            System.out.print("Enter amount to deposit for "+user.getFirstName()+" "+user.getLastName()+": ");
                            double depositAmount = userFunctions.isValidDouble(input);
                            userCNICFound = true;

                            for (Bank account : user.getUserAccounts()) {
                                account.depositAmount(depositAmount);

                                // Check if account is a SavingAccount, if yes, apply interest
                                if (account instanceof SavingAccount) {
                                    ((SavingAccount) account).interestAmount();
                                }
                            }
                            break;
                        }
                    }
                    if(!userCNICFound)
                        System.out.println("Invalid CNIC, no account registered. Please enter a valid CNIC");
                    break;

                case 4:
                    System.out.print("Enter CNIC of user to withdraw amount: ");
                    String withdrawCNIC = input.nextLine();
                    while(!userFunctions.isValidCNIC(withdrawCNIC)){
                        System.out.print("Invalid CNIC format, Enter again (13 digits): ");
                        withdrawCNIC = input.nextLine();
                    }

                    boolean validCNIC = false;
                    for (User user : bankAccountRecord.getUsers()) {
                        if (user.getCNIC().equals(withdrawCNIC)) {
                            validCNIC = true;
                            System.out.print("Enter amount to withdraw for "+user.getFirstName()+" "+user.getLastName()+": ");
                            double withdrawAmount = Double.parseDouble(input.nextLine());

                            for (Bank account : user.getUserAccounts()) {
                                account.withdrawAmount(withdrawAmount);
                            }
                            break;
                        }
                    }
                    if(!validCNIC)
                        System.out.println("Invalid CNIC, no account registered. Please enter a valid CNIC");
                    break;

                case 5:
                    System.out.print("Enter CNIC of user to check balance: ");
                    String checkBalanceCNIC = input.nextLine();
                    while(!userFunctions.isValidCNIC(checkBalanceCNIC)){
                        System.out.print("Invalid CNIC format, Enter again (13 digits): ");
                        checkBalanceCNIC = input.nextLine();
                    }

                    boolean checkBalUser = false;
                    BankAccountType bankAccountType = BankAccountType.SavingAccount;
                    for (User user : bankAccountRecord.getUsers()) {
                        if (user.getCNIC().equals(checkBalanceCNIC)) {
                            checkBalUser = true;
                            System.out.println("Account balances for user " + user.getFirstName() + " " + user.getLastName() + ":");
                            for (Bank account : user.getUserAccounts()) {
                                System.out.println("Account Number: " + user.getAccountNo());
                                System.out.println("Total balance: "+account.getTotalAmount());
                                System.out.println("Interest rate applied by Bank: "+bankAccountType.interestRate);
                            }
                            break;
                        }
                    }
                    if(!checkBalUser)
                        System.out.println("No account found registered on CNIC: "+checkBalanceCNIC);
                    break;
                case 6:
                    System.out.println("Enter CNIC of user to update details: ");
                    String updateCNIC = input.nextLine();
                    while(!userFunctions.isValidCNIC(updateCNIC)){
                        System.out.print("Invalid CNIC format, Enter again (13 digits): ");
                        updateCNIC = input.nextLine();
                    }

                    int updateChoice;
                    boolean foundUser = false;
                    for (User user : bankAccountRecord.getUsers()){
                        if (user.getCNIC().equals(updateCNIC)){
                            foundUser = true;
                            System.out.println("Update details of user: "+user.getFirstName()+" "+user.getLastName());
                            do {
                                System.out.println("1 - Update first name.");
                                System.out.println("2 - Update last name.");
                                System.out.println("3 - Update Contact Number.");
                                System.out.println("4 - Update Address.");
                                System.out.println("5 - Updated Successfully.");

                                System.out.print("Enter your choice: ");
                                updateChoice = input.nextInt();

                                switch (updateChoice) {
                                    case 1:
                                        input.nextLine();
                                        System.out.print("Enter your new first name: ");
                                        String updateFirstName = input.nextLine();
                                        user.setFirstName(updateFirstName);
                                        break;

                                    case 2:
                                        input.nextLine();
                                        System.out.print("Enter your new last name: ");
                                        String updateLastName = input.nextLine();
                                        user.setLastName(updateLastName);
                                        break;

                                    case 3:
                                        input.nextLine();
                                        System.out.print("Enter your new contact number: ");
                                        String updateContactNumber = input.nextLine();
                                        while(!userFunctions.isValidContact(updateContactNumber)){
                                            System.out.print("Invalid contact number format, Enter again (11 digits): ");
                                            updateContactNumber = input.nextLine();
                                        }

                                        user.setContactNumber(updateContactNumber);
                                        break;

                                    case 4:
                                        System.out.print("Enter new house no.: ");
                                        int updateHouseNo = userFunctions.isValidInt(input);

                                        System.out.print("Enter new street no.: ");
                                        int updateStreetNo = userFunctions.isValidInt(input);

                                        input.nextLine();
                                        System.out.print("Enter new city: ");
                                        String updateCity = input.nextLine();

                                        System.out.print("Enter new province: ");
                                        String updateProvince = input.nextLine();

                                        System.out.print("Enter new country: ");
                                        String updateCountry = input.nextLine();

                                        Address updateAddress = new Address(updateHouseNo, updateStreetNo, updateCity, updateProvince, updateCountry);
                                        user.setPostalAddress(updateAddress);
                                        break;

                                    case 5:
                                        System.out.println("Details updated successfully");
                                        break;

                                    default:
                                        throw new IllegalStateException("Invalid choice: " + updateChoice);
                                }
                            }while(updateChoice != 5);
                        }
                    }
                    if(!foundUser)
                        System.out.println("Invalid CNIC entered. Enter valid CNIC to update details.");
                    break;

                case 7:
                    System.out.println("Account logged out");
                    break;

                default:
                    throw new IllegalStateException("Invalid choice: " + caseChoice);
            }
        } while (caseChoice != 7);
    }
}