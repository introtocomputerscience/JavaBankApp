package bankapp;

import java.io.Serializable;


public abstract class Account  implements Serializable{
    private double balance = 0;
    private int accountNumber;
    
    Account(int accountNumber){
        this.accountNumber = accountNumber;
    }
      
    public abstract AccountType getAccountType();
    
    @Override
    public String toString(){
        return "Account Type: " + getAccountType().name() + " Account\n" +
                "Account Number: " + this.getAccountNumber() + "\n" +
                "Balance: " + this.getBalance() + "\n";
    }
    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public final void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }
}
