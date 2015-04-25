package bankapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carl
 */
class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("You have insufficient funds to complete the transaction.");
    }
    
}
