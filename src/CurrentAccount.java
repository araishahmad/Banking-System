public class CurrentAccount extends Bank{

    public CurrentAccount (double totalAmount, double depositAmount, double withdrawAmount){
        super(totalAmount, depositAmount, withdrawAmount);
    }

    public CurrentAccount(String CNIC){
        super(0.0, 0.0, 0.0);

    }

    public void withdrawAmount(double withdrawAmount) {
        if (super.getTotalAmount() >= withdrawAmount){
             double totalAmount = super.getTotalAmount();
             totalAmount -= withdrawAmount;
             setTotalAmount(totalAmount);
            System.out.println(withdrawAmount + " withdrawn successfully. Updated balance: "+super.getTotalAmount());
        } else
            System.out.println("Not enough balance.");
        System.out.println();
    }
}