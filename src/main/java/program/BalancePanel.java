package program;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BalancePanel {

    JPanel balancePanel;
    JLabel labelIncome;
    JLabel labelExpenses;
    JLabel labelBalance;
    public static JLabel labelIncomeValue = new JLabel("0");
    public static JLabel labelExpensesValue = new JLabel("0");
    public static JLabel labelBalanceValue = new JLabel("0");
    /**
     * Class constructor which creates the bottom panel (the balance panel).
     */
    public BalancePanel()
    {
        balancePanel = new JPanel();
        JLabel labelIncome = new JLabel();
        JLabel labelExpenses = new JLabel();
        JLabel labelBalance = new JLabel();


        balancePanel.setBackground(Color.white);
        balancePanel.setBounds(300,725,825,75);
        FramePanel.frame.add(balancePanel);


        Border border = BorderFactory.createLineBorder(Color.black,1);
        balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.X_AXIS));

        balancePanel.setBorder(border);

        labelIncome.setText("Income: ");
        labelExpenses.setText("Expenses: ");
        labelBalance.setText("Balance: ");
        labelIncomeValue.setForeground(Color.green);
        labelExpensesValue.setForeground(Color.red);

        labelBalance.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelIncome.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelExpenses.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelBalanceValue.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelIncomeValue.setFont(new Font("Times New Roman",Font.PLAIN,30));
        labelExpensesValue.setFont(new Font("Times New Roman",Font.PLAIN,30));


        balancePanel.add(Box.createHorizontalStrut(10));
        balancePanel.add(labelIncome);
        balancePanel.add(labelIncomeValue);
        balancePanel.add(Box.createHorizontalGlue());
        balancePanel.add(labelExpenses);
        balancePanel.add(labelExpensesValue);
        balancePanel.add(Box.createHorizontalGlue());
        balancePanel.add(labelBalance);
        balancePanel.add(labelBalanceValue);
        balancePanel.add(Box.createHorizontalStrut(10));

    }

    /**
     * Method for updating the values of income, expenses and balance from the balance panel, using the data from the money panel.
     */
    public static void updateData()
    {

        double income = 0, expenses = 0, balance = 0;
        for(Money i : MoneyPanel.moneyList)
        {
            if(i.price>0)
                income+=i.price;
            else expenses+=i.price;
        }
        balance=income+expenses;
        labelIncomeValue.setText(String.valueOf(income));
        labelExpensesValue.setText(String.valueOf(expenses));
        labelBalanceValue.setText(String.valueOf(balance));
        if(balance<0)
            labelBalanceValue.setForeground(Color.red);
        else
            labelBalanceValue.setForeground(Color.green);

    }
}
