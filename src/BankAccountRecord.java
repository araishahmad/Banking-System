import java.util.ArrayList;

public class BankAccountRecord {
    ArrayList<User> users;
    ArrayList<Bank> banks;

    public BankAccountRecord() {
        this.users = new ArrayList<>();
        this.banks = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Boolean removeUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    public void displayAccounts() {
        for (User user : users) {
            System.out.println(" - Name of user: " + user.getFirstName() + " " + user.getLastName());
            System.out.println(" - CNIC of user: " + user.getCNIC());
            System.out.println(" - Email of user: " + user.getEmail());
            System.out.println(" - Contact Number of user: " + user.getContactNumber());
            System.out.println(" - Account Number of user: " + user.getAccountNo());
            for (Bank bank : user.getUserAccounts())
                System.out.println(" - Total Balance: " + bank.getTotalAmount());

            System.out.println();
        }
    }
}