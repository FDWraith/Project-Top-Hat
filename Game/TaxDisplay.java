import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TaxDisplay extends JFrame implements ActionListener{
    private Player player;
    private Container pane;
    public boolean done;
    private Tax tax;
    
    public TaxDisplay(Player entry, Tax belong){
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
	JLabel l2 = new JLabel("Price you have to pay: "+tax.getPayPrice());
	l2.setAlignmentX(Component.CENTER_ALIGNMENT);

	p1.add(l1);
	p1.add(l2);
	
	JPanel p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
	p2.setAlignmentX(Component.CENTER_ALIGNMENT);

	JButton b1 = new JButton("OK");
	b1.addActionListener(this);
	b1.setActionCommand("Pay");

	p2.add(b1);
	
	JLabel money = new JLabel("Amount of Money: "+player.getMoney());

	pane.add(p1);
	pane.add(money);
	pane.add(p2);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("Pay")){
	    if(player.getMoney() < tax.getPayPrice()){
		JOptionPane.showMessageDialog(this,"You do not have sufficient funds to pay the tax. You are henceforth forfeit","WARNING!",JOptionPane.ERROR_MESSAGE);
		player.declareBankrupt();
	    }else{
		player.changeMoney(-1 * tax.getPayPrice());
	    }
	}else{
	    player.declareBankrupt();
	}
	terminate();
    }

    public void terminate(){
	this.done = true;
    }
}