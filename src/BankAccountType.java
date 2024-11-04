package src;

public enum BankAccountType {
    SavingAccount(1.3),
    CurrentAccount(0.0);

    double interestRate;

    BankAccountType(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}