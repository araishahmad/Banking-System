package src;

public class SavingAccount extends Bank{

    public SavingAccount (double totalAmount, double depositAmount, double withdrawAmount){
        super(totalAmount,depositAmount, withdrawAmount);
    }

    public SavingAccount (String CNIC){
        super(0.0, 0.0, 0.0);
    }


    public void interestAmount(){
        BankAccountType bankAccountType = BankAccountType.SavingAccount;
        double interestAmount = (super.getTotalAmount() * bankAccountType.interestRate) / 100;
        double totalAmount = super.getTotalAmount() + interestAmount;
        super.setTotalAmount(totalAmount);
        System.out.println(interestAmount + " amount added.");
    }
}