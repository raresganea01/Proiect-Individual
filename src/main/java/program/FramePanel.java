package program;

import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;

public class FramePanel{
    public static JFrame frame;
    /**
     * Class constructor which creates the frame of the application.
     */
    public FramePanel()
    {
        frame = new JFrame();
        frame.setTitle("Money Manager");
        frame.setSize(1215,839);  //1215,839
        frame.setResizable(false);
        frame.setLayout(null);


        ImageIcon image = new ImageIcon("icon.png");
        frame.setIconImage(image.getImage());
        frame.setBackground(Color.white);
        new UserPanel();
        new DatePanel();
        new BalancePanel();
        new AddPanel();
        new MoneyPanel();


        new OptionsPanel();



        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(MoneyPanel.save());

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MoneyPanel.save();

                System.exit(0);
            }});

        frame.setVisible(true);

    }



}
