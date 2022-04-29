import entity.BankAccount;

import java.util.List;

public class App {
    public static void main(String[] args) {
        List<BankAccount> bankAccounts = BankAccount.loadAll();
        bankAccounts.forEach(System.out::println);

        BankAccount bankAccount = BankAccount.loadByAccountNumber("1234567889999999");
        System.out.println("Dvije milje ili ne: "+bankAccount);
        System.out.println();
        System.out.println("Bogatiji iznosi na računima");
        BankAccount.loadByAmountGreaterThan(2000.0).forEach(System.out::println);
    }
}
