import entity.BankAccount;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;

public class BankAccountFrame  extends JFrame {
    private JLabel fromAccountLabel  = new JLabel("From account:");
    private JComboBox<BankAccount> fromAccountComboBox = new JComboBox<>();
    private JLabel toAccountLabel  = new JLabel("To account:");
    private JComboBox<BankAccount> toAccountComboBox = new JComboBox<>();
    private JLabel amountLabel = new JLabel("Amount: ");
    private JTextField amountTextField = new JTextField(10);
    private JButton executeTransferButton = new JButton("Execute transfer");

    public BankAccountFrame(){
        setTitle("Bank account transfers");
        List<BankAccount> bankAccounts = BankAccount.loadAll();
        setSize(400, 280);
        setLayout(new GridLayout(4, 1));
        JPanel fromPanel = new JPanel();
        fromPanel.add(fromAccountLabel);
        bankAccounts.forEach(bankAccount -> fromAccountComboBox.addItem(bankAccount));
        fromPanel.add(fromAccountComboBox);

        JPanel toPanel = new JPanel();
        toPanel.add(toAccountLabel);
        bankAccounts.forEach(bankAccount -> toAccountComboBox.addItem(bankAccount));
        toPanel.add(toAccountComboBox);

        JPanel amountPanel = new JPanel();
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);

        JPanel buttonPanel = new JPanel();
        executeTransferButton.addActionListener(this::executeMoneyTransfer);
        buttonPanel.add(executeTransferButton);

        add(fromPanel);
        add(toPanel);
        add(amountPanel);
        add(buttonPanel);
    }

    private void executeMoneyTransfer(ActionEvent e){
        BankAccount fromAccount = (BankAccount) fromAccountComboBox.getSelectedItem();
        BankAccount toAccount = (BankAccount) toAccountComboBox.getSelectedItem();
        Double amount = Double.parseDouble(amountTextField.getText());
        BankAccount.transferMoney(fromAccount, toAccount, amount);
        //refresh UI
        fromAccountComboBox.removeAllItems();
        BankAccount.loadAll().forEach(ba->fromAccountComboBox.addItem(ba));
        toAccountComboBox.removeAllItems();
        BankAccount.loadAll().forEach(ba->toAccountComboBox.addItem(ba));
        fromAccountComboBox.setSelectedItem(fromAccount);
        toAccountComboBox.setSelectedItem(toAccount);
        amountTextField.setText("");
    }

    public void showFrame(){
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        BankAccountFrame bankAccountFrame = new BankAccountFrame();
        SwingUtilities.invokeLater(bankAccountFrame::showFrame);
//        List<BankAccount> bankAccounts = BankAccount.loadAll();
//        bankAccounts.forEach(System.out::println);
//
//        BankAccount bankAccount = BankAccount.loadByAccountNumber("1234567889999999");
//        System.out.println("Dvije milje ili ne: "+bankAccount);
//        System.out.println();
//        System.out.println("Bogatiji iznosi na računima");
//        BankAccount.loadByAmountGreaterThan(2000.0).forEach(System.out::println);
    }
}
