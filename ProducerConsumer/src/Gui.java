import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui implements ActionListener {
    public static JLabel label;
    public static JTextField Num;
    public static  JLabel label2;
    public static  JLabel label3;
    public static  JLabel label4;
    public static  JLabel label5;
    public static  JLabel label6;
    public static  JLabel label7;
    public static  JLabel label8;
    public static JTextField Buff;
    public static JButton button;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize ( 400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        panel.setLayout (null) ;
        label = new JLabel("Number");
        label.setBounds (10, 20, 80, 25);
        panel.add (label) ;
        Num = new JTextField (20);

        Num.setBounds (100, 20, 165, 25);
        panel.add (Num);

        label2 = new JLabel("Buffer Size");
        label2.setBounds (10, 50, 80, 25);
        panel.add (label2) ;
        Buff = new JTextField (20);
//        label3 = new JLabel("Max buffer");
//        label3.setBounds (10, 150, 80, 25);
//        panel.add (label3) ;
//        label4 = new JLabel("");
//        label4.setBounds (90, 150, 80, 25);
//        panel.add (label4) ;
        label5 = new JLabel("Counter");
        label5.setBounds (10, 170, 80, 25);
        panel.add (label5) ;
        label6 = new JLabel("");
        label6.setBounds (90, 170, 80, 25);
        panel.add (label6) ;
        label7 = new JLabel("Time");
        label7.setBounds (10, 190, 80, 25);
        panel.add (label7) ;
        label8= new JLabel("");
        label8.setBounds (90, 190, 80, 25);
        panel.add (label8) ;
        Buff.setBounds (100, 50, 165, 25);
        panel.add (Buff);

        button = new JButton("Produce");
        button.setBounds(10,100,100,40);
        button.addActionListener(new Gui());
        panel.add(button);

        frame.setVisible (true) ;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String n = Num.getText();
        String b = Buff.getText();

        // System.out.println(n +" "+ b);


        int N = Integer.valueOf(n);
        int B = Integer.valueOf(b);
        Buffer bu = new Buffer(B);
        long Start =  System.currentTimeMillis();
        Producer p = new Producer(bu, N, B);
        Consumer c = new Consumer(bu);
        CounterPrime count = new CounterPrime(bu);
        max x = new max(bu);
        long end = System.currentTimeMillis();
        long time = end-Start;


//        try {
//            label4.setText(String.valueOf(bu.getMax()));
//        } catch (InterruptedException ex) {
//            throw new RuntimeException(ex);
//        }
        try {
            label6.setText(String.valueOf(bu.getPrimeCount()));
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        label8.setText(String.valueOf(time));
        }
    }
