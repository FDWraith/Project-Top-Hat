import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PropertyMenu extends JFrame implements ActionListener{
    private Container pane;
    private Property prop;
    private Player player;
    
    public PropertyMenu(Player entry,Property belong){
	this.prop = belong;
	this.player = entry;
	
	this.setTitle("What can you do with "+belong.getName());
	this.setSize(400,400);
	//this.setLocation(300,300);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane,Y_AXIS));

	JPanel p1 = new JPanel();
	p1.setLayout(new BoxLayout(p1,X_AXIS));
	p1.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JButton b1 = new JButton("Buy");
	b1.addActionListener(this);
	b1.addActionCommand("buy");

	JButton b2 = new JButton("Don't Buy");
	b2.addActionListener(this);
	b2.addActionCommand("dont");

	JPanel p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2,Y_AXIS));
	p2.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JLabel l1 = new JLabel(belong.getName());
	l1.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel l2 = new JLabel(belong.getBuyPrice());
	l2.setAlignmentX(Component.LEFT_ALIGHNMENT);
	JLabel l3 = new JLabel(belong.getRentPrice());
	l3.setAlignmentX(Component.LEFT_ALIGHNMENT);
	JLabel l4 = new JLabel(belong.getHousePrice());
	l4.setAlignmentX(Component.LEFT_ALIGHNMENT);
	JLabel l5 = new JLabel(belong.getHotelPrice());
	l5.setAlignmentX(Component.LEFT_ALIGHNMENT);
	JLabel l6 = new JLabel(belong.getMortgage());
	l6.setAlignmentX(Component.LEFT_ALIGHNMENT);	
	
	JLabel money = new JLabel(entry.getMoney());
	money.setAlignmentX(Component.CENTER_ALIGHNMENT);
	
	
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("buy")){
	    if(entry.getMoney()<belong.getBuyPrice()){
		JOptionPane.showMessageDialog("You do not have enough money to buy this property","WARNING!",JOptionPane.ERROR_MESSAGE);
		this.dispose();
	    }else{
		int reply = JOptionPane.showConfirmDialog("Are you sure you want to buy this property?","CONFRIM YOUR CHOICE!",JOptionPane.YES_NO_OPTION);
		if(reply = JOptionPane.YES_OPTION){
		    prop.buyProperty(player);
		}else{
		    this.dispose();//temp, this will eventually be replaced by auction.
		}
	    }
	}
    }
    
}
