
import java.util.ArrayList;


public class Bank {
    ArrayList<Customer> customers = new ArrayList<Customer>();
    
    void addCustomer(Customer customer) {
        customers.add(customer);
    }

    Customer getCustomer(int account) {
        return customers.get(account);
    }
    
    ArrayList<Customer> getCustomers(){
        return customers;
    }

    Customer getCustomerByAccountNumber(int accountNumber) {
        Customer customer = null;
        for(Customer c : customers){
            if(c.getAccount().getAccountNumber() == accountNumber){
                customer = c;
                break;
            }
        }
        return customer;
    }

    void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
    
}
