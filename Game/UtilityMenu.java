import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class UtilityMenu extends JFrame implements ActionListener{
    private Container pane;
    private Utility util;
    private Player player;
    public boolean done;
    
    public UtilityMenu(Player entry,Utility belong){
	this.player = entry;
	this.util = belong;
	done = false;

	this.setTitle("What Do you want to do with "+util.getName());
	this.setSize(400,200);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));

	JPanel p1 = new JPanel();
	p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
	p1.setAlignmentX(Component.CENTER_ALIGNMENT);

	JButton b1 = new JButton("Buy");
	b1.addActionListener(this);
	b1.setActionCommand("Buy");

	JButton b2 = new JButton("Don't Buy");
	b2.addActionListener(this);
	b2.setActionCommand("Dont");

	p1.add(b1);
	p1.add(b2);
	
	JPanel p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
	p2.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JLabel l1 = new JLabel(belong.getName());
	l1.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel l2 = new JLabel("Buy Price:"+ belong.getBuyPrice());
	l2.setAlignmentX(Component.LEFT_ALIGNMENT);
	JLabel l3 = new JLabel("Dice Factor:"+belong.getDiceFactor());
	l3.setAlignmentX(Component.LEFT_ALIGNMENT);	

	p2.add(l1);
	p2.add(l2);
	p2.add(l3);
	
	JLabel money = new JLabel("Money"+entry.getMoney());
	money.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	pane.add(p2);
	pane.add(money);
	pane.add(p1);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("Buy")){
	    if(player.getMoney()<util.getBuyPrice()){
		JOptionPane.showMessageDialog(this,"You do not have enough money to buy this property","WARNING!",JOptionPane.ERROR_MESSAGE);
		terminate();
	    }else{
		int reply = JOptionPane.showConfirmDialog(this,"Are you sure you want to buy this property?","CONFRIM YOUR CHOICE!",JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_OPTION){
		    util.buyUtility(player);
		    terminate();
		}else{
		    //terminate();//temp, this will eventually be replaced by auction.
		}
	    }
	}else {
	    terminate();//temp, this will eventually be replaced by an auction.
	}
    }

    public void terminate(){
	done = true;
        /*try{
	    TimeUnit.MILLISECONDS.sleep(200);
	}catch(InterruptedException e){
	    //nothing
	}
	this.dispose();*/
    }
    
}
