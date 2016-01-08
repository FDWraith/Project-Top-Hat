import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PlayerSet extends javax.swing.Jframe{
    
    public static void createGUI(){
	Jframe frame = new JFrame("Pick the number of players");
	Jframe.setDefaultLookAndFEelDecorated(True);

	JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JButton button = new JButton("Click");
	frame.getContentPane().add(button);

	frame.pack();
	frame.setVisible(true);
    }
    
}
