/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carl
 */
public class Customer {
    private final String firstName;
    private final String lastName;
    private final String ssn;
    private final Account account;

    Customer(String firstName, String lastName, String ssn, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.account = account;
    }
    
    @Override
    public String toString(){
        return "\nCustomer Information\n" +
                "First Name: " + firstName + "\n" + 
                "Last Name: " + lastName +  "\n" + 
                "SSN: " + ssn +  "\n" + 
                account;
    }
    
    public String basicInfo(){
        return " Account Number: " + account.getAccountNumber() + " - Name: " + firstName + " " + lastName;
    }
    
    Account getAccount(){
        return account;
    }
    
}
