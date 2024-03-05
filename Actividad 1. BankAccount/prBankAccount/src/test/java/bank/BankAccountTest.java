package bank;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

public class BankAccountTest {    
    @Test
    @DisplayName("Withdraw Test debe dar True si los datos son correctos")
    public void withdraw_enoughBalance_returnTrue() {
        BankAccount b1 = new BankAccount(10);
        assertTrue(b1.withdraw(9));
    }

    @Test
    @DisplayName("Withdraw Test debe dar False si los datos son incorrectos")
    public void withdraw_notEnoughBalance_returnFalse() {
        BankAccount b1 = new BankAccount(10);
        assertFalse(b1.withdraw(11));
    }

    @Test
    @DisplayName("Deposit Test debe devolver el nuevo balance tras un depósito")
    public void deposit_correctBalance_returnTrue() {
        BankAccount b1 = new BankAccount(10);
        int initialBalance = b1.getBalance();
        assertEquals(initialBalance+1, b1.deposit(1));
    }

    @Test
    @DisplayName("Deposit Test debe lanzar una excepción si los argumentos son inválidos")
    public void deposit_invalidArgument_throwsException() {
        BankAccount b1 = new BankAccount(10);
        assertThrows(IllegalArgumentException.class, () -> b1.deposit(-1));
    }

    @Test
    @DisplayName("Payment Test debe devolver el resultado correcto de la función payment")
    public void payment_correctResult_returnTrue() {
        BankAccount b1 = new BankAccount(10);
        double total_amount = 10000;
        double interest = 0.001;
        int months = 12;
        assertEquals(total_amount*(interest*Math.pow((1+interest), months)/(Math.pow((1+interest), months)-1)), b1.payment(total_amount, interest, months), 0);

    }

    @Test
    @DisplayName("Pending Test debe devolver amount si el mes es 0")
    public void pending_monthIsZero_returnTrue() {
        BankAccount b1 = new BankAccount(10);
        double total_amount = 10000;
        double interest = 0.001;
        int months = 12;
        int month = 0;
        assertEquals(total_amount, b1.pending(total_amount, interest, months, month), 0);
    }

    @Test
    @DisplayName("Pending Test debe devolver el monto pendiente de un préstamo en un mes específico")
    public void pending_monthNotZero_returnTrue() {
        BankAccount b1 = new BankAccount(10);
        double total_amount = 10000;
        double interest = 0.001;
        int months = 12;
        int month = 2;
        double res = total_amount;
        for(int i=1; i<=month; i++){
            double ant=res;
            res = ant - (total_amount*(interest*Math.pow((1+interest), months)/(Math.pow((1+interest), months)-1)) - interest*ant);
        }
        assertEquals(res, b1.pending(total_amount, interest, months, month), 0);
    }
}
