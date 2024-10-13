import java.util.Scanner;

public class Bank extends BankStructure{
    public static final String bankName = "WORLD TRUST BANK";
    private double totalAmount;
    private double depositAmount;
    private double withdrawAmount;

    public Bank (double totalAmount, double depositAmount, double withdrawAmount){
        setTotalAmount(totalAmount);
        setDepositAmount(depositAmount);
        setWithdrawAmount(withdrawAmount);
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDepositAmount() {
        return this.depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getWithdrawAmount() {
        return this.withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public void depositAmount(double depositAmount){
        totalAmount += depositAmount;
        System.out.println(depositAmount + " amount deposited successfully. Updated balance: "+totalAmount);
    }

    public void withdrawAmount(double withdrawAmount){
        if (totalAmount >= withdrawAmount){
            totalAmount -= withdrawAmount;
            System.out.println(withdrawAmount + " withdrawn successfully. Updated balance: "+totalAmount);
        } else
            System.out.println("Not enough balance.");
    }
}