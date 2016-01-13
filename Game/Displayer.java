import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Displayer extends JFrame{
    private int playernum;

    
    private JPanel p = new JPanel();
    private JLabel moneyLabel;
    

    public Displayer(Player x, int num){
	super("Player " + num + " Control");
	playernum = num;

	setSize(400,400);
	setResizable(false);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	
    }
    
}
