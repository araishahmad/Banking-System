abstract class BankStructure {
    protected double totalAmount;
    protected double withdrawAmount;
    protected double depositAmount;

    public abstract void depositAmount(double amount);

    public abstract void withdrawAmount(double amount);
}