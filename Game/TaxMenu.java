import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TaxMenu extends JFrame implements ActionListener{
    private Player player;
    private Container pane;
    public boolean done;
    private Tax tax;
    
    public TaxMenu(Player entry, Tax belong){
	this.player = entry;
	this.tax = belong;
	this.done = false;

	this.setTitle("Player "+(player.getIndex()+1)+", you have to pay a "+tax.getName()+".");
	this.setSize(400,400);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));

	JPanel p1 = new JPanel();
	p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
	p1.setAlignmentX(Component.CENTER_ALIGNMENT);

	JLabel l1 = new JLabel(tax.getName());
	l1.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel l2 = new JLabel("Make your decision about how much you want to pay");
	l2.setAlignmentX(Component.CENTER_ALIGNMENT);

	p1.add(l1);
	p1.add(l2);
	
	JPanel p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
	p2.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	JButton b1 = new JButton("Pay 10% of Total Money");
	b1.addActionListener(this);
	b1.setActionCommand("10");

	JButton b2 = new JButton("Pay $"+tax.getPayPrice());
	b2.addActionListener(this);
	b2.setActionCommand("Other");

	JLabel money = new JLabel("Amount of Money: "+player.getMoney());
	money.setAlignmentX(Component.CENTER_ALIGNMENT);
	
	p2.add(b1);
	p2.add(b2);

	pane.add(p1);
	pane.add(money);
	pane.add(p2);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("10")){
	    player.changeMoney(-1 * (player.getMoney()/10) );
	}else{
	    if(player.getMoney() < tax.getPayPrice()){
		JOptionPane.showMessageDialog(this,"You do not have enough money to pay for the tax. You will be defaulted to paying 10% instead","WARNING!",JOptionPane.ERROR_MESSAGE);
		player.changeMoney(-1 * (player.getMoney()/10) );
	    }else{
		player.changeMoney(-1 * tax.getPayPrice());
	    }
	}
	terminate();
       
    }

    public void terminate(){
	this.done = true;
    }
}
