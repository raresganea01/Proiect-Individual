package program;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class OptionsPanel implements ActionListener {

    JRadioButton ascending;
    JRadioButton descending;
    /**
     *  Class constructor which creates the left panel (the option panel).
     */
    public OptionsPanel()
    {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setBounds(10,90,300,725);
        FramePanel.frame.add(optionsPanel);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        JLabel optionsLabel = new JLabel("Options:");
        optionsLabel.setFont(new Font("Times New Roman",Font.PLAIN,40));
        optionsPanel.add(optionsLabel);

        JButton button1 = new JButton("Sort by Date");
        button1.setFont(new Font("Times New Roman",Font.PLAIN,25));
        JButton button2 = new JButton("Sort by Name");
        button2.setFont(new Font("Times New Roman",Font.PLAIN,25));
        JButton button3 = new JButton("Sort by Price");
        button3.setFont(new Font("Times New Roman",Font.PLAIN,25));
        JButton button4 = new JButton("Print list");
        button4.setFont(new Font("Times New Roman",Font.PLAIN,25));

        JButton exportButton = new JButton("Export to CSV");
        exportButton.setFont(new Font("Times New Roman",Font.PLAIN,25));

        JButton convertCSVtoExcelButton = new JButton("Convert to Excel");
        convertCSVtoExcelButton.setFont(new Font("Times New Roman",Font.PLAIN,25));

        ascending = new JRadioButton("Ascending",true);
        ascending.setFont(new Font("Times New Roman",Font.PLAIN,25));
        descending = new JRadioButton("Descending");
        descending.setFont(new Font("Times New Roman",Font.PLAIN,25));
        ButtonGroup group = new ButtonGroup();
        group.add(ascending);
        group.add(descending);

        optionsPanel.add(ascending);
        optionsPanel.add(descending);


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        exportButton.addActionListener(this);
        convertCSVtoExcelButton.addActionListener(this);

        optionsPanel.add(Box.createVerticalStrut(30));
        optionsPanel.add(button1);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(button2);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(button3);
        optionsPanel.add(Box.createVerticalStrut(15));
        optionsPanel.add(exportButton);
        optionsPanel.add(Box.createVerticalStrut(15));
//        optionsPanel.add(convertCSVtoExcelButton);
//        optionsPanel.add(Box.createVerticalStrut(15));
        //optionsPanel.add(button4);

    }
    /**
     * Method for the action of clicking the buttons from the options panel.
     */
    public void actionPerformed(ActionEvent ae)
    {
        int selected=1;
        if(descending.isSelected()) {
            selected=-1;
        }
        if(ae.getActionCommand().equals("Sort by Date"))
        {
            MoneyPanel.sortByDate(selected);
        }
        if(ae.getActionCommand().equals("Sort by Name"))
        {
            MoneyPanel.sortByName(selected);
        }
        if(ae.getActionCommand().equals("Sort by Price"))
        {
            MoneyPanel.sortByPrice(selected);
        }
        if(ae.getActionCommand().equals("Print list"))
        {
            MoneyPanel.moneyList.forEach(System.out::println);
        }
        if(ae.getActionCommand().equals("Export to CSV"))
        {
            System.out.println("se printeaza");
            MoneyPanel.exportToCSV();
        }
        if(ae.getActionCommand().equals("Convert to Excel"))
        {
            System.out.println("se converteste");
            MoneyPanel.convertCSVtoExcel();
        }
    }
}
