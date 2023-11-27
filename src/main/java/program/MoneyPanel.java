package program;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static program.Sql.connection;

public class MoneyPanel {
    static int height;
    public static List <Money> moneyList = new ArrayList<>();
    static JPanel moneyPanel;
    static JScrollBar scroll;

    /**
     * Class constructor which creates the middle panel, the most important panel where the user can see all his incomes and expenses from a month of an year searched by the user in the search/date panel.
     */
    public MoneyPanel()
    {
        moneyList = new ArrayList<>();
        height = 0;


        moneyPanel = new JPanel();
        scroll=new JScrollBar(1,-75,0,-75,-75);

        moneyPanel.setVisible(true);
        moneyPanel.setLayout(new BoxLayout(moneyPanel, BoxLayout.Y_AXIS));

        moneyPanel.setBackground(Color.blue);
        moneyPanel.setBounds(300,75,870,height);
        scroll.setBounds(1170,75,30,650);
        FramePanel.frame.add(scroll);
        scroll.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                moneyPanel.setBounds(300,scroll.getValue()*-1,870,height);

            }
        });

        addMoneyFromFile();

        try {
            addMoneyFromDatabase(DatePanel.shortDate);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        FramePanel.frame.add(moneyPanel);

    }

    /**
     * Method for adding incomes and expenses from a file.
     *
     * @version 1.0
     * Now used only for testing
     */
    public static void addMoneyFromFile()
    {
        try {
            Scanner dff = new Scanner(new File("testData.txt"));
            while(dff.hasNext())
            {
                int day = dff.nextInt();
                String name = dff.next();
                double price = dff.nextDouble();
                moneyList.add(new Money(moneyList.size(),day,name,price));
                height+=50;
                if(height>650)
                    scroll.setMaximum(scroll.getMaximum()+50);
                moneyPanel.setBounds(300,75,870,height);
                moneyList.get(moneyList.size()-1).addToPanel();
            }
            dff.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BalancePanel.updateData();
    }
    /**
     * Method for adding incomes and expenses from the data base.
     *
     * @param shortDate a string which contains a date (yyyy-MM), used for searching the incomes and expenses of a certain date
     * @throws Exception for the SQL query
     */
    public static void addMoneyFromDatabase(String shortDate) throws Exception
    {
        deleteAllMoney();

        try {
            String sql = "Select id, name ,mdate from moneymanager WHERE date_format(mdate, '%Y-%m') = DATE_FORMAT(STR_TO_DATE(?, '%Y-%m'), '%Y-%m');";
            System.out.println("Outer");

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, shortDate);
                ResultSet result = pstmt.executeQuery();

                System.out.println(result);

                System.out.println("Inner");
                while (result.next()) {
                    int ID = result.getInt(1);
                    String date = result.getString(2);
                    String name = result.getString(3);
                    double price = result.getDouble(4);
                    System.out.println(date);
                    System.out.println(name);
                    addOneMoney(ID, date, name, price);

                }
            }

        } catch (SQLException e) {
            System.out.println("Error when addFromDatabase");
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }


    }
    /**
     * Method which adds incomes and expenses from the existing ArrayList.
     */
    public static void addMoneyFromList()
    {
        moneyPanel.setVisible(false);
        height=moneyList.size()*50;
        moneyPanel.setBounds(300,75,870,height);
        moneyList.forEach(m -> m.addToPanel());
        if(height>650)
            scroll.setMaximum(scroll.getMaximum()+height-650);
        moneyPanel.setVisible(true);
        BalancePanel.updateData();
    }

    /**
     * Method which adds one income or expense in the panel.
     *
     * @param day an integer that represents the day of the income or expense.
     * @param name a string that represents the name of the income or expense.
     * @param price a double that represents the amount of money the user gave or get.
     */
    public static void addOneMoney(int day, String name, double price)
    {
        moneyPanel.setVisible(false);
        moneyList.add(new Money(moneyList.size(),day,name,price));
        height+=50;
        if(height>650)
            scroll.setMaximum(scroll.getMaximum()+50);
        moneyPanel.setBounds(300,75,870,height);
        moneyList.get(moneyList.size()-1).addToPanel();
        moneyPanel.setVisible(true);
        BalancePanel.updateData();
        sortByDate(1);
    }

    /**
     * Method which adds one income or expense in the panel.
     *
     * @param ID an integer that represents the ID of the income or expense in the data base.
     * @param date a string that represents the date of the income or expense.
     * @param name a string that represents the name of the income or expense.
     * @param price a double that represents the amount of money the user gave or get.
     */
    public static void addOneMoney(int ID,String date, String name, double price)
    {
        moneyPanel.setVisible(false);
        moneyList.add(new Money(moneyList.size(),ID,date,name,price));
        height+=50;
        if(height>650)
            scroll.setMaximum(scroll.getMaximum()+50);
        moneyPanel.setBounds(300,75,870,height);
        moneyList.get(moneyList.size()-1).addToPanel();
        moneyPanel.setVisible(true);
        BalancePanel.updateData();
        sortByDate(1);
    }
    /**
     * Method which deletes one income or expense from the panel and the ArrayList.
     *
     * @param i the index of the income or expense
     */
    public static void deleteOneMoney(int i)
    {
        //System.out.println(moneyList.toString());
        moneyPanel.setVisible(false);
        moneyList.remove(i);
        moneyPanel.remove(i);
        moneyList.forEach(m -> m.position = m.position>i? m.position-1 : m.position);
        //System.out.println(moneyList.toString());
        moneyPanel.setBounds(300,75,870,height-=50);
        if(height>650)
            scroll.setMaximum(scroll.getMaximum()-50);
        else

            scroll.setMaximum(-75);

        scroll.setValue(-75);
        moneyPanel.setVisible(true);
        BalancePanel.updateData();
    }

    /**
     * Method which deletes all incomes and expenses from the panel and the ArrayList.
     */
    public static void deleteAllMoney()
    {
        moneyList.removeAll(moneyList);
        refreshMoneyPanel();
        BalancePanel.updateData();
    }

    /**
     * Method which resets the money panel as it was before all the adding and deleting.
     */
    public static void refreshMoneyPanel()
    {
        moneyPanel.setVisible(false);
        moneyPanel.removeAll();

        scroll.setMaximum(-75);
        scroll.setValue(-75);

        height = 0;

        moneyPanel.setBounds(300,75,870,height);
        moneyPanel.setVisible(true);
    }

    /**
     * Method for sorting the ArrayList by date and then reseting the panel.
     *
     * @param ord an integer used to select the order (ascendant(1) or descendant(-1))
     */
    public static void sortByDate(int ord) {
        if (ord == 1)
            moneyList.sort((m1, m2) -> m1.day - m2.day);
        else
            moneyList.sort((m1, m2) -> m2.day - m1.day);

        AtomicInteger ok = new AtomicInteger(0);
        moneyList.forEach(m -> m.position = ok.getAndIncrement());
        refreshMoneyPanel();
        addMoneyFromList();
    }


    /**
     * Method for sorting the ArrayList by name and then reseting the panel.
     *
     * @param ord an integer used to select the order (ascendant(1) or descendant(-1))
     */
    public static void sortByName(int ord)
    {
        if(ord==1)
            moneyList.sort((m1,m2) -> {return m1.name.compareTo(m2.name);});
        else
            moneyList.sort((m1,m2) -> {return m2.name.compareTo(m1.name);});
        AtomicInteger ok = new AtomicInteger(0);
        moneyList.forEach(m -> m.position=ok.getAndIncrement());
        refreshMoneyPanel();
        addMoneyFromList();
    }

    /**
     * Method for sorting the ArrayList by price and then reseting the panel.
     *
     * @param ord an integer used to select the order (ascendant(1) or descendant(-1))
     */
    public static void sortByPrice(int ord)
    {
        if(ord==1)
            moneyList.sort((m1,m2) -> {return Double.compare(m2.price, m1.price);});
        else
            moneyList.sort((m1,m2) -> {return Double.compare(m1.price, m2.price);});
        AtomicInteger ok = new AtomicInteger(0);
        moneyList.forEach(m -> m.position=ok.getAndIncrement());
        refreshMoneyPanel();
        addMoneyFromList();
    }

    /**
     * Method used to save in a file
     *
     * @version 1.0
     * Isn't used anymore.
     */
    public static void save()
    {

        try
        {
            sortByDate(1);
            FileWriter wf = new FileWriter("data.txt");
            moneyList.forEach(m -> {try {wf.write(m.toString()+"\n");} catch (IOException e) {e.printStackTrace();}});
            wf.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
