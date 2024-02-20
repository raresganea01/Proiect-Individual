package program;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Money {
    int ID;
    int position;
    int day;
    double price;
    String date;
    String name;
    JPanel panel;
    JLabel labelName;
    JLabel labelPrice;
    JLabel labelDate;

    /**
     * Class constructor for the money.
     *
     * @param position an integer that represents the position in the ArrayList and
     *                 in the money panel.
     * @param day      an integer that represents the day of the income or expense.
     * @param name     a string that represents the name of the income or expense.
     * @param price    a double that represents the amount of money the user gave or
     *                 get.
     */
    public Money(int position, int day, String name, double price) {
        this.position = position;
        this.day = day;
        this.name = name;
        this.price = price;
        panel = new JPanel();
        labelName = new JLabel();
        labelPrice = new JLabel();
        labelDate = new JLabel();

        Border border = BorderFactory.createLineBorder(Color.black, 1);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.setBorder(border);

        labelDate.setText(String.valueOf(day));
        labelName.setText(name);
        labelPrice.setText(String.valueOf(price));
        if (price < 0)
            labelPrice.setForeground(Color.red);
        else
            labelPrice.setForeground(Color.green);

        labelDate.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        labelName.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        labelPrice.setFont(new Font("Times New Roman", Font.PLAIN, 35));

        if (day > 9)
            panel.add(Box.createHorizontalStrut(10));
        else
            panel.add(Box.createHorizontalStrut(25));
        panel.add(labelDate);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(labelName);
        panel.add(Box.createHorizontalGlue());
        panel.add(labelPrice);
        panel.add(Box.createHorizontalStrut(10));

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editMoney();
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

    /**
     * Class constructor for the money.
     *
     * @param position an integer that represents the position in the ArrayList and
     *                 in the money panel.
     * @param ID       an integer that represents the ID of the income or expense in
     *                 the data base.
     * @param date     a string that represents the date of the income or expense.
     * @param name     a string that represents the name of the income or expense.
     * @param price    a double that represents the amount of money the user gave or
     *                 get.
     */
    public Money(int position, int ID, String date, String name, double price) {
        this.position = position;
        this.ID = ID;
        this.date = date;
        this.name = name;
        this.price = price;
        Matcher matcher = Pattern.compile("\\d+").matcher(date);
        matcher.find();
        this.day = Integer.valueOf(matcher.group());

        panel = new JPanel();
        labelName = new JLabel();
        labelPrice = new JLabel();
        labelDate = new JLabel();

        Border border = BorderFactory.createLineBorder(Color.black, 1);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.setBorder(border);

        labelDate.setText(String.valueOf(day));
        labelName.setText(name);
        labelPrice.setText(String.valueOf(price));
        if (price < 0)

            labelPrice.setForeground(Color.red);
        else
            labelPrice.setForeground(Color.green);

        labelDate.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        labelName.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        labelPrice.setFont(new Font("Times New Roman", Font.PLAIN, 35));

        if (day > 9)
            panel.add(Box.createHorizontalStrut(10));
        else
            panel.add(Box.createHorizontalStrut(25));
        panel.add(labelDate);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(labelName);
        panel.add(Box.createHorizontalGlue());
        panel.add(labelPrice);
        panel.add(Box.createHorizontalStrut(10));

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editMoney();
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

    /**
     * Method that adds the current income or expense to the money panel.
     */
    public void addToPanel() {
        MoneyPanel.moneyPanel.add(panel);
    }

    /**
     * Method that is called when the user clicks on an income or an expense. This
     * method creates a pop-up where the user can edit or delete that selection.
     */
    public void editMoney() {
        // Border border = BorderFactory.createLineBorder(Color.black,1);

        JFrame editFrame = new JFrame();
        editFrame.setTitle("Edit");
        editFrame.setSize(450, 450);
        editFrame.setResizable(false);
        editFrame.setLayout(null);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("icon.png");
        editFrame.setIconImage(image.getImage());
        editFrame.getContentPane().setBackground(Color.white);
        editFrame.setVisible(true);

        JLabel editName = new JLabel("Name");
        editName.setBounds(10, 0, 300, 50);
        editName.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        // editName.setBorder(border);
        editFrame.add(editName);
        JLabel editPrice = new JLabel("Price");
        editPrice.setBounds(10, 100, 300, 50);
        editPrice.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        // editPrice.setBorder(border);
        editFrame.add(editPrice);
        JLabel editDate = new JLabel("Date");
        editDate.setBounds(10, 200, 300, 50);
        editDate.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        // editDate.setBorder(border);
        editFrame.add(editDate);

        JTextField nameField = new JTextField();
        nameField.setText(name);
        nameField.setBounds(10, 50, 410, 50);
        nameField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        editFrame.add(nameField);

        JTextField priceField = new JTextField();
        priceField.setText(String.valueOf(price));
        priceField.setBounds(10, 150, 410, 50);
        priceField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        editFrame.add(priceField);

        JTextField dateField = new JTextField();
        dateField.setText(date);
        dateField.setBounds(10, 250, 410, 50);
        dateField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        editFrame.add(dateField);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(10, 350, 200, 50);
        editButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        editFrame.add(editButton);
        editButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String updateSql = "UPDATE moneymanager SET mdate = ?, name = ?, price = ? WHERE id = ?";
                    try (PreparedStatement pstmt = Sql.connection.prepareStatement(updateSql)) {
                        pstmt.setDate(1, java.sql.Date.valueOf(dateField.getText())); // Assuming dateField is a JTextField
                        pstmt.setString(2, nameField.getText());
                        pstmt.setDouble(3, Double.parseDouble(priceField.getText())); // Assuming priceField is a JTextField
                        pstmt.setInt(4, ID); // Assuming ID is a String

                        int rowsAffected = pstmt.executeUpdate();

                        // Check the result
                        if (rowsAffected > 0) {
                            name = nameField.getText();
                            price = Double.valueOf(priceField.getText());
                            date = dateField.getText();
                            Matcher matcher = Pattern.compile("\\d+").matcher(dateField.getText());
                            matcher.find();
                            day = Integer.valueOf(matcher.group());

                            labelName.setText(name);
                            labelPrice.setText(String.valueOf(price));
                            labelDate.setText(String.valueOf(day));
                            if (price < 0)
                                labelPrice.setForeground(Color.red);
                            else
                                labelPrice.setForeground(Color.green);
                            BalancePanel.updateData();
                            editFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "No rows updated!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (Exception e3) {
                    e3.printStackTrace(); // Log the exception for debugging purposes
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

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(215, 350, 200, 50);
        deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        editFrame.add(deleteButton);
        deleteButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String sql = "delete from moneymanager where id=" + ID;
                    MoneyPanel.deleteOneMoney(position);
                    editFrame.dispose();
                    Sql.st.executeUpdate(sql);
                } catch (SQLException e1) {
                    e1.printStackTrace();
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

    @Override
    public String toString() {
        return date + " " + name + " " + price;
        // return position + " " + date + " " + name + " " + price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
