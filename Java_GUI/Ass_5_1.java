package Java_GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Ass_5_1 implements ActionListener{
    JFrame f;
    JTextField tf1,tf2,tf3;
    JButton b1,b2;
    JLabel warning;
    Ass_5_1(){
        f= new JFrame("Total CSE students");

        warning = new JLabel();
        warning.setText("* Please provide integer values in the input-fields");
        warning.setForeground(new Color(255,0,0));
        warning.setBounds(4, 4, 300, 15);
        warning.setVisible(false);
        f.add(warning);

        JLabel cse1_label = new JLabel();
        cse1_label.setBounds(75, 50, 200, 20);
        cse1_label.setText("CSE-1: ");
        cse1_label.setFont(new Font("Algerian",Font.BOLD,15));
        tf1=new JTextField();
        tf1.setBounds(150,50,50,20);
        cse1_label.setLabelFor(tf1);
        f.add(cse1_label);f.add(tf1);

        JLabel cse2_label = new JLabel();
        cse2_label.setBounds(75, 100, 200, 20);
        cse2_label.setText("CSE-2: ");
        cse2_label.setFont(new Font("Algerian",Font.BOLD,15));
        tf2=new JTextField();
        tf2.setBounds(150,100,50,20);
        cse2_label.setLabelFor(tf2);
        f.add(cse2_label);f.add(tf2);

        JLabel total_label = new JLabel();
        total_label.setBounds(75, 150, 200, 20);
        total_label.setText("Total: ");
        total_label.setFont(new Font("Algerian",Font.BOLD,15));
        tf3=new JTextField();
        tf3.setBounds(150,150,50,20);
        tf3.setEditable(false);
        total_label.setLabelFor(tf3);
        f.add(total_label);f.add(tf3);

        b1=new JButton("ADD");
        b1.setFont(new Font("Arial",Font.BOLD,20));
        b1.setBounds(50,200,80,50);
        f.add(b1);
        b1.addActionListener(this);

        b2=new JButton("EXIT");
        b2.setFont(new Font("Arial",Font.BOLD,20));
        b2.setBounds(150,200,80,50);
        f.add(b2);
        b2.addActionListener(this);

        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        String s1=tf1.getText();
        String s2=tf2.getText();

        if(e.getSource()==b2){
            f.setVisible(false);
            System.exit(0);
        }

        try{
            int a=Integer.parseInt(s1);
            int b=Integer.parseInt(s2);
            int c=0;
            if(e.getSource()==b1){
                c=a+b;
                warning.setVisible(false);
            }
            else{
                System.exit(0);
            }
            String result=String.valueOf(c);
            tf3.setText(result);
        }
        catch (NumberFormatException nfe){
            warning.setVisible(true);
            tf3.setText("");
        }
    }
    public static void main(String[] args) {
        new Ass_5_1();
    }
}