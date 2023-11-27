package program;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;


public class DatePanel {
    static String shortDate;
    JPanel datePanel;

    /**
     * Class constructor which creates the top panel (the date/search panel).
     */
    public DatePanel()
    {

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
        Date date = new Date(System.currentTimeMillis());
        shortDate=formatter.format(date);

        datePanel = new JPanel();
        //datePanel.setBackground(Color.BLUE);
        datePanel.setBounds(300,0,900,50);
        //datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        datePanel.setLayout(new FlowLayout(0));
        FramePanel.frame.add(datePanel);
        datePanel.add(Box.createHorizontalStrut(5));

        JPanel infoPanel = new JPanel();
        //infoPanel.setBackground(Color.RED);
        infoPanel.setBounds(300,45,900,30);
        infoPanel.setLayout(new FlowLayout(0));
        FramePanel.frame.add(infoPanel);
        JLabel labelDay = new JLabel("Day");
        labelDay.setFont(new Font("Times New Roman",Font.PLAIN,20));
        infoPanel.add(labelDay);
        infoPanel.add(Box.createHorizontalStrut(15));
        JLabel labelName = new JLabel("Name");
        labelName.setFont(new Font("Times New Roman",Font.PLAIN,20));
        infoPanel.add(labelName);
        infoPanel.add(Box.createHorizontalStrut(700));
        JLabel labelPrice = new JLabel("Price");
        labelPrice.setFont(new Font("Times New Roman",Font.PLAIN,20));
        infoPanel.add(labelPrice);


        JLabel label = new JLabel("Date:");
        label.setFont(new Font("Times New Roman",Font.PLAIN,40));
        datePanel.add(label);
        datePanel.add(Box.createHorizontalStrut(10));

        JTextField shortDateField = new JTextField();
        shortDateField.setText(shortDate);
        shortDateField.setPreferredSize(new Dimension(100,40));
        shortDateField.setFont(new Font("Times New Roman",Font.PLAIN,25));
        datePanel.add(shortDateField);
//		datePanel.add(Box.createHorizontalGlue());

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(110,40));
        searchButton.setFont(new Font("Times New Roman",Font.PLAIN,25));
        datePanel.add(searchButton);
        searchButton.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                try
                {
                    MoneyPanel.addMoneyFromDatabase(shortDateField.getText());
                    shortDate=shortDateField.getText();
                } catch (Exception e3) {
                    JOptionPane.showMessageDialog(null,"Incorrect search!","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });



    }
}
