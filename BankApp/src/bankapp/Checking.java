package bankapp;


public class Checking extends Account{
    
    Checking(int accountNumber, double initialDeposit){
        super(accountNumber);
        this.setBalance(initialDeposit);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.Checking;
    }
}
