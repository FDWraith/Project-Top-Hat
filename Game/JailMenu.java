import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JailMenu extends JFrame implements ActionListener{
    private Player player;
    private Container pane;
    public boolean done;
    
    public JailMenu(Player entry){
	this.player = entry;
	this.done = false;

	this.setTitle("Player "+(player.getIndex()+1)+", you are in Jail!");
	this.setSize(400,400);
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	pane = this.getContentPane();
	pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));

	JPanel p1 = new JPanel();
	p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
	p1.setAlignmentX(Component.CENTER_ALIGNMENT);

	JLabel l1 = new JLabel("You are stuck in Jail for "+player.getJailTime()+"more turns" );
	l1.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel l2 = new JLabel("What would you like to do?");
	l2.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel l3 = new JLabel("You can get out if you roll doubles or pay $50.");
	l3.setAlignmentX(Component.CENTER_ALIGNMENT);
	JLabel l4 = new JLabel("If you have a Get Out of Jail Free card, you may use it now");
	l4.setAlignmentX(Component.CENTER_ALIGNMENT);

	p1.add(l1);
	p1.add(l2);
	p1.add(l3);
	p1.add(l4);
	
	JPanel p2 = new JPanel();
	p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
	p2.setAlignmentX(Component.CENTER_ALIGNMENT);

	JButton b1 = new JButton("Roll");
	b1.addActionListener(this);
	b1.setActionCommand("Roll");

	JButton b2 = new JButton("Pay");
	b2.addActionListener(this);
	b2.setActionCommand("Pay");

	JButton b3 = new JButton("Use Card");
	b3.addActionListener(this);
	b3.setActionCommand("Card");
	
	p2.add(b1);
	p2.add(b2);
	p2.add(b3);
	
	JLabel money = new JLabel("Amount of Money: "+player.getMoney());

	JLabel cards = new JLabel("Number of Cards: "+player.getJailCards());
	
	pane.add(p1);
	pane.add(money);
	pane.add(cards);
	pane.add(p2);
    }

    public void actionPerformed(ActionEvent e){
	String event = e.getActionCommand();
	if(event.equals("Roll")){
	    Random r = new Random();
	    int n1 = r.nextInt(6);
	    int n2 = r.nextInt(6);
	    if(n1 == n2){
		JOptionPane.showMessageDialog(this,"You have gotten out of jail by rolling doubles","Congratulations", JOptionPane.INFORMATION_MESSAGE);
		player.setPhase(1);
		player.changeJailTime(0);
		player.changeLocation(player.getLocation()+n1+n2);
	    }else{
		//For all the other cases of rolling die.
		player.changeJailTime(player.getJailTime()-1);//lower one day
	        if(player.getJailTime() == 0){
		    //If this is the player's third turn in Jail
		    JOptionPane.showMessageDialog(this, "This is your third turn in jail. It is time to start heading out! You will still have to pay $50,unfortunately", "Congratulations",JOptionPane.INFORMATION_MESSAGE);
		    player.changeMoney(-1 * 50);
		    player.setPhase(1);
		    player.changeLocation(player.getLocation()+n1+n2);
		}
	    }
	    terminate();//no matter what happens, as soon as player decides to roll, his/her turn is over.
	}else if(event.equals("Pay")){
	    int res = JOptionPane.showConfirmDialog(this,"Are you sure you want to pay $50 to get out of jail? You currently have $"+player.getMoney(),"CONFIRM YOUR CHOICE!",JOptionPane.YES_NO_OPTION);
	    if(res == JOptionPane.YES_OPTION){
		if(player.getMoney()<50){
		    player.declareBankrupt();
		}else{
		    player.changeMoney(-1 * 50);
		    player.changeJailTime(0);
		}
		terminate();
	    }else{
	        //don't close anything.
	    }
	}else{
	    if(player.getJailCards() > 0){
		JOptionPane.showMessageDialog(this,"You are now out of Jail. You have "+(player.getJailCards()-1)+" Get Out Of Jail Free cards left","Alert!",JOptionPane.INFORMATION_MESSAGE);
		player.changeJailCards(-1);
		player.changeJailTime(0);
		//player.changeLocation(10);
		terminate();//End the player's turn
	    }else{
		JOptionPane.showMessageDialog(this,"You don't have any Jail cards available for you to use!","WARNING!",JOptionPane.INFORMATION_MESSAGE);
		//Nothing is closed.
	    }
	}
    }

    public void terminate(){
	this.done = true;
    }
}
