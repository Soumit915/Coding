package Java_GUI;

import javax.swing.*;
import java.awt.*;

/*<Applet
code="HelloWorld.class"
width=150 height=100>
</Applet>
*/

public class HelloWorld {
    public static void main(String[] args){
        JFrame frame = new JFrame("Soumit");
        JLabel l = new JLabel();

        // add text to label
        l.setText("label text");

        frame.show();
        System.out.println("Running Java program directly!");
    }
}