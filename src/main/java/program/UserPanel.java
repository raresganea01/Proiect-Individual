package program;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;

public class UserPanel {
    String name;
    /**
     * Class constructor for the top left corner (the user panel).
     */
    public UserPanel()
    {
        Border border = BorderFactory.createLineBorder(Color.black,1);
        JPanel userPanel = new JPanel();
        userPanel.setBackground(new Color(105, 152, 171));
        userPanel.setBounds(0,0,300,75);
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        ImageIcon image = new ImageIcon("user.png");
        try {
            Scanner fin = new Scanner(new File("user.txt"));
            name=fin.nextLine();
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JLabel userLabel = new JLabel();
        userLabel.setIcon(image);
        userLabel.setText(name);
        userLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));
        userPanel.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                String aux = JOptionPane.showInputDialog("Change name",name);
                if(aux != null)
                    if(aux.length()!=0 && aux.length()<15)
                    {
                        name=aux;
                        userLabel.setText(name);
                        try {
                            FileWriter wf = new FileWriter("user.txt");
                            wf.write(aux);
                            wf.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Name is to long.\n Use a shorter name!","ERROR",JOptionPane.ERROR_MESSAGE);
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


        userPanel.add(userLabel);
        userPanel.setBorder(border);
        FramePanel.frame.add(userPanel);
    }
}
