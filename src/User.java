import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String CNIC;
    private String accountNo;
    private String contactNumber;
    private Address postalAddress;
    private ArrayList<Bank> userAccounts;

    public User (String firstName, String lastName, String email, String password, String CNIC, String accountNo,String contactNumber, Address postalAddress){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setCNIC(CNIC);
        setAccountNo(accountNo);
        setContactNumber(contactNumber);
        setPostalAddress(postalAddress);
        this.userAccounts = new ArrayList<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public String getCNIC() {
        return this.CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Address getPostalAddress() {
        return this.postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public void addAccounts(Bank addAccount){
        userAccounts.add(addAccount);
    }

    public void removeAccount(Bank removeAccount){
        userAccounts.remove(removeAccount);
    }

    public ArrayList<Bank> getUserAccounts(){
        return this.userAccounts;
    }
}