package program;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.*;

public class AddPanel {
    JPanel addPanel;
    /**
     * Class constructor for the bottom right corner (the add panel)
     */
    public AddPanel()
    {
        Border border = BorderFactory.createLineBorder(Color.black,1);
        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.X_AXIS));
        addPanel.setBackground(Color.white);
        addPanel.setBounds(1125,725,75,75);
        addPanel.setBorder(border);


        JLabel addLabel = new JLabel();
        addLabel.setIcon(new ImageIcon("plus.png"));
        addPanel.add(Box.createHorizontalStrut(4));
        addPanel.add(addLabel);
        addPanel.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                addMoney();
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


        FramePanel.frame.add(addPanel);
    }
    /**
     * Method which is called the user clicks the add button. This method creates a pop-up where the user can insert data for another income or expense.
     */
    public void addMoney()
    {


        JFrame addFrame= new JFrame();
        addFrame.setTitle("Add");
        addFrame.setSize(450,450);
        addFrame.setResizable(false);
        addFrame.setLayout(null);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("icon.png");
        addFrame.setIconImage(image.getImage());
        addFrame.getContentPane().setBackground(Color.white);
        addFrame.setVisible(true);

        JLabel addName= new JLabel("Name");
        addName.setBounds(10,0,300,50);
        addName.setFont(new Font("Times New Roman",Font.PLAIN,30));
        addFrame.add(addName);
        JLabel addPrice= new JLabel("Price");
        addPrice.setBounds(10,100,300,50);
        addPrice.setFont(new Font("Times New Roman",Font.PLAIN,30));
        addFrame.add(addPrice);
        JLabel addDate= new JLabel("Date");
        addDate.setBounds(10,200,300,50);
        addDate.setFont(new Font("Times New Roman",Font.PLAIN,30));
        addFrame.add(addDate);

        JTextField nameField = new JTextField();
        nameField.setBounds(10,50,410,50);
        nameField.setFont(new Font("Times New Roman",Font.PLAIN,25));
        addFrame.add(nameField);

        JTextField priceField = new JTextField();
        priceField.setBounds(10,150,410,50);
        priceField.setFont(new Font("Times New Roman",Font.PLAIN,25));
        addFrame.add(priceField);

        JTextField dateField = new JTextField();
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/YYYY");
        Date date = new Date(System.currentTimeMillis());

        dateField.setText(formatter.format(date));
        dateField.setBounds(10,250,410,50);
        dateField.setFont(new Font("Times New Roman",Font.PLAIN,25));
        addFrame.add(dateField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(10,350,200,50);
        addButton.setFont(new Font("Times New Roman",Font.PLAIN,30));
        addFrame.add(addButton);


        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(215,350,200,50);
        cancelButton.setFont(new Font("Times New Roman",Font.PLAIN,30));
        addFrame.add(cancelButton);

        addButton.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String sql = "INSERT INTO MoneyManager (mdate, name, price) VALUES (STR_TO_DATE('" + dateField.getText() + "', '%Y-%m-%d'), '" + nameField.getText() + "', '" + priceField.getText() + "')";
                    Sql.st.executeUpdate(sql);
                    MoneyPanel.addMoneyFromDatabase(DatePanel.shortDate);

                    addFrame.dispose();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Invalid data inserted!", "ERROR", JOptionPane.ERROR_MESSAGE);
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

        cancelButton.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                addFrame.dispose();
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
