import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Displayer extends JFrame implements ActionListener{
    private int playernum;
    private Player x;
    
    private JPanel p = new JPanel();
    private JLabel moneyLabel;
    private JComboBox propertydisplay;
    javax.swing.Timer timer = new javax.swing.Timer(1000,this);



    public Displayer(Player x, int num){
	super("Player " + num + " Information");
	playernum = num;
	this.x = x;

	setSize(400,400);
	setResizable(false);
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	moneyLabel = new JLabel("Money Owned:" +x.getMoney()+"");
	propertydisplay = new JComboBox(getPropertyName());

	p.add(propertydisplay);
	p.add(moneyLabel);
	add(p);
	setVisible(true);


	timer.setInitialDelay(500);
	timer.start();
    }

    public String[] getPropertyName(){
	String [] save = new String[x.getProperties().size()];
	for(int i = 0; i < x.getProperties().size(); i++){
	    save[i] = (Game.SlotsList[x.getProperties().get(i)]).getName();
	}
	return save;
    }

    public void updateProperty(){
	propertydisplay.removeAllItems();
	for(String x: getPropertyName()){
	    propertydisplay.addItem(x);
	}
    }
    
    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	moneyLabel.setText("Money Owned:" + x.getMoney()+"");
    }
}
